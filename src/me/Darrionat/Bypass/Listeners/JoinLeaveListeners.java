package me.Darrionat.Bypass.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Darrionat.Bypass.main;

public class JoinLeaveListeners implements Listener {

	private main plugin;

	public JoinLeaveListeners(main plugin) {
		this.plugin = plugin;

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		// Used when someone leaves the server
		Player p = event.getPlayer();
		if (p.hasPermission("bypass.active")) {
			for (String remove : plugin.getConfig().getStringList("removepermissions")) {
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), remove.replace("%player%", p.getName()));
			}
		}
	}

}