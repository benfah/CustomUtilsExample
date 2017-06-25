package com.example;

import org.bukkit.Bukkit;
import org.bukkit.Nameable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.example.NMSUtils.Type;

import me.benfah.cu.api.CustomRegistry;
import me.benfah.cu.util.ReflectionUtils;

public class MyPlugin extends JavaPlugin
{
	public static MyPlugin instance;
	public static final RFCable rfMachine = new RFCable();
	public static final WirecoilItem wirecoilItem = new WirecoilItem();
	@Override
	public void onEnable() {
		MyPlugin.instance = this;
		
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		Bukkit.getScheduler().runTaskTimer(this, new TickRunnable(), 20, 20);
		CustomRegistry.registerBlock(rfMachine, this);
		CustomRegistry.registerItem(wirecoilItem, this);
		

	}
	
	@Override
	public void onLoad()
	{
		
		NMSUtils.registerEntity("cable_bat", Type.BAT, JavassistBat.toClass(), false);

	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("lolx"))
		if(args.length == 0)	
		((Player)sender).getInventory().addItem(wirecoilItem.getItem());
		if(args.length == 0 && args[0].equalsIgnoreCase("rm"))
		for(Entity ent : ((Player)sender).getWorld().getEntities())
		{
			if(ent instanceof Nameable)
			if(((Nameable)ent).getCustomName() != null)
			if(ent.getCustomName().equals("cb"))
			{
				try {
				Class<?> ce = ReflectionUtils.getRefClass("{cb}.entity.CraftEntity");
				Object nms = ce.getMethod("getHandle").invoke(ent);
					JavassistBat.toClass().getMethod("die").invoke(nms);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
		if((args.length > 0) && args[0].equalsIgnoreCase("inject"))
		{
			((CraftPlayer)sender).getHandle().playerConnection = new CustomPlayerConnection(((CraftServer)Bukkit.getServer()).getServer(), ((CraftPlayer)sender).getHandle().playerConnection.networkManager, ((CraftPlayer)sender).getHandle());
		}
		
		
		return super.onCommand(sender, command, label, args);
	}
}
