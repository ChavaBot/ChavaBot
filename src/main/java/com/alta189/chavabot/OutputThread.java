package com.alta189.chavabot;

import java.io.BufferedWriter;

public class OutputThread extends Thread {
    
    private ChavaBot bot = null;
    private Queue outQueue = null;
    
    
    protected OutputThread(ChavaBot bot, Queue outQueue) {
        this.bot = bot;
        this.outQueue = outQueue;
        this.setName(this.getClass() + "-Thread");
    }
    
    static void sendRawLine(ChavaBot bot, BufferedWriter bwriter, String line) {
        if (line.length() > bot.getMaxLineLength() - 2) {
            line = line.substring(0, bot.getMaxLineLength() - 2);
        }
        synchronized(bwriter) {
            try {
                bwriter.write(line + "\r\n");
                bwriter.flush();
                bot.log(">>>" + line);
            }
            catch (Exception e) {
                // Silent response - just lose the line.
            }
        }
    }
    
    public void run() {
        try {
            boolean running = true;
            while (running) {
                // Small delay to prevent spamming of the channel
                Thread.sleep(bot.getMessageDelay());
                
                String line = (String) outQueue.next();
                if (line != null) {
                    bot.sendRawLine(line);
                }
                else {
                    running = false;
                }
            }
        }
        catch (InterruptedException e) {
            // Just let the method return naturally...
        }
    }
    
}
