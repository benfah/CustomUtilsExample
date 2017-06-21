package com.example;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.benfah.cu.api.CustomBlock;
import me.benfah.cu.api.CustomRegistry;

public class MyPlugin extends JavaPlugin
{
	public static MyPlugin instance;
	public static final RFMachine rfMachine = new RFMachine();
	public static final RFMenu rfMenu = new RFMenu();
	public static final RFItem rfItem = new RFItem();
	@Override
	public void onEnable() {
		this.instance = this;
		CustomRegistry.registerBlock(rfMachine, this);
		CustomRegistry.registerGUI(rfMenu, this);
		CustomRegistry.registerItem(rfItem, this);
		
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("lolx"))
		{
			Player p = (Player) sender;
			p.getInventory().addItem(rfItem.getItem());
		}
		return super.onCommand(sender, command, label, args);
	}
}
