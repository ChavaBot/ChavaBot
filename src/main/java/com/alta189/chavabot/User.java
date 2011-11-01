package com.alta189.chavabot;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: alta189
 */
public abstract class User {
	protected String nick;
	protected String fullName;
	protected Type type;
	protected List<Channel> channels = new ArrayList<Channel>();


	public String getNick() {
		return nick;
	}

	public String getPrefix() {
		return null;
	}

	public boolean hasOp(String channel) {
		return false;
	}

	public String getHost() {
		return null;
	}

	public String getFullName() {
		return fullName;
	}

	public boolean inChannel(String channel) {
		for (Channel chan : channels) {
			if (chan.toString().equalsIgnoreCase(channel)) {
				return true;
			}
		}
		return false;
	}
	
	public enum Type {
		NORMAL_USER,
		IRC_OPER;
	}
}
