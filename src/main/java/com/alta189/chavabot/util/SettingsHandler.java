package com.alta189.chavabot.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * <b>Settings Handler</b><br>
 * A new better way of handling settings/configs without the complexity of
 * YAML or using Bukkit's Configuration Class (If this is for a Bukkit plugin). 
 * @author alta189
 * @version 1.5.1
 * @see <a href="http://www.alta189.com">www.alta189.com - My incomplete web site :P</a>
 *
 */

public class SettingsHandler {

	private File out;
	private boolean cached = false;
	private String resource = null;
	private HashMap<String, String> cache;
	private InputStream input = null;
	private boolean ignoreCase = false;

	/**
	 * Constructor for the Settings/Config file.
	 * Note: The resource must be in the same package or sub package
	 * as this. If in a Sub directory the resource parameter
	 * is the path to it from this class file.
	 * @param String resource
	 * @param File out
	 */
	public SettingsHandler(String resource, String out) {
		this.resource = resource;
		this.out = new File(out);
	}

	/**
	 * Constructor for the Settings/Config file.
	 * Note: The resource must be in the same package or sub package
	 * as this. If in a sub directory the resource parameter
	 * is the path to it from this class file.
	 * @param String resource
	 * @param File out
	 */
	public SettingsHandler(String resource, File out) {
		this.resource = resource;
		this.out = out;
	}

	/**
	 * Constructor for the Settings/Config file.
	 * Note: I discourage the use of this constructor, as it is not a fully tested as it should be.
	 * @param InputStream input
	 * @param File out
	 */
	public SettingsHandler(InputStream input, File out) {
		this.input = input;
		this.out = out;
	}

	/**
	 * Constructor for the Settings/Config file.
	 * Note: This file must exist.
	 * @param String out
	 * @throws FileNotFoundException
	 */
	public SettingsHandler(File out) throws FileNotFoundException {
		if (!out.exists())
			throw new FileNotFoundException("The out does not exist.");
		this.out = out;
	}

	/**
	 * Returns whether caching is enabled. <br>
	 * See {@link #setCached(boolean) setCached(boolean)} for more info on caching
	 * @return Boolean cached
	 */
	public boolean isCached() {
		return this.cached;
	}

	/**
	 * When cached the SettingsHandler will store the settings
	 * in a HashMap for quicker access. 
	 * @param Boolean cached
	 */
	public void setCached(boolean cached) {
		this.cached = cached;
		if (this.cached = false)
			this.cache = null;
	}
	
	/**
	 * Returns whether ignode case is enabled. <br>
	 * See {@link #setIgnoreCase(boolean) setIgnoreCase(boolean)} for more info on ignore case
	 * @return Boolean cached
	 */
	public boolean isIgnoreCase() {
		return ignoreCase;
	}
	
	/**
	 * When setIgnoreCase is enabled
	 * @param ignoreCase
	 */
	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	/**
	 * Returns the Settings File as a HashMap with a String key and a String value
	 * @return HashMap<String, String> map
	 */
	public HashMap<String, String> getAsMap() {
		if (this.cached)
			return cache;
		return this.loadHashMap();
	}
	
