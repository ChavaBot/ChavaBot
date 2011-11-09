package com.alta189.chavabot;

import java.util.HashMap;
import java.util.Map;

import org.jibble.pircbot.PircBot;

import com.alta189.chavabot.botevents.PrivateMessageEvent;
import com.alta189.chavabot.events.channelevents.JoinEvent;
import com.alta189.chavabot.events.channelevents.MessageEvent;
import com.alta189.chavabot.events.channelevents.PartEvent;
import com.alta189.chavabot.events.ircevents.ConnectEvent;
import com.alta189.chavabot.events.ircevents.DisconnectEvent;
import com.alta189.chavabot.events.userevents.ActionEvent;
import com.alta189.chavabot.events.userevents.ChavaUserEvent;
import com.alta189.chavabot.events.userevents.NoticeEvent;
import com.alta189.chavabot.events.userevents.QuitEvent;

public class SimplePircBot extends PircBot {
	private final ChavaBot parent;
	private WhoisResult whoisResult;

	@Override
	protected void onNotice(String sourceNick, String sourceLogin, String sourceHostname, String target, String notice) {
		ChavaManager.getPluginManager().callEvent(NoticeEvent.getInstance(new ChavaUser(sourceNick, sourceLogin, sourceHostname, null), target, notice));
	}

	private boolean waitWhois = false;
	private Map<String, String> motds = new HashMap<String, String>();

	protected String getMotd(String channel) {
		return motds.get(channel);
	}

	protected SimplePircBot(ChavaBot parent) {
		this.parent = parent;
	}

	@Override
	protected void onConnect() {
		ChavaManager.getPluginManager().callEvent(ConnectEvent.getInstance());
	}

	@Override
	protected void onPrivateMessage(String sender, String login, String hostname, String message) {
		ChavaManager.getPluginManager().callEvent(PrivateMessageEvent.getInstance(message, new ChavaUser(sender, login, hostname, null)));
	}

	@Override
	protected void onJoin(String channel, String sender, String login, String hostname) {
		parent.updateChannel(channel);
		ChavaManager.getPluginManager().callEvent(JoinEvent.getInstance(new ChavaUser(sender, login, hostname, null), parent.getChannel(channel)));
	}

	public synchronized void sendWhois(String nick) {
		while (waitWhois) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
		waitWhois = true;
		whoisResult = new WhoisResult(nick);
		this.sendRawLineViaQueue("WHOIS " + nick);
	}

	@Override
	protected void onDisconnect() {
		ChavaManager.getPluginManager().callEvent(DisconnectEvent.getInstance());
	}

	@Override
	protected void onTopic(String channel, String topic, String setBy, long date, boolean changed) {
		motds.remove(channel);
		motds.put(channel, topic);
		parent.updateChannel(channel);
	}

	@Override
	protected void onAction(String sender, String login, String hostname, String target, String action) {
		ChavaManager.getPluginManager().callEvent(ActionEvent.getInstance(new ChavaUser(sender, login, hostname, null), target, action));
	}

	@Override
	protected void onMessage(String channel, String sender, String login, String hostname, String message) {
		ChavaManager.getPluginManager().callEvent(MessageEvent.getInstance(new ChavaUser(sender, login, hostname, null), parent.getChannel(channel), message));
	}

	@Override
	protected void onQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason) {
		ChavaManager.getPluginManager().callEvent(QuitEvent.getInstance(new ChavaUser(sourceNick, sourceLogin, sourceHostname, null), reason));
	}

	@Override
	protected void onPart(String channel, String sender, String login, String hostname) {
		parent.updateChannel(channel);
		ChavaManager.getPluginManager().callEvent(PartEvent.getInstance(new ChavaUser(sender, login, hostname, null), parent.getChannel(channel)));
	}

	@Override
	protected void onServerResponse(int code, String response) {
		switch (code) {
			case RPL_WHOISUSER:
				whoisResult.put(code, response);
				break;
			case RPL_WHOISCHANNELS:
				whoisResult.put(code, response);
				break;
			case RPL_ENDOFWHOIS:
				whoisResult.put(code, response);
				ChavaUser result = whoisResult.build();
				whoisResult = null;
				waitWhois = false;
				ChavaManager.getPluginManager().callEvent(ChavaUserEvent.getInstance(result));
				break;
		}
	}

}
