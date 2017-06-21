package com.example;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.minecraft.server.v1_11_R1.EntityWitherSkull;
import net.minecraft.server.v1_11_R1.WorldServer;

public class CableLine {
 
private EntityWitherSkull start;
private EntityWitherSkull end;
 
public CableLine() {
}
 
public Location getEnd() {
return this.end.getBukkitEntity().getLocation();
}
 
public Location getStart() {
return this.start.getBukkitEntity().getLocation();
}
 
public void move(Location newStart, Location newEnd) {
delete();
spawn(newStart, newEnd);
}
 
public void delete() {
start.getBukkitEntity().getPassenger().remove();
start.getBukkitEntity().remove();
end.getBukkitEntity().getPassenger().remove();
end.getBukkitEntity().remove();
this.start = null;
this.end = null;
}
 
public void spawn(Location start, Location end) {
if (start.getWorld().getName().equals(end.getWorld().getName())) {
 
WorldServer world = ((CraftWorld) start.getWorld()).getHandle();
 
EntityWitherSkull skullStart = new EntityWitherSkull(world);
EntityWitherSkull skullEnd = new EntityWitherSkull(world);
 
skullStart.setLocation(start.getX(), start.getY(), start.getZ(), 0, 0);
skullEnd.setLocation(end.getX(), end.getY(), end.getZ(), 0, 0);
 
world.addEntity(skullStart);
world.addEntity(skullEnd);
 
 
Bat ropeStart = (Bat) world.getWorld().spawnEntity(start, EntityType.BAT);
Bat ropeEnd = (Bat) world.getWorld().spawnEntity(end, EntityType.BAT);
 
skullStart.getBukkitEntity().setPassenger(ropeStart);
skullEnd.getBukkitEntity().setPassenger(ropeEnd);
 
ropeStart.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 9999999, 1));
ropeEnd.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 9999999, 1));
 
ropeStart.setLeashHolder(ropeEnd);
 
this.start = skullStart;
this.end = skullEnd;
}
}
}