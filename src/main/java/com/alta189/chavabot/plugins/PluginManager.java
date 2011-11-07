package com.alta189.chavabot.plugins;

import java.io.File;

import com.alta189.chavabot.events.Event;


public abstract interface PluginManager {

	public abstract Plugin getPlugin(String paramString);

	public abstract Plugin[] getPlugins();

	public abstract boolean isPluginEnabled(String paramString);

	public abstract boolean isPluginEnabled(Plugin paramPlugin);

	public abstract Plugin loadPlugin(File paramFile) throws InvalidPluginException, InvalidDescriptionException, UnknownDependencyException;

	public abstract Plugin[] loadPlugins(File paramFile);

	public abstract void disablePlugins();

	public abstract void clearPlugins();

	public abstract <TEvent extends Event<TEvent>> void callEvent(TEvent event);

	public abstract void enablePlugin(Plugin paramPlugin);

	public abstract void disablePlugin(Plugin paramPlugin);

}