	/**
	 * Clears the cache and reset the file.
	 */
	public void reset() {
		if (cache != null) this.cache.clear();
		this.out.delete();
		try {
			this.out.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.load();
	}
	
	/**
	 * Clears the cache and reset the file.
	 * @param in InputStream of the default file
	 */
	public void reset(InputStream in) {
		if (cache != null) this.cache.clear();
		this.out.delete();
		this.input = in;
		try {
			this.out.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.load();
	}

	/**
	 * Private method that takes the resource and writes it to the File out.
	 * @param String name
	 */
	private void create(String resource) {
		InputStream input = getClass().getResourceAsStream(resource);
		if (input != null) {
			FileOutputStream output = null;
			try {
				output = new FileOutputStream(out);
				byte[] buf = new byte[8192];
				int length = 0;

				while ((length = input.read(buf)) > 0) {
					output.write(buf, 0, length);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (input != null)
						input.close();
				} catch (Exception e) {
				}
				try {
					if (output != null)
						output.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * Private method that takes the InputStream and it writes it to the File out
	 * @param InputStream input
	 */
	private void create(InputStream input) {
		if (input != null) {
			FileOutputStream output = null;
			try {
				output = new FileOutputStream(out);
				byte[] buf = new byte[8192];
				int length = 0;

				while ((length = input.read(buf)) > 0) {
					output.write(buf, 0, length);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (input != null)
						input.close();
				} catch (Exception e) {
				}
				try {
					if (output != null)
						output.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * Private method that loads the properties in a HashMap.
	 * @return HashMap result
	 */
	private HashMap<String, String> loadHashMap() {
		HashMap<String, String> result = new HashMap<String, String>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(out));
			String line = null;

			while ((line = br.readLine()) != null) {
				if ((line.isEmpty()) || (line.startsWith("#")) || (!line.contains(": ")))
					continue;
				String[] args = line.split(": ");
				if (args.length < 2) {
					if (ignoreCase) {
						result.put(args[0].toLowerCase(), null);
					} else {
						result.put(args[0], null);
					}
					continue;
				}
				if (ignoreCase) {
					result.put(args[0].toLowerCase(), args[1]);
				} else {
					result.put(args[0], args[1]);
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * Call this method before doing anything else with SettingsHandler. 
	 * The only exception to this is {@link #setCached(Boolean) setCanced(Boolean)}.
	 * If caching is enabled then it will load the Properties into the cache.
	 */
	public void load() {
		if (this.resource != null && !out.exists()) {
			create(resource);
		}
		if (this.input != null && !out.exists()) {
			create(input);
		} else if (this.input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (this.cached) {
			this.cache = this.loadHashMap();
		}

	}

	/**
	 * Returns the value of a property as a String
	 * @param String property
	 * @return String value
	 */
	public String getPropertyString(String property, String argDefault) {
		if (ignoreCase) property = property.toLowerCase();
		try {
			if (this.cached) {
				return this.cache.get(property);
			} else {
				HashMap<String, String> contents = this.loadHashMap();
				return contents.get(property);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return argDefault;
	}

	/**
	 * Returns the value of a property as a Integer
	 * @param String property
	 * @return Integer value
	 */
	public Integer getPropertyInteger(String property, Integer argDefault) {
		if (ignoreCase) property = property.toLowerCase();
		try {
			if (this.cached) {
				return Integer.parseInt(this.cache.get(property));
			} else {
				HashMap<String, String> contents = this.loadHashMap();
				return Integer.parseInt(contents.get(property));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return argDefault;
	}

	/**
	 * Returns the value of a property as a Boolean
	 * @param String property
	 * @return Boolean value
	 */
	public Boolean getPropertyBoolean(String property, Boolean argDefault) {
		if (ignoreCase) property = property.toLowerCase();
		try {
			String result = null;
			if (this.cached) {
				result = this.cache.get(property);
			} else {
				HashMap<String, String> contents = this.loadHashMap();
				result = contents.get(property);
			}
			if (result.equalsIgnoreCase("true") || result.equalsIgnoreCase("false")) {
				return Boolean.valueOf(result.toLowerCase());
			} else {
				return argDefault;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return argDefault;
	}

	/**
	 * Returns the value of a property as a Double
	 * @param String property
	 * @return Double value
	 */
	public Double getPropertyDouble(String property, Double argDefault) {
		if (ignoreCase) property = property.toLowerCase();
		try {
			String result = null;
			if (this.cached) {
				result = this.cache.get(property);
			} else {
				HashMap<String, String> contents = this.loadHashMap();
				result = contents.get(property);
			}
			if (!result.contains("."))
				result += ".0";
			return Double.parseDouble(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return argDefault;
	}

	/**
	 * Returns true if a property exists
	 * @param String property
	 * @return Boolean check
	 */
	public Boolean checkProperty(String property) {
		if (ignoreCase) property = property.toLowerCase();
		String check = null;
		try {
			if (this.cached) {
				check = this.cache.get(property);
			} else {
				HashMap<String, String> contents = this.loadHashMap();
				check = contents.get(property);
			}
			if (check != null)
				return true;
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	// Editing the Settings/Config File Methods \\

	/**
	 * Private method that writes out the new Settings/Config file after changes are made. 
	 * If caching is enabled, it will call the 
	 * {@link #load() load()} method.
	 * 
	 * @param HashMap newContents
	 */
	private void flush(HashMap<Integer, String> newContents) {
		try {
			this.delFile(out);
			out.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(out));
			for (int i = 1; i <= newContents.size(); i++) {
				String line = newContents.get(i);
				if (line == null) {
					writer.append("\n");
					continue;
				}
				writer.append(line);
				writer.append("\n");
			}
			if (writer != null) {
				writer.flush();
				writer.close();
			}
			if (cached)
				this.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Private method used by {@link #flush(HashMap) flush(HashMap)} to delete the old
	 * Settings/Config file.
	 * @param File file
	 */
	private void delFile(File file) {
		File delFile = file;
		if (delFile.exists())
			delFile.delete();
	}

	/**
	 * Private method to get a HashMap of the contents of the Settings/Contents file.
	 * They are stored as lines indexed by the line number.
	 * @return HashMap contents
	 */
	private HashMap<Integer, String> getAllFileContents() {
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		Integer i = 1;
		try {
			BufferedReader br = new BufferedReader(new FileReader(out));
			String line = null;

			while ((line = br.readLine()) != null) {
				if (line.isEmpty()) {
					result.put(i, null);
					i++;
					continue;
				}

				result.put(i, line);
				i++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * Allows you to add a comment to the end of the end of the file.
	 * @param String comment
	 */
	public void insertComment(String comment) {
		HashMap<Integer, String> contents = this.getAllFileContents();
		contents.put(contents.size() + 1, "#" + comment);
		this.flush(contents);
	}

	/**
	 * Allows to add a comment at the line that you specify.
	 * @param String comment
	 * @param Integer line
	 */
	public void insertComment(String comment, Integer line) {
		HashMap<Integer, String> contents = this.getAllFileContents();
		if (line >= contents.size() + 1)
			return;
		HashMap<Integer, String> newContents = new HashMap<Integer, String>();
		for (int i = 1; i < line; i++) {
			newContents.put(i, contents.get(i));
		}
		newContents.put(line, "#" + comment);
		for (int i = line; i <= contents.size(); i++) {
			newContents.put(i + 1, contents.get(i));
		}
		this.flush(newContents);
	}

	/**
	 * Allows you to add a property at the end of the file.
	 * @param String property
	 * @param Object obj
	 */
	public void put(String property, Object obj) {
		HashMap<Integer, String> contents = this.getAllFileContents();
		contents.put(contents.size() + 1, property + ": " + obj.toString());
		this.flush(contents);
	}

	/**
	 * Allows you to delete a property
	 * @param String property
	 */
	public void delete(String property) {
		if (ignoreCase) property = property.toLowerCase();
		HashMap<Integer, String> contents = this.getAllFileContents();
		contents.remove(property);
		this.flush(contents);
	}

	/**
	 * Allows you to add a property at the line that you specify
	 * @param String property
	 * @param Object obj
	 * @param Integer line
	 */
	public void put(String property, Object obj, Integer line) {
		HashMap<Integer, String> contents = this.getAllFileContents();
		if (line >= contents.size() + 1)
			return;
		HashMap<Integer, String> newContents = new HashMap<Integer, String>();
		for (int i = 1; i < line; i++) {
			newContents.put(i, contents.get(i));
		}
		newContents.put(line, property + ": " + obj.toString());
		for (int i = line; i <= contents.size(); i++) {
			newContents.put(i + 1, contents.get(i));
		}
		this.flush(newContents);
	}

	/**
	 * Allows you to change the value of a property.
	 * @param String property
	 * @param Object obj
	 */
	public void changeProperty(String property, Object obj) {
		if (ignoreCase) property = property.toLowerCase();
		HashMap<Integer, String> contents = this.getAllFileContents();
		if ((contents == null)) {
			return;
		}
		for (int i = 1; i <= contents.size(); i++) {
			if (contents.get(i) == null)
				continue;
			String check = contents.get(i);
			if (check.startsWith(property)) {
				check = check.replace(property, "");
				if (!(check.startsWith(": ")))
					continue;
				contents.remove(i);
				contents.put(i, property + ": " + obj.toString());
			}
		}
		this.flush(contents);
	}

	/**
	 * Returns the amount of the lines in the file. It ignores the last line if it is empty.
	 * @return Integer lineCount
	 */
	public Integer getLineCount() {
		HashMap<Integer, String> contents = this.getAllFileContents();
		return contents.size();
	}
}
