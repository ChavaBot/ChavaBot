package com.alta189.chavabot;

import java.io.IOException;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;

import com.beust.jcommander.JCommander;

public class Main {

	/**
	 * @param args
	 * @throws IrcException 
	 * @throws IOException 
	 * @throws NickAlreadyInUseException 
	 */
	public static void main(String[] args) throws NickAlreadyInUseException, IOException, IrcException {
		Options options = new Options();
		new JCommander(options, args);
		ChavaManager.getInstance().start(options);
	}

}
