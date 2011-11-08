package com.alta189.chavabot.events.channelevents;

import com.alta189.chavabot.Channel;
import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.Event;

public abstract class ChannelEvent <TEvent extends ChannelEvent<TEvent>> extends Event<TEvent> {
	protected Channel channel;
	protected ChavaUser user;
	
	public ChavaUser getUser() {
		return user;
	}
	
	/**
	 * @return the channel
	 */
	public Channel getChannel() {
		return channel;
	}

}
