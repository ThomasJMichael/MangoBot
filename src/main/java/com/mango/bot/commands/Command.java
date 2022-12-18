package com.mango.bot.commands;

import com.mango.bot.MangoBot;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a general slash command with properties.
 * Based of implementation by TechnoVision.
 */
public abstract class Command {
    public MangoBot mb; //Mango Bot instance
    public String name; //Name of command
    public String description; //Description of command
    public Category category; //Category command belongs to
    public List<OptionData> args; //Stores arguments for the command
    public List<SubcommandData> subCommands; //Stores sub commands.
    public Permission permission; //Permission user needs to execute this command
    public Permission botPermission; //Permission bot needs to execute this command

    public Command(MangoBot mb){
        this.mb = mb;
        this.args = new ArrayList<>();
        this.subCommands = new ArrayList<>();
    }

    public abstract void execute(SlashCommandInteractionEvent event);

}
