package com.alta189.chavabot;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: alta189
 */
public class IrcUser {
	private String nick;
	private Type type;
	private List<Channel> channels = new ArrayList<Channel>();


	public enum Type {
		NORMAL_USER,
		IRC_OPER;
	}
}
