/*
 * This file is part of Spout (http://www.getspout.org/).
 *
 * The SpoutAPI is licensed under the SpoutDev license version 1.  
 *
 * SpoutAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the 
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * SpoutAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev license version 1 along with this program.  
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://getspout.org/SpoutDevLicenseV1.txt> for the full license, 
 * including the MIT license.
 */
package com.alta189.chavabot.plugin;

public interface PluginStore {
	
	public void downloadAddon(int databaseId, DownloadEventDelegate delegate);

	public void downloadAddon(String name, DownloadEventDelegate delegate);

	public boolean hasUpdate(Plugin addon);

	public boolean hasInternetAccess(Plugin addon);

	public long getQuota(Plugin addon);

	public boolean isEnabled(Plugin addon);

	public abstract class DownloadEventDelegate {
		
		public abstract void onDownloadFinished(Plugin addon);

		public abstract void onDownloadFailure(Exception e, int databaseId, String name);
		
	}
}
