package com.alta189.chavabot.plugin;

import java.io.File;
import java.util.logging.Logger;

import com.alta189.chavabot.ChavaManager;

public abstract class CommonPlugin implements Plugin {

	private PluginDescriptionFile description;
	private CommonClassLoader classLoader;
	private CommonPluginLoader pluginLoader;
	private File dataFolder;
	private File paramFile;
	private boolean enabled;
	private ChavaManager chavamanager;
	
	public abstract void onEnable();

	
	public abstract void onDisable();

	
	public void onReload() {
	}

	
	public void onLoad() {
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public PluginLoader getPluginLoader() {
		return pluginLoader;
	}

	public Logger getLogger() {
		return chavamanager.getLogger();
	}

	public PluginDescriptionFile getDescription() {
		return description;
	}

	public void initialize(CommonPluginLoader commonsPluginLoader, ChavaManager chavamanager,
			PluginDescriptionFile desc, File dataFolder, File paramFile,
			CommonClassLoader loader) {
		this.description = desc;
		this.classLoader = loader;
		this.pluginLoader = commonsPluginLoader;
		this.setDataFolder(dataFolder);
		this.paramFile = paramFile;
		this.chavamanager = chavamanager;
		// TODO Auto-generated method stub
		
	}
	
	public ChavaManager getChavaManager() {
		return chavamanager;
	}

	public ClassLoader getClassLoader() {
		return classLoader;
	}


	/**
	 * @return the dataFolder
	 */
	public File getDataFolder() {
		return dataFolder;
	}


	/**
	 * @param dataFolder the dataFolder to set
	 */
	public void setDataFolder(File dataFolder) {
		this.dataFolder = dataFolder;
	}

}
