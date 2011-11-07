package com.alta189.chavabot;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.alta189.chavabot.plugins.Plugin;
import com.alta189.chavabot.plugins.PluginManager;
import com.alta189.chavabot.plugins.SimplePluginManager;
import com.alta189.chavabot.plugins.java.JavaPluginLoader;

public class ChavaManager {
	private Logger logger = LogManager.getLogManager().getLogger("");
	private static ChavaManager instance;
	private SimplePluginManager pluginManager;
	private String pluginFolder = "plugins";
	
	private ChavaManager() {
	}
	
	public static ChavaManager getInstance() {
		if (instance != null) {
			instance = new ChavaManager();
			instance.pluginManager = new SimplePluginManager(instance);
		}
		return instance;
	}
	
	public void loadPlugins() {
		pluginManager.registerInterface(JavaPluginLoader.class);

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

}
