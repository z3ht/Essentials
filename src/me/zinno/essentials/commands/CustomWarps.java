package me.zinno.essentials.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.zinno.essentials.Main;
import net.md_5.bungee.api.ChatColor;

public class CustomWarps implements CommandExecutor {
	private Main plugin;

	public CustomWarps(Main pl) {
		plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("SetWarp")) {
			if (!(sender instanceof Player))
				return true;
			Player player = (Player) sender;
			if (!(player.hasPermission("essentials.setwarp"))) {
				player.sendMessage(ChatColor.RED + "You do not have permission to create warps");
				return true;
			}
			if (args.length != 1) {
				player.sendMessage(ChatColor.RED + "Please name the warp you would like to create");
				return true;
			}
			int[] newWorld = {player.getLocation().getBlockX(),  player.getLocation().getBlockZ(), player.getWorld().getName()}
			plugin.getConfig().createSection(args[0]);
			plugin.getConfig().set(args[0], newWarp);
			player.sendMessage(ChatColor.GREEN + "New warp created. Use /delwarp to remove it");
			plugin.saveConfig();
			return true;
		} else if (label.equalsIgnoreCase("Warp")) {
			if (!(sender instanceof Player))
				return true;
			Player player = (Player) sender;
			if (!(player.hasPermission("essentials.warp"))) {
				player.sendMessage(ChatColor.RED + "You do not have permission to warp");
				return true;
			} else if (args.length != 1) {
				player.sendMessage(ChatColor.RED + "Please enter the desired warp location");
				return true;
			}
			String locationData = plugin.getConfig().getString(args[0]);
			if (locationData == null || locationData == "") {
				player.sendMessage(ChatColor.RED + args[0] + " is not a warp location");
			}
			int x = Integer.parseInt(locationData.split("\\s+")[0]);
			int z = Integer.parseInt(locationData.split("\\s+")[1]);
			int y = player.getWorld().getHighestBlockYAt(x, z);
			if (y == 0) {
				player.sendMessage(ChatColor.RED + "Unsafe warp location");
				return true;
			}
			Location teleport = new Location(Bukkit.getServer().getWorld(args[2]), x, y, z);
			player.sendMessage(ChatColor.GREEN + "Warping...");
			player.teleport(teleport);
			return true;
		} else if (label.equalsIgnoreCase("delwarp")) {
			if (!(sender.hasPermission("essentials.delwarp"))) {
				sender.sendMessage(ChatColor.RED + "You do not have permission to delete warps");
				return true;
			} else if (args.length != 1) {
				sender.sendMessage(ChatColor.RED + "Please clarify the warp you would like to delete");
				return true;
			}
			sender.sendMessage(ChatColor.RED
					+ "Sorry, this portion of the plugin is incomplete. Please delete the warps manually.");
			// Remove the section
		}
		plugin.saveConfig();
		return true;
	}
}
