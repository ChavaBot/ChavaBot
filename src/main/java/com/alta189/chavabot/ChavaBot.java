package com.alta189.chavabot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;

import com.alta189.chavabot.events.botevents.JoinEvent;
import com.alta189.chavabot.events.botevents.KickEvent;
import com.alta189.chavabot.events.botevents.PartEvent;
import com.alta189.chavabot.events.botevents.SendMessageEvent;
import com.alta189.chavabot.events.botevents.SendNoticeEvent;
import com.alta189.chavabot.events.botevents.SendRawLineEvent;
import com.alta189.chavabot.events.userevents.NickChangeEvent;

public class ChavaBot {
	private SimplePircBot bot = new SimplePircBot(this);
	//private List<Channel> channels = new ArrayList<Channel>();
	private String host;
	private int port = 0;
	private List<String> ajChannels = new ArrayList<String>();
	private String pass;
	
	/**
	 * Connects to the host
	 * @throws NickAlreadyInUseException
	 * @throws IOException
	 * @throws IrcException
	 */
	public void connect() throws NickAlreadyInUseException, IOException, IrcException {
		if (host != null && port != 0 && bot.getNick() != null && bot.getLogin() != null) {
			if (pass != null) {
				bot.connect(host, port, pass);
			} else {
				bot.connect(host, port);	
			}
		}
	}

	protected List<String> getAjChannels() {
		return ajChannels;
	}

	protected void setAjChannels(List<String> ajChannels) {
		this.ajChannels = ajChannels;
	}
	
	/**
	 * Requests a ChavaUser from the server
	 * NOTE: Use at your own risk, this is untested and probably will fail
	 * @param nick
	 */
	public void requestChavaUser(String nick) {
		bot.sendWhois(nick);
	}
	
	/**
	 * Sets verbose
	 * @param verbose
	 */
	public void setVerbose(boolean verbose) {
		bot.setVerbose(verbose);
	}

	/**
	 * Gets the bot's nick
	 * @return nick
	 */
	public String getNick() {
		return bot.getNick();
	}
	
	/**
	 * Sets the nick of a bot
	 * @param nick
	 */
	public void setNick(String nick) {
		NickChangeEvent event = NickChangeEvent.getInstance(bot.getNick(), nick);
		ChavaManager.getPluginManager().callEvent(event);
		if (!event.isCancelled()) {
			if (bot.isConnected()) {
				bot.changeNick(event.getNewNick());
			} else {
				bot.publicSetName(event.getNewNick());
			}
		}
	}
	
	/**
	 * Returns the login of the bot
	 * @return
	 */
	public String getLogin() {
		return bot.getLogin();
	}
	
	/**
	 * Sets the login of the bot
	 * @param login
	 */
	public void setLogin(String login) {
		if (!bot.isConnected())
			bot.publicSetLogin(login);
	}
	
	/**
	 * Gets the channel requested
	 * @param channel
	 * @return Channel channel
	 *//*
	public Channel getChannel(String channel) {
		for (Channel chan : channels) {
			if (chan.equals(channel))
				return chan;
		}
		return null;
	}
	
	*//**
	 * Forces an update of a channel
	 * @param channel
	 *//*
	public void updateChannel(String channel) {
		channels.remove(channel);
		Channel chan = new Channel(channel);
		chan.addUsers(bot.getUsers(channel));
		chan.setTopic(bot.getMotd(channel));
		channels.add(chan);
	}*/
	
	/**
	 * Returns a List of Channels
	 * @return channels
	 */
	/*public List<Channel> getChannels() {
		return channels;
	}*/
	
	/**
	 * Gets the host of the bot
	 * @return host
	 */
	public String getHost() {
		return host;
	}
	
	/**
	 * Sets the host of the bot
	 * @param host
	 */
	public void setHost(String host) {
		this.host = host;
	}
	
	/**
	 * Gets the port of the bot
	 * @return port
	 */
	public int getPort() {
		return port;
	}
	
