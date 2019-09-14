package com.github.ungarscool1.Roboto;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.util.logging.ExceptionLogger;

import com.github.ungarscool1.Roboto.listeners.ReacListener;
import com.github.ungarscool1.Roboto.listeners.commands.GameCommand;
import com.github.ungarscool1.Roboto.listeners.commands.UtilsCommand;
import com.github.ungarscool1.Roboto.listeners.commands.VoteCommand;

public class Main {
    public static void main(String[] args) {
        new DiscordApiBuilder()
        	.setToken(args[0])
        	.setRecommendedTotalShards()
        	.join()
        	.loginAllShards()
        	.forEach(shardFuture -> shardFuture.thenAcceptAsync(Main::onShardLogin).exceptionally(ExceptionLogger.get()));
    }
    
    private static void onShardLogin(DiscordApi api) {
        System.out.println("Shard " + api.getCurrentShard() + " logged in!");
        
        api.updateActivity(ActivityType.LISTENING, api.getServers().size() + " servers");
        
        api.addMessageCreateListener(new VoteCommand());
        api.addMessageCreateListener(new GameCommand(api));
        api.addMessageCreateListener(new UtilsCommand(api));
        
        api.addReactionAddListener(new ReacListener());
    }

}