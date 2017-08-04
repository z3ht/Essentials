package me.zinno.essentials.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.zinno.essentials.Main;
import net.md_5.bungee.api.ChatColor;

public class AdminList implements CommandExecutor {

	private Main plugin;

	public AdminList(Main pl) {
		plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))
			return true;
		Player player = (Player) sender;
		player.sendMessage(ChatColor.AQUA + "The server admins are:");
		List<String> serverAdmins = plugin.getConfig().getStringList("Server Admins");
		for (String admin : serverAdmins) {
			player.sendMessage(ChatColor.DARK_AQUA + "  - " + admin);
		}
		return true;
	}

}
