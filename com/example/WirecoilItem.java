package com.example;

import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;

import me.benfah.cu.api.CustomBlock;
import me.benfah.cu.api.CustomItem;
import me.benfah.cu.api.CustomRegistry;

public class WirecoilItem extends CustomItem{

	public WirecoilItem() {
		super("wirecoil", "item/wirecoil", "Wirecoil");
	}
	
	@Override
	public void onInteract(PlayerInteractEvent e) {
		Block b = e.getClickedBlock();
		
		if(CustomRegistry.isCustomBlock(b))
		{
			CustomBlock cb = CustomRegistry.getCustomBlockByBlock(b);
			if(cb.getName().equals("cable"))
			{
				
			}
		}
		
		
		super.onInteract(e);
	}
}
