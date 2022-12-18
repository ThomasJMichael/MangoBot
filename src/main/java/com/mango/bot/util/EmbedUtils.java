package com.mango.bot.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class EmbedUtils {
    /**
     * Custom Emojis
     */
    public static final String GREEN_TICK = ":white_check_mark:";
    public static final String BLUE_TICK = ":ballot_box_with_check:";
    public static final String RED_X = ":x:";
    public static final String BLUE_X = ":regional_indicator_x:";

    /**
     * Creates simple error embed.
     * @param errorMessage Message to be dispalyed
     * @return Compelted error embed.
     */
    public static MessageEmbed createError(String errorMessage){
        return new EmbedBuilder()
                .setColor(EmbedColor.ERROR.color)
                .setDescription(RED_X + " " + errorMessage)
                .build();
    }

    /**
     * Creates simple default embed
     * @param message Message to embed
     * @return Completed embed.
     */
    public static MessageEmbed createDefault(String message){
        return new EmbedBuilder()
                .setColor(EmbedColor.DEFAULT.color)
                .setDescription(message)
                .build();
    }

    /**
     * Creates simple success embed.
     * @param message Message to be embedded
     * @return Completed embed.
     */
    public static MessageEmbed createSuccess(String message){
        return new EmbedBuilder()
                .setColor(EmbedColor.SUCCESS.color)
                .setDescription(EmbedUtils.GREEN_TICK + "  " + message)
                .build();
    }

    //TODO Json embed messages
}
