package me.Darrionat.Bypass;

import org.bukkit.plugin.java.JavaPlugin;

import me.Darrionat.Bypass.Commands.Bypass;
import me.Darrionat.Bypass.Listeners.JoinLeaveListeners;

public class main extends JavaPlugin {
	@Override
	public void onEnable() {
		new Bypass(this);
		new JoinLeaveListeners(this);
		System.out.println("[Bypass v1.1] starting up!");
		saveDefaultConfig();
	}

	public void onDisable() {
		System.out.println("[Bypass v1.1] shutting down");
	}

}
