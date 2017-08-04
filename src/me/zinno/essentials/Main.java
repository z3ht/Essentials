package me.zinno.essentials;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.zinno.essentials.commands.AdminList;
import me.zinno.essentials.commands.CustomWarps;
import me.zinno.essentials.commands.warps.Spawn;
import me.zinno.essentials.commands.warps.Wild;
import me.zinno.essentials.commands.warps.set.SetSpawn;
import me.zinno.essentials.event.player.PlayerJoin;

public class Main extends JavaPlugin {

	public void onEnable() {
		registerConfig();
		registerCommands();
		registerEvent();
	}

	public void onDisable() {
	}

	public void registerEvent() {
		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new PlayerJoin(this), this);
	}

	public void registerConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	public void registerCommands() {
		getCommand("wild").setExecutor(new Wild());
		getCommand("spawn").setExecutor(new Spawn(this));
		getCommand("admins").setExecutor(new AdminList(this));
		getCommand("setspawn").setExecutor(new SetSpawn(this));
		getCommand("setwarp").setExecutor(new CustomWarps(this));
		getCommand("delwarp").setExecutor(new CustomWarps(this));
		getCommand("warp").setExecutor(new CustomWarps(this));
	}
}
