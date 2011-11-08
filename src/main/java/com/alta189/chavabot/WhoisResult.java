package com.alta189.chavabot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.jibble.pircbot.ReplyConstants;

public class WhoisResult {
	private Map<Integer, String> lines = new HashMap<Integer, String>();
	private final String nick;

	public WhoisResult(String nick) {
		this.nick = nick;
	}

	public ChavaUser build() {
		if (lines.size() <= 1) {
			return new ChavaUser(nick, null, null, null);
		} else {
			String userInfo = lines.get(ReplyConstants.RPL_WHOISUSER);
			String channelInfo = lines.get(ReplyConstants.RPL_WHOISCHANNELS);
			StringTokenizer tokens = new StringTokenizer(userInfo);
			tokens.nextToken();
			tokens.nextToken();
			String login = tokens.nextToken();
			String hostname = tokens.nextToken();
			tokens = new StringTokenizer(channelInfo.split(" :")[1]);
			List<String> channels = new ArrayList<String>();
			while (tokens.hasMoreElements()) {
				channels.add(tokens.nextToken());
			}
			return new ChavaUser(nick, login, hostname, channels);
		}
	}

	public void put(int code, String line) {
		lines.put(code, line);
	}
}
