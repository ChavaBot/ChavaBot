package com.alta189.chavabot.plugins;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alta189.chavabot.ChavaManager;
import com.alta189.chavabot.events.Event;
import com.alta189.chavabot.events.HandlerList;
import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.plugins.java.JavaPluginLoader;
import com.alta189.chavabot.util.FileUtil;

public class SimplePluginManager implements PluginManager {

	private ChavaManager cManager;
	private final Map<Pattern, PluginLoader> fileAssociations = new HashMap<Pattern, PluginLoader>();
	private final List<Plugin> plugins = new ArrayList<Plugin>();
	private final Map<String, Plugin> lookupNames = new HashMap<String, Plugin>();
	private static File updateDirectory = null;

	public SimplePluginManager(ChavaManager instance) {
		cManager = instance;
	}

	/**
	 * Registers the specified plugin loader
	 * 
	 * @param loader Class name of the PluginLoader to register
	 * @throws IllegalArgumentException Thrown when the given Class is not a valid PluginLoader
	 */
	public void registerInterface(Class<? extends PluginLoader> loader) throws IllegalArgumentException {
		if (!loader.equals(JavaPluginLoader.class)){
			throw new UnsupportedOperationException("Spoutcraft does not currently support non-standard plugin loaders. :(");
		}
		PluginLoader instance;

		if (PluginLoader.class.isAssignableFrom(loader)) {
			Constructor<? extends PluginLoader> constructor;

			try {
				constructor = loader.getConstructor(ChavaManager.class);
				instance = constructor.newInstance(cManager);
			} catch (NoSuchMethodException ex) {
				String className = loader.getName();

				throw new IllegalArgumentException(String.format("Class %s does not have a public %s(Spoutcraft) constructor", className, className), ex);
			} catch (Exception ex) {
				throw new IllegalArgumentException(String.format("Unexpected exception %s while attempting to construct a new instance of %s", ex.getClass().getName(), loader.getName()), ex);
			}
		} else {
			throw new IllegalArgumentException(String.format("Class %s does not implement interface PluginLoader", loader.getName()));
		}

		Pattern[] patterns = instance.getPluginFileFilters();

		synchronized (this) {
			for (Pattern pattern : patterns) {
				fileAssociations.put(pattern, instance);
			}
		}
	}
	
	/**
	 * Loads the plugins contained within the specified directory
	 * 
	 * @param directory Directory to check for plugins
	 * @return A list of all plugins loaded
	 */
	public Plugin[] loadPlugins(File directory) {
		List<Plugin> result = new ArrayList<Plugin>();
		File[] files = directory.listFiles();

		boolean allFailed = false;
		boolean finalPass = false;

		LinkedList<File> filesList = new LinkedList<File>(Arrays.asList(files));

		if (!(cManager.getUpdateFolder() == null)) {
			updateDirectory = cManager.getUpdateFolder();
		}

		while (!allFailed || finalPass) {
			allFailed = true;
			Iterator<File> itr = filesList.iterator();

			while (itr.hasNext()) {
				File file = itr.next();
				Plugin plugin = null;

				try {
					plugin = loadPlugin(file, finalPass);
					itr.remove();
				} catch (UnknownDependencyException ex) {
					if (finalPass) {
						cManager.getLogger().log(Level.SEVERE, "Could not load '" + file.getPath() + "' in folder '" + directory.getPath() + "': " + ex.getMessage(), ex);
						itr.remove();
					} else {
						plugin = null;
					}
				} catch (InvalidPluginException ex) {
					cManager.getLogger().log(Level.SEVERE, "Could not load '" + file.getPath() + "' in folder '" + directory.getPath() + "': ", ex.getCause());
					itr.remove();
				} catch (InvalidDescriptionException ex) {
					cManager.getLogger().log(Level.SEVERE, "Could not load '" + file.getPath() + "' in folder '" + directory.getPath() + "': " + ex.getMessage(), ex);
					itr.remove();
				}

				if (plugin != null) {
					result.add(plugin);
					allFailed = false;
					finalPass = false;
				}
			}
			if (finalPass) {
				break;
			} else if (allFailed) {
				finalPass = true;
			}
		}

		return result.toArray(new Plugin[result.size()]);
	}

	/**
	 * Loads the plugin in the specified file
	 * 
	 * File must be valid according to the current enabled Plugin interfaces
	 * 
	 * @param file File containing the plugin to load
	 * @return The Plugin loaded, or null if it was invalid
	 * @throws InvalidPluginException Thrown when the specified file is not a valid plugin
	 * @throws InvalidDescriptionException Thrown when the specified file contains an invalid description
	 */
	public synchronized Plugin loadPlugin(File file) throws InvalidPluginException, InvalidDescriptionException, UnknownDependencyException {
		return loadPlugin(file, true);
	}