	/**
	 * Sets the port of the bot
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}
	
	/**
	 * Sends a raw line via the output queue
	 * @param raw
	 */
	public void sendRawLineViaQueue(String raw) {
		SendRawLineEvent event = SendRawLineEvent.getInstance(raw);
		if (!event.isCancelled()) {
			bot.sendRawLineViaQueue(raw);
		}
	}
	
	/**
	 * Sends a raw line bypassing the queue
	 * @param raw
	 */
	public void sendRawLine(String raw) {
			bot.sendRawLine(raw);
	}
	
	/**
	 * Sends the message to the target. The target can be a channel or a user
	 * @param target
	 * @param message
	 */
	public void sendMessage(String target, String message) {
		SendMessageEvent event = SendMessageEvent.getInstance(message, target);
		if (!event.isCancelled()) {
			bot.sendMessage(event.getTarget(), event.getMessage());
		}
	}
	
	/**
	 * Sends an action to the target. The target can be a channel or a user
	 * @param target
	 * @param message
	 */
	public void sendAction(String target, String message) {
		SendMessageEvent event = SendMessageEvent.getInstance(message, target);
		if (!event.isCancelled()) {
			bot.sendAction(event.getTarget(), event.getMessage());
		}
	}
	
	/**
	 * Sends an notice to the target. The target can be a channel or a user
	 * @param target
	 * @param message
	 */
	public void sendNotice(String target, String notice) {
		SendNoticeEvent event = SendNoticeEvent.getInstance(notice, target);
		if (!event.isCancelled()) {
			bot.sendNotice(event.getTarget(), event.getNotice());
		}
	}
	
	/**
	 * Kicks a user from a channel.
	 * @param channel
	 * @param nick
	 */
	public void kick(String channel, String nick) {
		kick(channel, nick, null);
	}
	
	/**
	 * Kicks a user from a channel with a reason
	 * @param channel
	 * @param nick
	 * @param reason
	 */
	public void kick(String channel, String nick, String reason) {
		KickEvent event = KickEvent.getInstance(channel, nick, reason);
		if (!event.isCancelled()) {
			if (event.getReason() != null) {
				bot.kick(event.getChannel(), event.getRecipient(), event.getReason());
			} else {
				bot.kick(event.getChannel(), event.getRecipient());
			}
		}
	}
	
	/**
	 * Joins a channel
	 * @param channel
	 */
	public void joinChannel(String channel) {
		JoinEvent event = JoinEvent.getInstance(channel);
		if (!event.isCancelled()) {
			bot.joinChannel(event.getChannel());
		}
	}
	
	/**
	 * Leaves a channel
	 * @param channel
	 */
	public void partChannel(String channel) {
		partChannel(channel, null);
	}
	
	/**
	 * Leaves a channel with a reason
	 * @param channel
	 * @param reason
	 */
	public void partChannel(String channel, String reason) {
		PartEvent event = PartEvent.getInstance(channel, reason);
		if (!event.isCancelled()) {
			if (event.getReason() != null) {
				bot.partChannel(event.getChannel(),event.getReason());
			} else {
				bot.partChannel(event.getChannel());
			}
		}
	}
	
	/**
	 * Disconnects from the host and exits
	 */
	public void disconnect() {
		bot.quitServer();
		ChavaManager.getPluginManager().disablePlugins();
		System.exit(1);
	}
	
	/**
	 * Disconnects from the host with a reason and exits
	 * @param reason
	 */
	public void disconnect(String reason) {
		bot.quitServer(reason);
		ChavaManager.getPluginManager().disablePlugins();
		System.exit(1);
	}
	
	/**
	 * Disconnects and reconnects to the host
	 * @throws NickAlreadyInUseException
	 * @throws IOException
	 * @throws IrcException
	 */
	public void reconnect() throws NickAlreadyInUseException, IOException, IrcException {
		bot.quitServer();
		bot.reconnect();
	}
	
	/**
	 * Disconnects with a reason and reconnects to the host
	 * @param reason
	 * @throws NickAlreadyInUseException
	 * @throws IOException
	 * @throws IrcException
	 */
	public void reconnect(String reason) throws NickAlreadyInUseException, IOException, IrcException {
		bot.quitServer(reason);
		bot.reconnect();		
	}

	/**
	 * @return pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass 
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

}
