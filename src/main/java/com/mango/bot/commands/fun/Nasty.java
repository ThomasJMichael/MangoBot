package com.mango.bot.commands.fun;

import com.mango.bot.MangoBot;
import com.mango.bot.commands.Category;
import com.mango.bot.commands.Command;
import com.mango.bot.util.EmbedUtils;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Nasty extends Command {
    public Nasty(MangoBot mb) {
        super(mb);
        this.name = "nasty";
        this.description = "Get nasty!";
        this.category = Category.FUN;
    }

    public static int nastyCount = 1;

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.replyEmbeds(EmbedUtils.createDefault("\uD83D\uDEAE So nasty, bro \n \t Nastiness level is now " + nastyCount)).queue();
        nastyCount++;
    }
}
