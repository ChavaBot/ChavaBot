package com.alta189.chavabot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.exception.IrcException;
import org.pircbotx.exception.NickAlreadyInUseException;

import com.alta189.chavabot.event.botevents.BotJoinEvent;
import com.alta189.chavabot.event.botevents.BotKickEvent;
import com.alta189.chavabot.event.botevents.BotPartEvent;
import com.alta189.chavabot.event.botevents.ChangeNickEvent;
import com.alta189.chavabot.event.botevents.SendActionEvent;
import com.alta189.chavabot.event.botevents.SendMessageEvent;
import com.alta189.chavabot.event.botevents.SendNoticeEvent;
import com.alta189.chavabot.event.botevents.SendPrivateActionEvent;
import com.alta189.chavabot.event.botevents.SendPrivateMessageEvent;
import com.alta189.chavabot.event.botevents.SendPrivateNoticeEvent;
import com.alta189.chavabot.event.botevents.SendRawLineEvent;
import com.alta189.chavabot.event.botevents.SendUnknownActionEvent;
import com.alta189.chavabot.event.botevents.SendUnknownMessageEvent;
import com.alta189.chavabot.event.botevents.SendUnknownNoticeEvent;

public class ChavaBot {
	private PircBotX bot = new PircBotX();
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
		ChangeNickEvent event = new ChangeNickEvent(bot.getNick(), nick);
		ChavaManager.getEventManager().callEvent(event);
		if (!event.isCancelled()) {
			if (bot.isConnected()) {
				bot.changeNick(event.getNewNick());
			} else {
				bot.setName(event.getNewNick());
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
			bot.setLogin(login);
	}

	public Set<Channel> getChannels() {
		return bot.getChannels();
	}

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
	 * Sends a raw line. If immediately is true, then it sends it immediately
	 * @param raw
	 * @param immediately
	 */
	public void sendRawLine(String raw, boolean immediately) {
		SendRawLineEvent event = new SendRawLineEvent(raw, immediately);
		ChavaManager.getEventManager().callEvent(event);
		if (!event.isCancelled()) {
			if (event.isImmediately()) {
				bot.sendRawLineNow(event.getRawLine());
			} else {
				bot.sendRawLineNow(event.getRawLine());
			}
		}
	}

	/**
	 * Sends the message to the target. The target can be a channel or a user
	 * @param target
	 * @param message
	 */
	public void sendMessage(String target, String message) {
		Channel chan = bot.getChannel(target);
		if (chan != null) {
			sendMessage(chan, message);
			return;
		}

		User user = bot.getUser(target);
		if (user != null) {
			sendPrivateMessage(user, message);
			return;
		}

		SendUnknownMessageEvent event = new SendUnknownMessageEvent(target, message);
		if (!event.isCancelled()) {
			bot.sendMessage(event.getTarget(), event.getMessage());
		}
	}

	public void sendMessage(Channel channel, String message) {
		SendMessageEvent event = new SendMessageEvent(channel, message);
		if (!event.isCancelled()) {
			bot.sendMessage(event.getChannel(), event.getMessage());
		}
	}

	public void sendPrivateMessage(User user, String message) {
		SendPrivateMessageEvent event = new SendPrivateMessageEvent(user, message);
		if (!event.isCancelled()) {
			bot.sendMessage(event.getUser(), event.getMessage());
		}
	}

	/**
	 * Sends an action to the target. The target can be a channel or a user
	 * @param target
	 * @param action
	 */
	public void sendAction(String target, String action) {
		Channel chan = bot.getChannel(target);
		if (chan != null) {
			sendAction(chan, action);
			return;
		}

		User user = bot.getUser(target);
		if (user != null) {
			sendPrivateAction(user, action);
			return;
		}

		SendUnknownActionEvent event = new SendUnknownActionEvent(target, action);
		if (!event.isCancelled()) {
			bot.sendAction(event.getTarget(), event.getAction());
		}
	}

	public void sendAction(Channel channel, String action) {
		SendActionEvent event = new SendActionEvent(channel, action);
		if (!event.isCancelled()) {
			bot.sendAction(event.getChannel(), event.getAction());
		}
	}

	public void sendPrivateAction(User user, String action) {
		SendPrivateActionEvent event = new SendPrivateActionEvent(user, action);
		if (!event.isCancelled()) {
			bot.sendAction(event.getUser(), event.getAction());
		}
	}

	/**
	 * Sends an notice to the target. The target can be a channel or a user
	 * @param target
	 * @param message
	 */
	public void sendNotice(String target, String notice) {
		Channel chan = bot.getChannel(target);
		if (chan != null) {
			sendNotice(chan, notice);
			return;
		}

		User user = bot.getUser(target);
		if (user != null) {
			sendPrivateNotice(user, notice);
			return;
		}

		SendUnknownNoticeEvent event = new SendUnknownNoticeEvent(target, notice);
		if (!event.isCancelled()) {
			bot.sendNotice(event.getTarget(), event.getNotice());
		}
	}

	public void sendNotice(Channel channel, String notice) {
		SendNoticeEvent event = new SendNoticeEvent(channel, notice);
		if (!event.isCancelled()) {
			bot.sendNotice(event.getChannel(), event.getNotice());
		}
	}

	public void sendPrivateNotice(User user, String notice) {
		SendPrivateNoticeEvent event = new SendPrivateNoticeEvent(user, notice);
		if (!event.isCancelled()) {
			bot.sendNotice(event.getUser(), event.getNotice());
		}
	}

	/**
	 * Kicks a user from a channel.
	 * @param channel
	 * @param user
	 */
	public void kick(Channel channel, User user) {
		kick(channel, user, null);
	}

	/**
	 * Kicks a user from a channel with a reason
	 * @param channel
	 * @param user
	 * @param reason
	 */
	public void kick(Channel channel, User user, String reason) {
		BotKickEvent event = new BotKickEvent(channel, user, reason);
		if (!event.isCancelled()) {
			if (event.getReason() != null) {
				bot.kick(channel, user, reason);
			} else {
				bot.kick(channel, user, reason);
			}
		}
	}

	/**
	 * Joins a channel
	 * @param channel
	 */
	public void joinChannel(String channel) {
		BotJoinEvent event = new BotJoinEvent(channel);
		if (!event.isCancelled()) {
			bot.joinChannel(channel, event.getChannel());
		}
	}

	/**
	 * Leaves a channel
	 * @param channel
	 */
	public void partChannel(Channel channel) {
		partChannel(channel, null);
	}

	/**
	 * Leaves a channel with a reason
	 * @param channel
	 * @param reason
	 */
	public void partChannel(Channel channel, String reason) {
		BotPartEvent event = new BotPartEvent(channel, reason);
		if (!event.isCancelled()) {
			if (event.getReason() != null) {
				bot.partChannel(event.getChannel(), event.getReason());
			} else {
				bot.partChannel(channel);
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

	public User getUser(String nick) {
		return bot.getUser(nick);
	}

	public Channel getChannel(String name) {
		return bot.getChannel(name);
	}

	public PircBotX getBot() {
		return bot;
	}

}
