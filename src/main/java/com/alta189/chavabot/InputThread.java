package com.alta189.chavabot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class InputThread extends Thread {
    
    private ChavaBot bot = null;
    private Socket socket = null;
    private BufferedReader br = null;
    private BufferedWriter bw = null;
    private boolean isConnected = true;
    private boolean disposed = false;
    
    public static final int MAX_LINE_LENGTH = 512;
    
    InputThread(ChavaBot bot, Socket socket, BufferedReader breader, BufferedWriter bwriter) {
        this.bot = bot;
       this. socket = socket;
        br = breader;
        bw = bwriter;
        this.setName(this.getClass() + "-Thread");
    }
    
    void sendRawLine(String line) {
        OutputThread.sendRawLine(bot, bw, line);
    }
    
    boolean isConnected() {
        return isConnected;
    }
        
    public void run() {
        try {
            boolean running = true;
            while (running) {
                try {
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        try {
                            bot.handleLine(line);
                        }
                        catch (Throwable t) {
                            // Stick the whole stack trace into a String so we can output it nicely.
                            StringWriter sw = new StringWriter();
                            PrintWriter pw = new PrintWriter(sw);
                            t.printStackTrace(pw);
                            pw.flush();
                            StringTokenizer tokenizer = new StringTokenizer(sw.toString(), "\r\n");
                            synchronized (bot) {
                                bot.log("### An error has occurred in ChavaBot");
                                bot.log("### however ChavaBot will try to continue to work");
                                bot.log("### ");
                                while (tokenizer.hasMoreTokens()) {
                                    bot.log("### " + tokenizer.nextToken());
                                }
                            }
                        }
                    }
                    if (line == null) {
                        // The server must have disconnected us.
                        running = false;
                    }
                }
                catch (InterruptedIOException iioe) {
                    // This will happen if we haven't received anything from the server for a while.
                    // So we shall send it a ping to check that we are still connected.
                    this.sendRawLine("PING " + (System.currentTimeMillis() / 1000));
                    // Now we go back to listening for stuff from the server...
                }
            }
        }
        catch (Exception e) {
            // Do nothing.
        }
        
        // If we reach this point, then we must have disconnected.
        try {
            socket.close();
        }
        catch (Exception e) {
            // Just assume the socket was already closed.
        }

        if (!disposed) {
            bot.log("*** Disconnected.");        
            isConnected = false;
            // TODO: Throw Disconnect event
        }
        
    }
    
    
    /**
     * Closes the socket without onDisconnect being called subsequently.
     */
    public void dispose () {
        try {
            disposed = true;
            socket.close();
        }
        catch (Exception e) {
            // Do nothing.
        }
    }
    
}

