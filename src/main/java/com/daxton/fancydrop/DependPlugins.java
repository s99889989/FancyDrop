package com.daxton.fancydrop;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class DependPlugins {

    public static boolean depend(){

        FancyDrop fancyDrop = FancyDrop.fancyDrop;

        if (Bukkit.getServer().getPluginManager().getPlugin("FancyCore") != null && Bukkit.getPluginManager().isPluginEnabled("FancyCore")){
            fancyDrop.getLogger().info(ChatColor.GREEN+"Loaded FancyCore");
        }else {
            fancyDrop.getLogger().severe("*** FancyCore is not installed or not enabled. ***");
            fancyDrop.getLogger().severe("*** FancyItemsy will be disabled. ***");
            fancyDrop.getLogger().severe("*** FancyCore未安裝或未啟用。 ***");
            fancyDrop.getLogger().severe("*** FancyItems將被卸載。 ***");
            return false;
        }

        return true;
    }

}
