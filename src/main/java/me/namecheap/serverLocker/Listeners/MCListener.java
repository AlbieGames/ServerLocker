package me.namecheap.serverLocker.Listeners;

import me.namecheap.serverLocker.ServerLocker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.List;

public class MCListener implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        YamlConfiguration config = ServerLocker.config.getConfig();
        boolean enabled = config.getBoolean("options.enabled");
        if(!enabled) return;
        List<String> players = config.getStringList("options.players");
        String kick_message = config.getString("messages.kick-message");
        boolean found = false;
        if(players.isEmpty()|| kick_message == null){Bukkit.getLogger().severe("Failed to kick player, Invalid configuration."); return;}
        for(String p : players){
            if(Bukkit.getPlayer(p) != null || player.getName().equals(p)){
                found = true;
            }
        }
        if(!found){
            Bukkit.getLogger().info(String.format("Kicking player %s due to server being locked.", player.getName()));
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.translateAlternateColorCodes('&', kick_message));
        }
    }

}
