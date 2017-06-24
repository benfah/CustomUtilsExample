package com.example;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.benfah.cu.api.CustomBlock;
import me.benfah.cu.api.CustomItem;
import me.benfah.cu.api.CustomRegistry;

public class WirecoilItem extends CustomItem{

	public WirecoilItem() {
		super("wirecoil", "item/wirecoil", "Wirecoil");
	}
	
	@Override
	public void onInteract(PlayerInteractEvent e, EquipmentSlot es) {
		Block b = e.getClickedBlock();
		Player p = e.getPlayer();
		if(CustomRegistry.isCustomBlock(b))
		{
			CustomBlock cb = CustomRegistry.getCustomBlockByBlock(b);
			if(cb.getName().equals("cable"))
			{
				ItemStack stack = getStackOfES(es, p);
				NBTItem item = new NBTItem(stack);
				if(!item.hasKey("firstloc"))
				{
					item.setString("firstloc", b.getWorld().getName() + ";" + b.getX() + ";" + b.getY() + ";" + b.getZ());
				}
				else
				{
					CableLine cl = new CableLine();
					String[] ls = item.getString("firstloc").split(";");
					Location firstloc = new Location(Bukkit.getWorld(ls[0]), Integer.parseInt(ls[1]), Integer.parseInt(ls[2]), Integer.parseInt(ls[3]));
					cl.spawn(firstloc.getBlock(), b);
					item.removeKey("firstloc");
					setStackOfES(es, p, item.getItem());
				}
				setStackOfES(es, p, item.getItem());
			}
		}
		
		
		super.onInteract(e, es);
	}
	@Override
	public void onUpdate(Player p, int slot) {
		
	}
	
	
	public static ItemStack getStackOfES(EquipmentSlot es, Player p)
	{
		if(es.equals(EquipmentSlot.HAND))
			return p.getInventory().getItemInMainHand();
		
		if(es.equals(EquipmentSlot.OFF_HAND))
			return p.getInventory().getItemInOffHand();
		return null;
	}
	public static void setStackOfES(EquipmentSlot es, Player p, ItemStack is)
	{
		if(es.equals(EquipmentSlot.HAND))
			p.getInventory().setItemInMainHand(is);
		
		if(es.equals(EquipmentSlot.OFF_HAND))
			p.getInventory().setItemInOffHand(is);
	}
}
