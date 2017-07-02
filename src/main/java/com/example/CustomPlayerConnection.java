package com.example;


import java.lang.reflect.Field;

import org.bukkit.Bukkit;

import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.MinecraftServer;
import net.minecraft.server.v1_12_R1.NetworkManager;
import net.minecraft.server.v1_12_R1.Packet;
import net.minecraft.server.v1_12_R1.PacketPlayOutAttachEntity;
import net.minecraft.server.v1_12_R1.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_12_R1.PlayerConnection;

public class CustomPlayerConnection extends PlayerConnection{

	public CustomPlayerConnection(MinecraftServer minecraftserver, NetworkManager networkmanager,
			EntityPlayer entityplayer) {
		super(minecraftserver, networkmanager, entityplayer);
	}
	@Override
	public void sendPacket(Packet<?> packet)
	{
		if(packet instanceof PacketPlayOutAttachEntity)
		System.out.println("fefergpreubghghfghujnfgdhuofgr");	
		if(packet instanceof PacketPlayOutSpawnEntityLiving)
		{
			PacketPlayOutSpawnEntityLiving cp = (PacketPlayOutSpawnEntityLiving) packet;
			try 
			{
				System.out.println("hi");
				Field f = cp.getClass().getDeclaredField("a");
				f.setAccessible(true);
				int firstId = (int) f.get(cp);
				int secondId = 0;
				CableLine.biMap.entrySet().forEach(entr -> System.out.println(entr.getKey() + "|" + entr.getValue()));
				if(CableLine.biMap.containsKey(firstId))
				{
					secondId = CableLine.biMap.get(firstId);
					System.out.println("fuck off");
				}
				else
				if(CableLine.biMap.containsValue(firstId))
				{
					secondId = CableLine.biMap.inverse().get(firstId);
					System.out.println("fuck off2");

				}
				if(secondId != 0)
				{
					final int sid2 = secondId;
					Bukkit.getScheduler().runTaskLater(MyPlugin.instance, new Runnable() {
						
						@Override
						public void run() {
							Entity ent1 = CustomPlayerConnection.this.player.getWorld().getEntity(firstId);
							System.out.println("lolx nultzen jaegazn");
							Entity ent2 = CustomPlayerConnection.this.player.getWorld().getEntity(sid2);
							PacketPlayOutAttachEntity ppoae = new PacketPlayOutAttachEntity(ent1, ent2);
							sendPacket(ppoae);							
						}
					}, 250);
					
				}
				
				
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		super.sendPacket(packet);
	}
}
