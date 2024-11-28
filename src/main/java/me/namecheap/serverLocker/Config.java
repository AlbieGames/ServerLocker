package me.namecheap.serverLocker;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static me.namecheap.serverLocker.ServerLocker.plugin;

public class Config {

    YamlConfiguration config;

    public void load(){
        if(new File(plugin.getDataFolder()+"/config.yml").exists()){
            config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder()+"/config.yml"));
        }else{
            config = createConfig();
        }
    }

    public YamlConfiguration createConfig(){
        try{
            File file = new File(plugin.getDataFolder()+"/config.yml");
            if(!plugin.getDataFolder().exists()){plugin.getDataFolder().mkdir();}
            file.createNewFile();
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
            yamlConfiguration.set("options.enabled", true);
            yamlConfiguration.set("options.players", List.of("ChangeMe"));
            yamlConfiguration.set("messages.kick-message", "&7The server has been locked.");
            yamlConfiguration.save(file);
            return yamlConfiguration;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public YamlConfiguration getConfig(){return this.config;}

}
