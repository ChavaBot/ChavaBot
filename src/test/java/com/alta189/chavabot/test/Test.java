package com.alta189.chavabot.test;

import java.io.IOException;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;

import com.alta189.chavabot.Main;

public class Test {
	public static void main(String[] args) throws NickAlreadyInUseException, IOException, IrcException {
		String newArgs = "-v -h irc.esper.net -c spouty,chava";		
		Main.main(newArgs.split(" "));
	}
}
