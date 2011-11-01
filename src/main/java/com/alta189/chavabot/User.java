package com.alta189.chavabot;

/**
 * @author: alta189
 */
public interface User {

	public String getNick();

	public String getPrefix();

	public boolean isOp(String channel);

	public boolean hasOp(String channel);

	public String getHost();

	public String getFullName();
}
