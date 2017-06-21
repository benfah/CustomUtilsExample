package com.example;

import org.apache.http.impl.conn.Wire;
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
	public static final RFCable rfMachine = new RFCable();
	public static final RFMenu rfMenu = new RFMenu();
	public static final WirecoilItem wirecoilItem = new WirecoilItem();
	@Override
	public void onEnable() {
		this.instance = this;
		CustomRegistry.registerBlock(rfMachine, this);
		CustomRegistry.registerGUI(rfMenu, this);
		CustomRegistry.registerItem(wirecoilItem, this);

	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("lolx"))
		((Player)sender).getInventory().addItem(wirecoilItem.getItem());
		return super.onCommand(sender, command, label, args);
	}
}
