package com.mango.bot.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventListener extends ListenerAdapter {
    static int nastyCount = 1;
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (message.equals("!nasty")){
            String messageToSend = "So nasty, bro. \n \t Nastiness level is now " + nastyCount + ".";
            event.getChannel().sendMessage(messageToSend).queue();
            nastyCount++;
        }
    }

}
