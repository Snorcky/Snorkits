package fr.snorcky.snorkits;

import org.bukkit.plugin.java.JavaPlugin;

import fr.snorcky.snorkits.commands.CommandKits;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		saveDefaultConfig();
		
		System.out.println("[Snorkits] 1.0 Plugin initialisé");
		getCommand("kit").setExecutor(new CommandKits(this));
		getCommand("kits").setExecutor(new CommandKits(this));

	}
	
	@Override
	public void onDisable() {
		System.out.println("[Snorkits] 1.0 Plugin éteint");
		super.onDisable();
	}
}
