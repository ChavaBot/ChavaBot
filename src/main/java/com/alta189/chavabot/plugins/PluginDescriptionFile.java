package com.alta189.chavabot.plugins;

import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

public final class PluginDescriptionFile {
	private static final Yaml yaml = new Yaml(new SafeConstructor());
	private String name = null;
	private String main = null;
	private ArrayList<String> depend = null;
	private ArrayList<String> softDepend = null;
	private String version = null;
	private Object commands = null;
	private String description = null;
	private ArrayList<String> authors = new ArrayList<String>();
	private String website = null;
	private boolean database = false;

	@SuppressWarnings("unchecked")
	public PluginDescriptionFile(final InputStream stream) throws InvalidDescriptionException {
		loadMap((Map<String, Object>) yaml.load(stream));
	}

	@SuppressWarnings("unchecked")
	public PluginDescriptionFile(final Reader reader) throws InvalidDescriptionException {
		loadMap((Map<String, Object>) yaml.load(reader));
	}

	public PluginDescriptionFile(final String pluginName, final String pluginVersion, final String mainClass) {
		name = pluginName;
		version = pluginVersion;
		main = mainClass;
	}

	public void save(Writer writer) {
		yaml.dump(saveMap(), writer);
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	public String getFullName() {
		return name + " v" + version;
	}

	public String getMain() {
		return main;
	}

	public Object getCommands() {
		return commands;
	}

	public Object getDepend() {
		return depend;
	}

	public Object getSoftDepend() {
		return softDepend;
	}

	public String getDescription() {
		return description;
	}

	public ArrayList<String> getAuthors() {
		return authors;
	}

	public String getWebsite() {
		return website;
	}

	public boolean isDatabaseEnabled() {
		return database;
	}

	public void setDatabaseEnabled(boolean database) {
		this.database = database;
	}

	@SuppressWarnings("unchecked")
	private void loadMap(Map<String, Object> map) throws InvalidDescriptionException {
		try {
			name = map.get("name").toString();

			if (!name.matches("^[A-Za-z0-9 _.-]+$")) {
				throw new InvalidDescriptionException("name '" + name + "' contains invalid characters.");
			}
		} catch (NullPointerException ex) {
			throw new InvalidDescriptionException(ex, "name is not defined");
		} catch (ClassCastException ex) {
			throw new InvalidDescriptionException(ex, "name is of wrong type");
		}

		try {
			version = map.get("version").toString();
		} catch (NullPointerException ex) {
			throw new InvalidDescriptionException(ex, "version is not defined");
		} catch (ClassCastException ex) {
			throw new InvalidDescriptionException(ex, "version is of wrong type");
		}

		try {
			main = map.get("main").toString();
			if (main.startsWith("org.bukkit.")) {
				throw new InvalidDescriptionException("main may not be within the org.bukkit namespace");
			}
		} catch (NullPointerException ex) {
			throw new InvalidDescriptionException(ex, "main is not defined");
		} catch (ClassCastException ex) {
			throw new InvalidDescriptionException(ex, "main is of wrong type");
		}

		if (map.containsKey("commands")) {
			try {
				commands = map.get("commands");
			} catch (ClassCastException ex) {
				throw new InvalidDescriptionException(ex, "commands are of wrong type");
			}
		}

		if (map.containsKey("depend")) {
			try {
				depend = (ArrayList<String>) map.get("depend");
			} catch (ClassCastException ex) {
				throw new InvalidDescriptionException(ex, "depend is of wrong type");
			}
		}

		if (map.containsKey("softdepend")) {
			try {
				softDepend = (ArrayList<String>) map.get("softdepend");
			} catch (ClassCastException ex) {
				throw new InvalidDescriptionException(ex, "softdepend is of wrong type");
			}
		}

		if (map.containsKey("database")) {
			try {
				database = (Boolean) map.get("database");
			} catch (ClassCastException ex) {
				throw new InvalidDescriptionException(ex, "database is of wrong type");
			}
		}

		if (map.containsKey("website")) {
			try {
				website = (String) map.get("website");
			} catch (ClassCastException ex) {
				throw new InvalidDescriptionException(ex, "website is of wrong type");
			}
		}

		if (map.containsKey("description")) {
			try {
				description = (String) map.get("description");
			} catch (ClassCastException ex) {
				throw new InvalidDescriptionException(ex, "description is of wrong type");
			}
		}

		if (map.containsKey("author")) {
			try {
				String extra = (String) map.get("author");

				authors.add(extra);
			} catch (ClassCastException ex) {
				throw new InvalidDescriptionException(ex, "author is of wrong type");
			}
		}

		if (map.containsKey("authors")) {
			try {
				ArrayList<String> extra = (ArrayList<String>) map.get("authors");

				authors.addAll(extra);
			} catch (ClassCastException ex) {
				throw new InvalidDescriptionException(ex, "authors are of wrong type");
			}
		}

	}

	private Map<String, Object> saveMap() {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("name", name);
		map.put("main", main);
		map.put("version", version);
		map.put("database", database);

		if (commands != null) {
			map.put("command", commands);
		}
		if (depend != null) {
			map.put("depend", depend);
		}
		if (softDepend != null) {
			map.put("softdepend", softDepend);
		}
		if (website != null) {
			map.put("website", website);
		}
		if (description != null) {
			map.put("description", description);
		}

		if (authors.size() == 1) {
			map.put("author", authors.get(0));
		} else if (authors.size() > 1) {
			map.put("authors", authors);
		}

		return map;
	}
}
