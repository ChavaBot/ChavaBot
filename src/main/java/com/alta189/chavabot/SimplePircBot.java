package com.alta189.chavabot;

import org.jibble.pircbot.PircBot;

import com.alta189.chavabot.events.ircevents.ConnectEvent;

public class SimplePircBot extends PircBot {
	private final ChavaBot parent;
	private WhoisResult whoisResult = new WhoisResult();
	private boolean waitWhois = false;
	
	protected SimplePircBot(ChavaBot parent) {
		this.parent = parent;
	}

	@Override
	protected void onConnect() {
		ChavaManager.getPluginManager().callEvent(ConnectEvent.getInstance());
		joinChannel("#chavabot");
	}

	@Override
	protected void onJoin(String channel, String sender, String login, String hostname) {
		if (sender.equalsIgnoreCase(this.getNick())) {
			//for (org.jibble.pircbot.User user : this.getUsers(channel)) {
				
			//}
			sendWhois("alta189");
			sendWhois("dsadsad");
		}
	}
	
	public synchronized void sendWhois(String nick) {
		while (waitWhois) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
		this.sendRawLineViaQueue("WHOIS " + nick);
	}

	@Override
	protected void onServerResponse(int code, String response) {
		switch (code) {
		case RPL_WHOISUSER:
			whoisResult.responces.put(code, response);
		case RPL_WHOISCHANNELS:
			whoisResult.responces.put(code, response);
		case RPL_ENDOFWHOIS:
			whoisResult.responces.put(code, response);
		//case ERR_NOSUCHUSER: 
			
		}
	}
	

	
	
}
