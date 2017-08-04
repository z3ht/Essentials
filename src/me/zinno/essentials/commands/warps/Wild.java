package me.zinno.essentials.commands.warps;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Wild implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use this command");
			return true;
		}
		Player player = (Player) sender;

		WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();

		double size = wb.getSize() / 2;
		int zcenter = wb.getCenter().getBlockY();
		int xcenter = wb.getCenter().getBlockY();

		double randFloatz, randFloatx;
		int x, y, z;
		int counter = 0;
		do {
			randFloatz = Math.random();
			randFloatx = Math.random();

			double randx = Math.random();
			if (randx > .5) {
				randx = (int) -1;
			} else {
				randx = (int) 1;
			}

			double randz = Math.random();
			if (randz > .5) {
				randz = (int) -1;
			} else {
				randz = (int) 1;
			}
			x = (int) ((size * randFloatz + zcenter) * randx);
			z = (int) ((size * randFloatx + xcenter) * randz);
			y = player.getWorld().getHighestBlockYAt(x, z);
			if (counter == 49) {
				player.sendMessage(ChatColor.RED + "Could not find a desirable location");
				return true;
			}
			counter += 1;
		} while (y == 0 && counter != 50);

		player.teleport(new Location(player.getWorld(), x, y, z));
		player.sendMessage(ChatColor.GREEN + "Welcome to the wild!");
		return true;
	}
}
