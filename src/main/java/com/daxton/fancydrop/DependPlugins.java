package com.daxton.fancydrop;


import com.daxton.fancydrop.listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import static com.daxton.fancydrop.config.FileConfig.languageConfig;

public class DependPlugins {

    public static boolean depend(){

        FancyDrop fancyDrop = FancyDrop.fancyDrop;

        if (Bukkit.getServer().getPluginManager().getPlugin("FancyCore") != null && Bukkit.getPluginManager().isPluginEnabled("FancyCore")){
            fancyDrop.getLogger().info(ChatColor.GREEN+"Loaded FancyCore");
        }else {
            languageConfig.getStringList("LogMessage.NotLoadFancyCore").forEach(message->{
                fancyDrop.getLogger().severe(message);
            });
            return false;
        }
        if (Bukkit.getServer().getPluginManager().getPlugin("FancyMobs") != null) {
            fancyDrop.getLogger().info(ChatColor.GREEN+"Loaded FancyMobs");
            Bukkit.getPluginManager().registerEvents(new PlayerListener(), fancyDrop);
        }else {
            languageConfig.getStringList("LogMessage.NotLoadFancyMobs").forEach(message->{
                fancyDrop.getLogger().severe(message);
            });
            return false;
        }
        return true;
    }

}
