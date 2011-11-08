package com.alta189.chavabot;

import org.jibble.pircbot.PircBot;

import com.alta189.chavabot.events.ircevents.ConnectEvent;

public class SimplePircBot extends PircBot {

	@Override
	protected void onConnect() {
		ChavaManager.getPluginManager().callEvent(ConnectEvent.getInstance());
	}

	
	
}
