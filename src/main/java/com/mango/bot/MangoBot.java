package com.mango.bot;

import com.mango.bot.commands.CommandRegistry;
import com.mango.bot.listeners.EventListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class MangoBot {

    private final ShardManager shardManger;
    private final Dotenv config;


    /**
     * Loads env variables and builds the bot shard manager.
     * @throws LoginException occurs when the bot token is invalid
     */
    public MangoBot() throws LoginException {

        //Load environment variables.
        config = Dotenv.configure().directory("./.env").load();
        String token = config.get("TOKEN");
        String botActivity = config.get("ACTIVITY");

        //Setup shard manager.
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.listening(botActivity));
        builder.addEventListeners(new CommandRegistry(this));
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_MEMBERS);
        shardManger = builder.build();

        //Register listeners
        shardManger.addEventListener(new EventListener());

    }

    /**
     * Initialize MangoBot
     * @param args Args ignored.
     */
    public static void main(String[] args) {
        try {
            MangoBot mangoBot = new MangoBot();
        } catch (LoginException e){
            System.out.println("ERROR: Provided bot token is invalid.");
        }

    }
}