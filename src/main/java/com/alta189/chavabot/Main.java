package com.alta189.chavabot;

import java.io.IOException;

import org.pircbotx.exception.IrcException;
import org.pircbotx.exception.NickAlreadyInUseException;

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
