package com.alta189.chavabot.plugins;

import java.util.logging.Logger;

public interface Plugin {
	
	public abstract PluginDescriptionFile getDescription();

	public abstract void onEnable();

	public abstract void onDisable();

	public abstract void onLoad();

	public abstract PluginLoader getAddonLoader();

	public abstract boolean isEnabled();

	public abstract void setEnabled(boolean arg);

	public abstract boolean isNaggable();

	public abstract void setNaggable(boolean b);

	public abstract Logger getLogger();

	public enum Mode {
		SINGLE_PLAYER,
		MULTIPLAYER,
		BOTH;
	}

}
