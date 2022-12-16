package com.mango.bot;

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
    private static final Dotenv config = Dotenv.configure().directory("./.env").load();


    /**
     * Loads env variables and builds the bot shard manager.
     * @throws LoginException occurs when the bot token is invalid
     */
    public MangoBot() throws LoginException {
        //Load environment variables.

        String token = config.get("TOKEN");
        String botActivity = config.get("ACTIVITY");

        //Setup shard manager.
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.listening(botActivity));
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT);
        shardManger = builder.build();


        //Register listeners
        shardManger.addEventListener(new EventListener());

    }

    /**
     * Retrieves the bot shard manager.
     * @return the ShardManager instance for the bot.
     */
    public ShardManager getShardManger(){
        return shardManger;
    }

    /**
     * Retrieves the config file
     * @return the Dotenv config instance.
     */
    public static Dotenv getConfig(){
        return config;
    }

    public static void main(String[] args) {
        try {
            MangoBot mangoBot = new MangoBot();
        } catch (LoginException e){
            System.out.println("ERROR: Provided bot token is invalid.");
        }

    }
}