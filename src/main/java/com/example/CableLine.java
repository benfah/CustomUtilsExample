package com.example;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Bat;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.google.common.collect.HashBiMap;

import me.benfah.cu.api.CustomBlock;
import me.benfah.cu.api.CustomRegistry;
import me.benfah.cu.util.ReflectionUtils;
 
 
public class CableLine {
 
private Bat start;
private Bat end;
 
public static HashBiMap<Integer, Integer> biMap = HashBiMap.create();

public static void save() throws IOException
{
	File f = new File(MyPlugin.instance.getDataFolder(), "cableMap");
	f.mkdirs();
	if(f.exists())
	f.delete();
	FileOutputStream fos = new FileOutputStream(f);
	ObjectOutputStream oos = new ObjectOutputStream(fos);
	oos.writeObject(biMap);
	oos.close();
	fos.close();
}

public static void load() throws IOException, ClassNotFoundException
{
	File f = new File(MyPlugin.instance.getDataFolder(), "cableMap");
	if(f.exists())
	{
	FileInputStream fis = new FileInputStream(f);
	ObjectInputStream ois = new ObjectInputStream(fis);
	biMap = (HashBiMap<Integer, Integer>) ois.readObject();
	ois.close();
	fis.close();
	}
}

public CableLine() {
}
 
public Location getEnd() {
return this.end.getLocation();
}
 
public Location getStart() {
return this.start.getLocation();
}
 
public void move(Block newStart, Block newEnd) {
delete();
spawn(newStart, newEnd);
}
 
public void delete() {
start.remove();
end.remove();
this.start = null;
this.end = null;
}
 
public void spawn(Block start, Block end) {
if (start.getWorld().getName().equals(end.getWorld().getName())) {
 
 
 World w = start.getWorld();
 CraftWorld cw = (CraftWorld)w;
 try {

 
 Class<?> craftWorldClass = ReflectionUtils.getRefClass("{cb}.CraftWorld");
 Class<?> craftEntityClass = ReflectionUtils.getRefClass("{cb}.entity.CraftEntity");
 Class<?> craftPlayerClass = ReflectionUtils.getRefClass("{cb}.entity.CraftPlayer");

 Class<?> ppoaeClass = ReflectionUtils.getRefClass("{nms}.PacketPlayOutAttachEntity");
 Class<?> packetClass = ReflectionUtils.getRefClass("{nms}.Packet");
 Class<?> worldClass = ReflectionUtils.getRefClass("{nms}.World");

 Class<?> entityClass = ReflectionUtils.getRefClass("{nms}.Entity");

Location startL = start.getLocation().clone();
Location endL = end.getLocation().clone();
CustomBlock Scb = CustomRegistry.getCustomBlockByBlock(start);
CustomBlock Ecb = CustomRegistry.getCustomBlockByBlock(end);

//	Object nmsRopeStart = JavassistBat.javassistBat.getConstructor(nmsWorld.getClass().getSuperclass()).newInstance(nmsWorld);
//	Object nmsRopeEnd = JavassistBat.javassistBat.getConstructor(nmsWorld.getClass().getSuperclass()).newInstance(nmsWorld);
//	nmsRopeStart.getClass().getMethod("setLocation", double.class, double.class, double.class, float.class, float.class).invoke(nmsRopeStart, start.getX(), start.getY(), start.getZ(), start.getYaw(), start.getPitch());
//	nmsRopeStart.getClass().getMethod("setLocation", double.class, double.class, double.class, float.class, float.class).invoke(nmsRopeEnd, start.getX(), start.getY(), start.getZ(), start.getYaw(), start.getPitch());
// 
startL.add(0, -1.1, -0.2);
endL.add(0.69, 0.15, -0.5);
if(Scb instanceof IVisualCable)
{
	startL.add(((IVisualCable)Scb).getRelativeContactPoint().getX(), ((IVisualCable)Scb).getRelativeContactPoint().getY(), ((IVisualCable)Scb).getRelativeContactPoint().getZ());
}
if(Ecb instanceof IVisualCable)
{
	endL.add(((IVisualCable)Ecb).getRelativeContactPoint().getX(), ((IVisualCable)Ecb).getRelativeContactPoint().getY(), ((IVisualCable)Ecb).getRelativeContactPoint().getZ());
}


Object craftWorld = craftWorldClass.cast(w);
Object nmsWorld = craftWorld.getClass().getMethod("getHandle").invoke(craftWorld);

//Bat ropeStart = (Bat) w.spawnEntity(start.getLocation().clone().add(0.5, -0.7, 0.3), EntityType.BAT);
//Bat ropeEnd = (Bat) w.spawnEntity(end.getLocation().clone().add(1.19, 0.57, 0), EntityType.BAT);

 Object tb = JavassistBat.toClass().getConstructor(worldClass).newInstance(nmsWorld);
 Object tb2 = JavassistBat.toClass().getConstructor(worldClass).newInstance(nmsWorld);
 tb.getClass().getMethod("setLocation", double.class, double.class, double.class, float.class, float.class).invoke(tb, startL.getX(), startL.getY(), startL.getZ(), startL.getYaw(), startL.getPitch());
 tb2.getClass().getMethod("setLocation", double.class, double.class, double.class, float.class, float.class).invoke(tb2, endL.getX(), endL.getY(), endL.getZ(), endL.getYaw(), endL.getPitch());

 craftWorld.getClass().getMethod("addEntity", entityClass, SpawnReason.class).invoke(craftWorld, tb, SpawnReason.CUSTOM);
 craftWorld.getClass().getMethod("addEntity", entityClass, SpawnReason.class).invoke(craftWorld, tb2, SpawnReason.CUSTOM);
 Bat ropeStart = (Bat) tb.getClass().getMethod("getBukkitEntity").invoke(tb);
 Bat ropeEnd = (Bat) tb2.getClass().getMethod("getBukkitEntity").invoke(tb2);

 
ropeStart.setGravity(false);
ropeStart.setInvulnerable(true);
ropeStart.setCustomName("rs");
//ropeStart.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999 * 20, 0, false, false));
ropeStart.setAI(false);
ropeStart.setAwake(true);
ropeStart.setSilent(true);

ropeEnd.setGravity(false);
ropeEnd.setInvulnerable(true);
//ropeEnd.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999 * 20, 0, false, false));
ropeEnd.setAI(false);
ropeEnd.setAwake(true);
ropeEnd.setSilent(true);
ropeEnd.setCustomName("re");

//Object nmsRopeStart = craftEntityClass.getMethod("getHandle").invoke(ropeStart);
//Object nmsRopeEnd = craftEntityClass.getMethod("getHandle").invoke(ropeEnd);
//
//Object ppoae = ppoaeClass.getConstructor(entityClass, entityClass).newInstance(nmsRopeStart, nmsRopeEnd);
//
//Object entityPlayer = craftPlayerClass.getMethod("getHandle").invoke(Bukkit.getPlayer("benfah"));
//
//Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
//
//
//playerConnection.getClass().getMethod("sendPacket", packetClass).invoke(playerConnection, ppoae);

//ropeStart.setLeashHolder(ropeEnd);
this.biMap.put(ropeStart.getEntityId(), ropeEnd.getEntityId());
System.out.println(biMap.get(ropeStart.getEntityId()));
this.start = ropeStart;
this.end = ropeEnd;
 } catch (Exception e) {
		e.printStackTrace();
	}
	 
}
}
}