	/**
	 * Loads the plugin in the specified file
	 * 
	 * File must be valid according to the current enabled Plugin interfaces
	 * 
	 * @param file File containing the plugin to load
	 * @param ignoreSoftDependencies Loader will ignore soft dependencies if this flag is set to true
	 * @return The Plugin loaded, or null if it was invalid
	 * @throws InvalidPluginException Thrown when the specified file is not a valid plugin
	 * @throws InvalidDescriptionException Thrown when the specified file contains an invalid description
	 */
	public synchronized Plugin loadPlugin(File file, boolean ignoreSoftDependencies) throws InvalidPluginException, InvalidDescriptionException, UnknownDependencyException {
		File updateFile = null;

		if (updateDirectory != null && updateDirectory.isDirectory() && (updateFile = new File(updateDirectory, file.getName())).isFile()) {
			if (FileUtil.copy(updateFile, file)) {
				updateFile.delete();
			}
		}

		Set<Pattern> filters = fileAssociations.keySet();
		Plugin result = null;

		for (Pattern filter : filters) {
			String name = file.getName();
			Matcher match = filter.matcher(name);

			if (match.find()) {
				PluginLoader loader = fileAssociations.get(filter);

				result = loader.loadPlugin(file, ignoreSoftDependencies);
			}
		}

		if (result != null) {
			plugins.add(result);
			lookupNames.put(result.getDescription().getName(), result);
		}
		return result;
	}

	/**
	 * Checks if the given plugin is loaded and returns it when applicable
	 * 
	 * Please note that the name of the plugin is case-sensitive
	 * 
	 * @param name Name of the plugin to check
	 * @return Plugin if it exists, otherwise null
	 */
	public synchronized Plugin getPlugin(String name) {
		return lookupNames.get(name);
	}

	public synchronized Plugin[] getPlugins() {
		return plugins.toArray(new Plugin[0]);
	}

	/**
	 * Checks if the given plugin is enabled or not
	 * 
	 * Please note that the name of the plugin is case-sensitive.
	 * 
	 * @param name Name of the plugin to check
	 * @return true if the plugin is enabled, otherwise false
	 */
	public boolean isPluginEnabled(String name) {
		Plugin plugin = getPlugin(name);

		return isPluginEnabled(plugin);
	}

	/**
	 * Checks if the given plugin is enabled or not
	 * 
	 * @param plugin Plugin to check
	 * @return true if the plugin is enabled, otherwise false
	 */
	public boolean isPluginEnabled(Plugin plugin) {
		if ((plugin != null) && (plugins.contains(plugin))) {
			return plugin.isEnabled();
		} else {
			return false;
		}
	}

	public void enablePlugin(final Plugin plugin) {
		if (!plugin.isEnabled()) {
			try {
				plugin.getPluginLoader().enablePlugin(plugin);
			} catch (Throwable ex) {
				cManager.getLogger().log(Level.SEVERE, "Error occurred (in the plugin loader) while enabling " + plugin.getDescription().getFullName() + " (Is it up to date?): " + ex.getMessage(), ex);
			}
		}
	}

	public void disablePlugins() {
		for (Plugin plugin : getPlugins()) {
			disablePlugin(plugin);
		}
	}

	public void disablePlugin(final Plugin plugin) {
		if (plugin.isEnabled()) {
			try {
				plugin.getPluginLoader().disablePlugin(plugin);
			} catch (Throwable ex) {
				cManager.getLogger().log(Level.SEVERE, "Error occurred (in the plugin loader) while disabling " + plugin.getDescription().getFullName() + " (Is it up to date?): " + ex.getMessage(), ex);
			}
		}
	}

	public void clearPlugins() {
		synchronized (this) {
			disablePlugins();
			plugins.clear();
			lookupNames.clear();
			HandlerList.clearAll();
		}
	}

	/**
	 * Call an event.
	 * 
	 * @param <TEvent> Event subclass
	 * @param event Event to handle
	 * @author lahwran
	 */
	public <TEvent extends Event<TEvent>> void callEvent(TEvent event) {
		
		HandlerList<TEvent> handlerlist = event.getHandlers();
		handlerlist.bake();

		Listener<TEvent>[][] handlers = handlerlist.handlers;
		int[] handlerids = handlerlist.handlerids;
		

		for (int arrayidx = 0; arrayidx < handlers.length; arrayidx++) {

			// if the order slot is even and the event has stopped propogating
			if (event.isCancelled() && (handlerids[arrayidx] & 1) == 0)
				continue; // then don't call this order slot

			for (int handler = 0; handler < handlers[arrayidx].length; handler++) {
				try {
					
					handlers[arrayidx][handler].onEvent(event);

				} catch (Throwable t) {
					System.err.println("Error while passing event " + event);
					t.printStackTrace();
				}
			}
		}
	}

}

