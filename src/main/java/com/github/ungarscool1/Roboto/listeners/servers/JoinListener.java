package com.github.ungarscool1.Roboto.listeners.servers;

import com.github.ungarscool1.Roboto.Main;
import com.github.ungarscool1.Roboto.ServerLanguage;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.event.server.ServerJoinEvent;
import org.javacord.api.listener.server.ServerJoinListener;

import java.util.Locale;

public class JoinListener implements ServerJoinListener {
    @Override
    public void onServerJoin(ServerJoinEvent event) {
        DiscordApi api = event.getApi();
        ServerLanguage serverLanguage = new ServerLanguage();
        Main.locByServ.put(event.getServer(), new Locale("en", "US"));
        serverLanguage.addServer(event.getServer());
        api.updateActivity(ActivityType.LISTENING, api.getServers().size() + " servers");
        Main.dbl.setStats(api.getCurrentShard(), api.getTotalShards(), api.getServers().size());
    }
}
