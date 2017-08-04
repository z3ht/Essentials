package me.zinno.essentials.commands.warps.set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.zinno.essentials.Main;
import net.md_5.bungee.api.ChatColor;

public class SetSpawn implements CommandExecutor {

	private Main plugin;

	public SetSpawn(Main pl) {
		plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))
			return true;
		Player player = (Player) sender;
		if (!(player.hasPermission("essentials.setspawn"))) {
			player.sendMessage(ChatColor.RED + "You do not have permission to set the spawn");
			return true;
		}
		if (args.length != 0) {
			player.sendMessage(ChatColor.RED + "This command does not require args");
			return true;
		}
		String newSpawnPoint = player.getLocation().getBlockX() + " " + player.getLocation().getBlockZ();
		plugin.getConfig().set("Spawn", newSpawnPoint);
		player.sendMessage(ChatColor.GREEN + "New spawn location created");
		plugin.saveConfig();
		return true;
	}
}
