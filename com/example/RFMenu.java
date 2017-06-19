package com.example;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import me.benfah.cu.api.CustomBlock;
import me.benfah.cu.api.CustomGUI;
import me.benfah.cu.api.CustomRegistry;
import me.benfah.cu.main.CustomUtils;
import me.benfah.cu.util.ReflectionUtils;
import net.minecraft.server.v1_11_R1.NBTTagList;

public class RFMenu extends CustomGUI{
	
	public List<Inventory> inv = new ArrayList<>();
	
	public RFMenu()
	{
		super("rf_menu", "guis/upper_section", "guis/lower_section", 27);
	}
	
	@Override
	public void onClose(InventoryCloseEvent e) {
		super.onClose(e);
		Inventory inv = e.getInventory();
		String[] title = inv.getTitle().split(",");
		Location loc = new Location(Bukkit.getWorld(title[0]), Integer.parseInt(title[1]), Integer.parseInt(title[2]), Integer.parseInt(title[3]));
		RFMachine rfMachine = MyPlugin.rfMachine;
		
		
		
		rfMachine.saveInventory(loc, inv);
	}
	
	
}
