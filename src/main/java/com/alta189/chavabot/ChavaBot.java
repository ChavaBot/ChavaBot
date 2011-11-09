package com.alta189.chavabot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;

import com.alta189.chavabot.botevents.SendMessageEvent;
import com.alta189.chavabot.botevents.SendNoticeEvent;
import com.alta189.chavabot.events.userevents.NickChangeEvent;

public class ChavaBot {
	private SimplePircBot bot = new SimplePircBot(this);
	private List<Channel> channels = new ArrayList<Channel>();
	private String host;
	private int port = 0;

	public void connect() throws NickAlreadyInUseException, IOException, IrcException {
		if (host != null && port != 0 && bot.getNick() != null && bot.getLogin() != null) {
			bot.connect(host, port);
		}
	}

	public void requestChavaUser(String nick) {
		bot.sendWhois(nick);
	}

	public void setVerbose(boolean verbose) {
		bot.setVerbose(verbose);
	}

	public String getNick() {
		return bot.getNick();
	}

	public void setNick(String nick) {
		NickChangeEvent event = NickChangeEvent.getInstance(bot.getNick(), nick);
		ChavaManager.getPluginManager().callEvent(event);
		if (!event.isCancelled()) {
			if (bot.isConnected()) {
				bot.changeNick(event.getNewNick());
			} else {
				bot.setName(event.getNewNick());
			}
		}
	}

	public String getLogin() {
		return bot.getLogin();
	}

	public void setLogin(String login) {
		if (!bot.isConnected())
			bot.setLogin(login);
	}

	public Channel getChannel(String channel) {
		for (Channel chan : channels) {
			if (chan.equals(channel))
				return chan;
		}
		return null;
	}

	public void updateChannel(String channel) {
		channels.remove(channel);
		Channel chan = new Channel(channel);
		chan.addUsers(bot.getUsers(channel));
		chan.setMotd(bot.getMotd(channel));
		channels.add(chan);
	}

	public List<Channel> getChannels() {
		return channels;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public void sendMessage(String target, String message) {
		SendMessageEvent event = SendMessageEvent.getInstance(message, target);
		if (!event.isCancelled()) {
			bot.sendMessage(event.getTarget(), event.getMessage());
		}
	}
	
	public void sendAction(String target, String message) {
		SendMessageEvent event = SendMessageEvent.getInstance(message, target);
		if (!event.isCancelled()) {
			bot.sendMessage(event.getTarget(), event.getMessage());
		}
	}
	
	public void sendNotice(String target, String notice) {
		SendNoticeEvent event = SendNoticeEvent.getInstance(notice, target);
		if (!event.isCancelled()) {
			bot.sendNotice(event.getTarget(), event.getNotice());
		}
	}

}
