package com.alta189.chavabot.events.channelevents;

import com.alta189.chavabot.Channel;
import com.alta189.chavabot.User;
import com.alta189.chavabot.events.Event;

public abstract class ChannelEvent <TEvent extends ChannelEvent<TEvent>> extends Event<TEvent> {
	protected Channel channel;
	protected User user;
	
	public User getUser() {
		return user;
	}
	
	/**
	 * @return the channel
	 */
	public Channel getChannel() {
		return channel;
	}

}
