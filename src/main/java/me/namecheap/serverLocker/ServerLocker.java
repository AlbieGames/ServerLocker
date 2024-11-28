package me.namecheap.serverLocker;

import me.namecheap.serverLocker.Listeners.MCListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class ServerLocker extends JavaPlugin {

    public static Plugin plugin;
    public static Config config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        config = new Config();
        config.load();
        Bukkit.getPluginManager().registerEvents(new MCListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
