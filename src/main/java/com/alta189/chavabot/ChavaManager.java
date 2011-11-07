package com.alta189.chavabot;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.alta189.chavabot.plugins.PluginManager;

public class ChavaManager {
	private Logger logger = LogManager.getLogManager().getLogger("");
	private static ChavaManager instance;
	private PluginManager pManager;
	
	public static ChavaManager getInstance() {
		if (instance != null) {
			instance = new ChavaManager();
		}
		return instance;
	}
	
	public Logger getLogger() {
		return logger;
	}

	public static PluginManager getPluginManager() {
		return getInstance().pManager;
	}

}
