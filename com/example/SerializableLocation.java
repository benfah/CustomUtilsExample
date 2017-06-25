package com.example;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class SerializableLocation extends Location implements Serializable{
	
	String worldName;
	@Override
	public World getWorld() {
		return Bukkit.getWorld(worldName);
	}
	
	
	@Override
	public void setWorld(World world) {
		this.worldName = world.getName();
	}
	@Override
	public Block getBlock() {
		return getWorld().getBlockAt(this);
	}
	@Override
	public Chunk getChunk() {
		return getWorld().getChunkAt(this);
	}
	public SerializableLocation(World world, double x, double y, double z) {
		super(null, x, y, z);
		this.worldName = world.getName();
	}
	
	
	@Override
    public int hashCode() {
        int hash = 3;

        hash = 19 * hash + (this.getWorld() != null ? this.getWorld().hashCode() : 0);
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.getX()) ^ (Double.doubleToLongBits(this.getX()) >>> 32));
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.getY()) ^ (Double.doubleToLongBits(this.getY()) >>> 32));
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.getZ()) ^ (Double.doubleToLongBits(this.getZ()) >>> 32));
        hash = 19 * hash + Float.floatToIntBits(this.getPitch());
        hash = 19 * hash + Float.floatToIntBits(this.getYaw());
        return hash;
    }

	public SerializableLocation(World world, double x, double y, double z, float yaw, float pitch) {
		super(null, x, y, z, yaw, pitch);
		this.worldName = world.getName();
	}
	
	public SerializableLocation(Location loc)
	{
		super(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
	}
	@Override
    public String toString() {
        return "Location{" + "world=" + getWorld() + ",x=" + getX() + ",y=" + getY() + ",z=" + getZ() + ",pitch=" + getPitch() + ",yaw=" + getYaw() + '}';
    }
	
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;

        if (this.getWorld() != other.getWorld() && (this.getWorld() == null || !this.getWorld().equals(other.getWorld()))) {
            return false;
        }
        if (Double.doubleToLongBits(this.getX()) != Double.doubleToLongBits(other.getX())) {
            return false;
        }
        if (Double.doubleToLongBits(this.getY()) != Double.doubleToLongBits(other.getY())) {
            return false;
        }
        if (Double.doubleToLongBits(this.getZ()) != Double.doubleToLongBits(other.getZ())) {
            return false;
        }
        if (Float.floatToIntBits(this.getPitch()) != Float.floatToIntBits(other.getPitch())) {
            return false;
        }
        if (Float.floatToIntBits(this.getYaw()) != Float.floatToIntBits(other.getYaw())) {
            return false;
        }
        return true;
    }
}
