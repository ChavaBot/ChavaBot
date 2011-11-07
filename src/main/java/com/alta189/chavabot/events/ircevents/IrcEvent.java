package com.alta189.chavabot.events.ircevents;

import com.alta189.chavabot.events.Event;

public abstract class IrcEvent <TEvent extends IrcEvent<TEvent>> extends Event<TEvent> {

}
