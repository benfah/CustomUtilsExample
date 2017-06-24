package com.example;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Nameable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
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
		this.instance = this;
		
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		NMSUtils.registerEntity("cable_bat", Type.BAT, JavassistBat.toClass(), false);
		CustomRegistry.registerBlock(rfMachine, this);
		CustomRegistry.registerItem(wirecoilItem, this);

	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("lolx"))
		if(args.length == 0)	
		((Player)sender).getInventory().addItem(wirecoilItem.getItem());
		if(args[0].equalsIgnoreCase("rm"))
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
		return super.onCommand(sender, command, label, args);
	}
}
