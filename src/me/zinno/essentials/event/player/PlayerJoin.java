package me.zinno.essentials.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.zinno.essentials.Main;
import net.md_5.bungee.api.ChatColor;

public class PlayerJoin implements Listener {
	private Main plugin;

	public PlayerJoin(Main pl) {
		plugin = pl;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		String welcomeMessage = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("Welcome Message"));
		Player player = event.getPlayer();
		player.sendMessage(welcomeMessage);
	}
}