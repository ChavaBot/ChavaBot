package com.alta189.chavabot.events.userevents;

import com.alta189.chavabot.events.Event;

public abstract class UserEvent <TEvent extends UserEvent<TEvent>> extends Event<TEvent> {

}
