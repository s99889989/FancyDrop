package com.daxton.fancydrop;

import com.daxton.fancydrop.command.MainCommand;
import com.daxton.fancydrop.command.TabCommand;
import com.daxton.fancydrop.listener.PlayerListener;
import com.daxton.fancydrop.task.Start;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class FancyDrop extends JavaPlugin {

	public static FancyDrop fancyDrop;

	@Override
	public void onEnable() {
		fancyDrop = this;
		//前置插件
		if(!DependPlugins.depend()){
			fancyDrop.setEnabled(false);
			fancyDrop.onDisable();
			return;
		}
		//指令
		Objects.requireNonNull(Bukkit.getPluginCommand("fancydrop")).setExecutor(new MainCommand());
		Objects.requireNonNull(Bukkit.getPluginCommand("fancydrop")).setTabCompleter(new TabCommand());
		//開服執行任務
		Start.execute();
		//監聽
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), fancyDrop);
	}

	@Override
	public void onDisable() {

	}
}
