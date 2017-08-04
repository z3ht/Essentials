package me.zinno.essentials.commands.warps;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.zinno.essentials.Main;
import net.md_5.bungee.api.ChatColor;

public class Spawn implements CommandExecutor {

	private Main plugin;

	public Spawn(Main pl) {
		plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can use this command!");
			return true;
		}
		Player player = (Player) sender;
		String locationData = plugin.getConfig().getString("Spawn");
		int x = Integer.parseInt(locationData.split("\\s+")[0]);
		int z = Integer.parseInt(locationData.split("\\s+")[1]);
		int y = player.getWorld().getHighestBlockYAt(x, z);
		if (y == 0) {
			player.sendMessage(ChatColor.RED + "Unsafe spawn location");
			return true;
		}
		Location teleport = new Location(Bukkit.getServer().getWorld("world"), x, y, z);
		player.sendMessage(ChatColor.GREEN + "Teleporting...");
		player.teleport(teleport);
		return true;
	}
}
