package com.alta189.chavabot.plugins;

import java.io.File;
import java.util.regex.Pattern;

public abstract interface PluginLoader {

	public abstract Plugin loadPlugin(File paramFile) throws InvalidPluginException, InvalidPluginException, UnknownDependencyException, InvalidDescriptionException;

	public abstract Plugin loadPlugin(File paramFile, boolean paramBoolean) throws InvalidPluginException, InvalidPluginException, UnknownDependencyException, InvalidDescriptionException;

	public abstract Pattern[] getPluginFileFilters();

	public abstract void enablePlugin(Plugin paramPlugin);

	public abstract void disablePlugin(Plugin paramPlugin);

}
