package com.example;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import me.benfah.cu.api.CustomBlock;

public class RFMachine extends CustomBlock
{
	private ArrayList<Inventory> invlist = new ArrayList<>();
	
	public RFMachine() {
		super("test_block", "block/test_block");
	}

	@Override
	public void onInteract(PlayerInteractEvent e)
	{
		super.onInteract(e);
		Location loc = e.getClickedBlock().getLocation();
		
			String s = (String) getMetadataValue(loc, "inventory_0");
			String title = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
			boolean same = false;
			Inventory inv = null;
			
			for(Inventory finv : invlist)
			{
				if(finv != null)
				if(title.equals(finv.getTitle()))
				{
					same = true;
					inv = finv;
				}
			}
			if(!same)
			{
				if(s == null)
				{
					inv = MyPlugin.rfMenu.createInventory(loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ());
				}
				else
				inv = fromBase64(s, title);
				invlist.add(inv);
			}
			
			
			
			
			
			e.getPlayer().openInventory(inv);
		}
		
	
	
	
	public static Inventory fromBase64(String data, String title){
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt(), title);
            
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, (ItemStack) dataInput.readObject());
            }
            
            dataInput.close();
            return inventory;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
    }
	
	@Override
	public ItemStack[] getLoot(Location loc) {
		ItemStack[] istack = new ItemStack[]{};
		if(hasMetadataValue(loc, "inventory_0"))
		istack = fromBase64((String) getMetadataValue(loc, "inventory_0"), "").getContents();
		if(istack.length > 2)
		{
			istack[0] = new ItemStack(Material.AIR);
			istack[istack.length - 9] = new ItemStack(Material.AIR);
			return (ItemStack[]) ArrayUtils.addAll(istack, super.getLoot(loc));
		}
		else
		return super.getLoot(loc);
		
		
		
	}
	
	public static String toBase64(Inventory inventory) throws IllegalStateException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            
            // Write the size of the inventory
            dataOutput.writeInt(inventory.getSize());
            
            // Save every element in the list
            for (int i = 0; i < inventory.getSize(); i++) {
                dataOutput.writeObject(inventory.getItem(i));
            }
            
            // Serialize that array
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }
	
	public void saveInventory(Location loc, Inventory inv)
	{
		
		
		setMetadataValue(loc, "inventory_0", toBase64(inv));
		
		RFMachine rfMachine = MyPlugin.rfMachine;
	}
}
