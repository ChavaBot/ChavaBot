package com.alta189.chavabot;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.pircbotx.exception.IrcException;
import org.pircbotx.exception.NickAlreadyInUseException;
import org.pircbotx.hooks.managers.ListenerManager;

import com.alta189.chavabot.event.EventManager;
import com.alta189.chavabot.event.SimpleEventManager;
import com.alta189.chavabot.plugin.CommonPluginLoader;
import com.alta189.chavabot.plugin.CommonPluginManager;
import com.alta189.chavabot.plugin.Plugin;
import com.alta189.chavabot.plugin.PluginManager;

public class ChavaManager {
	private Logger logger = LogManager.getLogManager().getLogger("");
	private static ChavaManager instance;
	private CommonPluginManager pluginManager;
	private String pluginFolder = "plugins";
	private String version = null;
	private ChavaBot bot = null;
	private SimpleEventManager events = new SimpleEventManager();
	
	private ChavaManager() {
	}
	
	public static ChavaManager getInstance() {
		if (instance == null) {
			instance = new ChavaManager();
			instance.pluginManager = new CommonPluginManager(instance);
		}
		return instance;
	}
	
	public static String getVersion() {
		if (instance.version == null) {
			String build = "0";
			try {
				build = IOUtils.toString(ChavaManager.class.getResource("version").openStream(), "UTF-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
			instance.version = "ChavaBot b" + build + ", author alta189";
		}
		return instance.version;
	}
	
	public ChavaBot getChavaBot() {
		return bot;
	}
	
	public void loadPlugins() {
		pluginManager.registerPluginLoader(CommonPluginLoader.class);

		File pluginDir = new File(pluginFolder);
		if (pluginDir.exists()) {
			Plugin[] plugins = pluginManager.loadPlugins(pluginDir);
			for (Plugin plugin : plugins) {
				try {
					plugin.onLoad();
				} catch (Throwable ex) {
					Logger.getLogger(ChavaManager.class.getName()).log(Level.SEVERE, ex.getMessage() + " initializing " + plugin.getDescription().getFullName() + " (Is it up to date?)", ex);
				}
			}
		} else {
			pluginDir.mkdir();
		}
	}
	
	public void enablePlugins() {
		Plugin[] plugins = pluginManager.getPlugins();

		for (Plugin plugin : plugins) {
			loadPlugin(plugin);
		}
	}

	private void loadPlugin(Plugin plugin) {
		try {
			pluginManager.enablePlugin(plugin);
		} catch (Throwable ex) {
			Logger.getLogger(ChavaManager.class.getName()).log(Level.SEVERE, ex.getMessage() + " loading " + plugin.getDescription().getFullName() + " (Is it up to date?)", ex);
		}
	}
	
	public Logger getLogger() {
		return logger;
	}

	public static PluginManager getPluginManager() {
		return getInstance().pluginManager;
	}

	public File getUpdateFolder() {
		return new File(pluginFolder + File.separator + "updates");
	}
	
	/**
	 * Register ChavaBot events
	 * @return
	 */
	public static EventManager getEventManager() {
		return instance.events;
	}
	
	/**
	 * Register PirBotX Listeners
	 * @param listener
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static ListenerManager getListenerManager() {
		return instance.getChavaBot().getBot().getListenerManager();
	}

	public void start(Options options) throws NickAlreadyInUseException, IOException, IrcException {
		if (bot == null) {
			bot = new ChavaBot();
			bot.setHost(options.getHost());
			bot.setLogin(options.getLogin());
			bot.setPort(options.getPort());
			bot.setNick(options.getNick());
			bot.setVerbose(options.isVerbose());
			bot.setAjChannels(options.getChannels());
			bot.setPass(options.getPass());
			loadPlugins();
			enablePlugins();
			bot.connect();
		}
	}

}
