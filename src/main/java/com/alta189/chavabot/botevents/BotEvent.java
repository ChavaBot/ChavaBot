package com.alta189.chavabot.botevents;

import com.alta189.chavabot.events.Event;

public abstract class BotEvent <TEvent extends BotEvent<TEvent>> extends Event<TEvent> {

}
