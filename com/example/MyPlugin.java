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
	@Override
	public void onEnable() {
		this.instance = this;
		CustomRegistry.registerBlock(rfMachine, this);
		CustomRegistry.registerGUI(rfMenu, this);
		
	}
}
