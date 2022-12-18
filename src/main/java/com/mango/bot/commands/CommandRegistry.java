package com.mango.bot.commands;

import com.mango.bot.MangoBot;
import com.mango.bot.commands.fun.GoogleCommand;
import com.mango.bot.commands.fun.Nasty;
import com.mango.bot.util.EmbedUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.*;

/**
 * Registers, listens, and executes commands.
 * Based on implementation by TechnoVision.
 */
public class CommandRegistry  extends ListenerAdapter {
    public static final List<Command> commands = new ArrayList<>(); //List of commands in order registered.
    public static final Map<String, Command> commandsMap = new HashMap<>(); //Map of string command name to command object.

    public CommandRegistry(MangoBot mb){
        mapCommand(
                //Add commands here format (new <command object name>(bot),
                //Fun
                new GoogleCommand(mb),
                new Nasty(mb)
        );
    }

    /**
     * Add command to the static list and map.
     * @param cmds list of command objects.
     */
    private void mapCommand(Command ...cmds){
        for (Command cmd : cmds){
            commandsMap.put(cmd.name, cmd);
            commands.add(cmd);
        }
    }

    /**
     * Creates a list of CommandData for all commands
     * @return list of command data used for registration
     */
    public static  List<CommandData> unpackCommandData(){
        List<CommandData> commandData = new ArrayList<>();
        for (Command command : commands){
            SlashCommandData slashCommand = Commands.slash(command.name, command.description).addOptions(command.args);
            if (command.permission != null){
                slashCommand.setDefaultPermissions(DefaultMemberPermissions.enabledFor(command.permission));
            }
            if (!command.subCommands.isEmpty()){
                slashCommand.addSubcommands(command.subCommands);
            }
            commandData.add(slashCommand);
        }
        return commandData;
    }

    /**
     * Runs whenever a slash command is ran in discord.
     * @param event Slash command run.
     */
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        Command cmd = commandsMap.get(event.getName());
        if (cmd != null){
            Role botRole = Objects.requireNonNull(event.getGuild()).getBotRole();
            if (cmd.botPermission != null){
                if (!botRole.hasPermission(cmd.botPermission) && !botRole.hasPermission(Permission.ADMINISTRATOR)){
                    String permissionErrorMessage = "I need the '" + cmd.botPermission.getName() + "' permission to execute the command.";
                    event.replyEmbeds(EmbedUtils.createError(permissionErrorMessage)).setEphemeral(true).queue();
                }
            }
            cmd.execute(event);
        }
    }

    /**
     * Register slash commands for guild
     * Can handle different guilds in future with database.
     * @param event executes when a guild is ready.
     */
    @Override
    public void onGuildReady(GuildReadyEvent event) {
        event.getGuild().updateCommands().addCommands(unpackCommandData()).queue();
    }
}
