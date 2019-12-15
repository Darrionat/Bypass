package me.Darrionat.Bypass.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.Darrionat.Bypass.main;

public class Bypass implements CommandExecutor {

	private main plugin;

	public Bypass(main plugin) {
		this.plugin = plugin;
		plugin.getCommand("bypass").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eOnly players may execute this command!"));
			return true;
		}
		Player p = (Player) sender;
		if (label.equalsIgnoreCase("bypass")) {
			if (args.length == 0) {
				// Prompts player with a list of commands
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6---Bypass " + plugin.getDescription().getVersion() + " By Darrionat---"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/bypass use &o&ato activate bypass mode"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&e/bypass reload &o&ato reload the plugin config"));
			} else {
				if (args[0].equalsIgnoreCase("use")) {
					if (p.hasPermission("bypass.use")) {
						// Checks to see if the player is in Bypass Mode already, if so removes
						// permissions.
						if (p.hasPermission("bypass.active")) {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aExiting Bypass Mode!"));
							for (String remove : plugin.getConfig().getStringList("removepermissions")) {
								Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
										remove.replace("%player%", p.getName()));
							}
							// Puts player in Bypass Mode
						} else {
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&aBypassing all boundaries now!"));
							p.sendMessage(ChatColor.translateAlternateColorCodes('&',
									"&aTo exit bypass mode, type /bypass again!"));
							for (String add : plugin.getConfig().getStringList("permissions")) {
								Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
										add.replace("%player%", p.getName()));
							}
						}
						return true;
					} else {
						// If player does not have bypass.use
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&4You do not have the permission node 'bypass.use'"));
					}
				}
				// Reload Function
				if (args[0].equalsIgnoreCase("reload")) {
					Player player = (Player) sender;
					if (player.hasPermission("bypass.reload")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&fReloading Bypass Config..."));
						Bukkit.getPluginManager().getPlugin("Bypass").reloadConfig();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aReloading Complete!"));
					}
				}
			}
		}
		return true;
	}

}