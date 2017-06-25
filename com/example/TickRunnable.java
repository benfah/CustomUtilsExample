package com.example;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_12_R1.PacketPlayOutAttachEntity;

public class TickRunnable implements Runnable{

	@Override
	public void run()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			
			for(Entity ent : p.getNearbyEntities(10, 10, 10))
			{
				int firstId = ent.getEntityId();
				int secondId = 0;
				if(CableLine.biMap.containsKey(firstId))
				{
					secondId = CableLine.biMap.get(firstId);
				}
				
				if(secondId != 0)
				{
					final int sid2 = secondId;
						
					
					net.minecraft.server.v1_12_R1.Entity ent1 = ((CraftWorld)p.getWorld()).getHandle().getEntity(firstId);
					net.minecraft.server.v1_12_R1.Entity ent2 = ((CraftWorld)p.getWorld()).getHandle().getEntity(sid2);
					PacketPlayOutAttachEntity ppoae = new PacketPlayOutAttachEntity(ent1, ent2);
					((CraftPlayer)p).getHandle().playerConnection.sendPacket(ppoae);							
						
					
					
				}
			}
		}
	}

}
