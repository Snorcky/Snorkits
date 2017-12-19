package fr.snorcky.snorkits.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.snorcky.snorkits.Main;

public class CommandKits implements CommandExecutor {

	private Main main;

	public CommandKits(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if(sender instanceof Player) {
				Player player = (Player)sender;
				
				if(cmd.getName().equalsIgnoreCase("kit")) {
					
					if(args.length == 0) {
						player.sendMessage("§c/kit <nomdukit>");
						player.sendMessage("§c/kits : Afficher la liste des kits");
					}
					if(args.length == 1) {
						player.sendMessage("caca");
						for(String kit : main.getConfig().getConfigurationSection("kits").getKeys(false)) {
							player.sendMessage(kit);
							System.out.println((main.getConfig().getConfigurationSection("kits").getIntegerList(kit + ".items")));
							player.getInventory().addItem();
						}
					}
					
				}
				if(cmd.getName().equalsIgnoreCase("kits")) {
					if(args.length == 0) {
						if(player.hasPermission("kit.use")) {
							player.sendMessage("§b=========Snorkit=========");
							player.sendMessage("§bListe des kits: ");
								for(String listkit : main.getConfig().getConfigurationSection("kits").getKeys(false)) {
									player.sendMessage("§e- " + listkit);
								}
							player.sendMessage("§b========================");
						}else {
							player.sendMessage(main.getConfig().getString("messages.no-perm").replaceAll("&", "§"));
						}
					}

				}
			}
		return false;
	}

}
