package fr.snorcky.snorkits.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.snorcky.snorkits.Main;

public class CommandKits implements CommandExecutor {

	private Main main;

	public CommandKits(Main main) {
		this.main = main;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if(sender instanceof Player) {
				Player player = (Player)sender;
				
				if(cmd.getName().equalsIgnoreCase("kit")) {
					
					if(args.length == 0 || args.length > 1) {
						player.sendMessage("§c/kit <nomdukit>");
						player.sendMessage("§c/kits : Afficher la liste des kits");
					}
					if(args.length == 1) {
						for(String kit : main.getConfig().getConfigurationSection("kits").getKeys(false)) {
							if(args[0].equalsIgnoreCase(kit)) {
								player.sendMessage("§eVous avez reçu le kit : "+ kit);
								for(int i : main.getConfig().getConfigurationSection("kits").getIntegerList(kit + ".items")) {
									if(player.getInventory().firstEmpty() == -1) { //Si l'inventaire du joueur est plein
										player.getWorld().dropItem(player.getPlayer().getLocation(), new ItemStack(i, 1)); //on drop l'item au sol
										player.sendMessage(("Votre inventaire est plein, votre achat est à vos pieds!"));
									}else {
										player.getInventory().addItem(new ItemStack(i, 1)); // on lui donne l'item
									}
								}
							}
							if(!args[0].equalsIgnoreCase(kit)) {
								player.sendMessage("caca");
							}
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
