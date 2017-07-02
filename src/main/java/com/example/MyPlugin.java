package com.example;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Nameable;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.example.NMSUtils.Type;

import me.benfah.cu.api.CustomBlock;
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
			CableLine.load();
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
	public void onDisable()
	{
		try {
			CableLine.save();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("lolx"))
		if(args.length == 0)	
		((Player)sender).getInventory().addItem(wirecoilItem.getItem());
		if(args.length == 1)
		{
			RayTrace rtu = RayTrace.eyeTrace((LivingEntity) sender, 6);
			if(rtu.getBlock() != null)
			{
				Block b = rtu.getBlock();
				CustomBlock cb = CustomRegistry.getCustomBlockByBlock(b);
				if(CustomRegistry.isCustomBlock(b))
				if(cb.getName().equalsIgnoreCase(MyPlugin.rfMachine.getName()))
				{
					MyPlugin.rfMachine.setCapacity(b.getLocation(), MyPlugin.rfMachine.getMaxCapacity(b.getLocation()));
				}
			}
		}
		
		
		
		
		return super.onCommand(sender, command, label, args);
	}
}
