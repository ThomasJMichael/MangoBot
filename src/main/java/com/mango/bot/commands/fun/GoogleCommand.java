package com.mango.bot.commands.fun;

import com.mango.bot.MangoBot;
import com.mango.bot.commands.Category;
import com.mango.bot.commands.Command;
import com.mango.bot.util.EmbedUtils;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class GoogleCommand extends Command {
    public GoogleCommand(MangoBot mb){
        super(mb);
        this.name = "google";
        this.description = "Google a question";
        this.category = Category.FUN;
        this.args.add(new OptionData(OptionType.STRING, "question", "The question to ask google", true));
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String question = Objects.requireNonNull(event.getOption("question")).getAsString();
        if (question.length() > 250){
            event.replyEmbeds(EmbedUtils.createError("Google does not like it when questions are longer than 250 characters!")).queue();
            return;
        }
        String googleQuestion = URLEncoder.encode(question, StandardCharsets.UTF_8);
        event.replyEmbeds(EmbedUtils.createSuccess("https://lmgt.org/?q=" + googleQuestion)).queue();
    }
}
