package com.alta189.chavabot.plugins.java;

import java.io.File;
import java.util.logging.Logger;

import com.alta189.chavabot.ChavaManager;
import com.alta189.chavabot.plugins.Plugin;
import com.alta189.chavabot.plugins.PluginDescriptionFile;
import com.alta189.chavabot.plugins.PluginLoader;

public abstract class JavaPlugin implements Plugin {

	private boolean initialized = false;
	private PluginLoader loader = null;
	private ChavaManager cManager = null;
	private File file = null;
	private File dataFolder = null;
	private PluginClassLoader classLoader = null;
	private boolean enabled = false;
	private PluginDescriptionFile description = null;
	private boolean naggable = false;

	public JavaPlugin(){
		
	}

	public final PluginDescriptionFile getDescription() {
		return description;
	}

	
	public final void initialize(JavaPluginLoader loader, ChavaManager client, PluginDescriptionFile description, File dataFolder, File file, PluginClassLoader classLoader) {
		if (!initialized) {
			this.loader = loader;
			this.cManager = client;
			this.file = file;
			this.description = description;
			this.dataFolder = dataFolder;
			this.classLoader = classLoader;
			this.initialized = true;
		}
	}

	public abstract void onEnable();

	public abstract void onDisable();

	public final File getFile() {
		return file;
	}

	public final File getDataFolder() {
		return dataFolder;
	}

	public final ChavaManager getChavaManager() {
		return cManager;
	}

	public final PluginLoader getPluginLoader() {
		return loader;
	}

	public final boolean isEnabled() {
		return enabled;
	}
	
	public final Logger getLogger(){
		return cManager.getLogger();
	}
	
	public void onLoad() {
	}

	public void setEnabled(boolean arg) {
		if (this.enabled != arg) {
			this.enabled = arg;
			if (this.enabled) {
				this.onEnable();
			} else {
				this.onDisable();
			}
		}
	}

	public final boolean isNaggable() {
		return naggable;
	}

	public final void setNaggable(boolean naggable) {
		this.naggable = naggable;
	}

	public final PluginClassLoader getClassLoader() {
		return classLoader;
	}

}

