package com.alta189.chavabot;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: alta189
 */
public class Channel {
	private String name;
	private boolean mute;
	private List<User> users = new ArrayList<User>();

	/**
	 * Returns true if the bot is muted in that channel
	 * @return mute
	 */
	public boolean isMuted() {
		return mute;
	}

	/**
	 * Sets the mute for the jBot on this channel
	 * @param mute
	 */
	public void setMute(boolean mute) {
		this.mute = mute;
	}


}
