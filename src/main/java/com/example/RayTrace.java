package com.example;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.block.CraftBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_12_R1.MovingObjectPosition;
import net.minecraft.server.v1_12_R1.Vec3D;
import net.minecraft.server.v1_12_R1.WorldServer;

public class RayTrace {
	public static final double RAY_LENGTH_LIMIT = 200;
	
	private static Vec3D toVec3D( Vector vec ){
		return new Vec3D(vec.getX(), vec.getY(), vec.getZ());
	}

	private static WorldServer getHandle( World world ){
		if ( world instanceof CraftWorld )
			return ((CraftWorld) world).getHandle();
				
		throw new IllegalArgumentException("Cannot raytrace in a non CraftBukkit world!");
	}
	
	public static RayTrace rayTrace( World world, Vector start, Vector direction ){
		return rayTrace(world, start, direction, RAY_LENGTH_LIMIT);
	}
	public static RayTrace rayTrace( World world, Vector start, Vector direction, double length ){
		Vector end = start.clone().add( direction.multiply(length) );
		
		return new RayTrace(world, getHandle(world).rayTrace( toVec3D(start), toVec3D(end), false));
	}
	
	public static RayTrace eyeTrace( LivingEntity entity ){
		return eyeTrace(entity, RAY_LENGTH_LIMIT);
	}
	public static RayTrace eyeTrace( LivingEntity entity, double length ){
		Location loc = entity.getEyeLocation();

		World world = loc.getWorld();
		Vector end = loc.toVector().add( loc.getDirection().multiply(length) );
		
		return new RayTrace(world, getHandle(world).rayTrace( toVec3D(loc.toVector()), toVec3D(end), false));
	}
	
	private boolean isHit = false;
	
	private World hitWorld;
	private BlockVector hitBlock;
	private BlockFace hitFace;
	private Vector hitPos;
	
	protected RayTrace( World inWorld, MovingObjectPosition traceRes ){
		this.isHit = traceRes != null;
		this.hitWorld = inWorld;
		
		if ( isHit ){
			this.hitBlock	= new BlockVector(traceRes.a().getX(), traceRes.a().getY(), traceRes.a().getZ());
			this.hitFace	= CraftBlock.notchToBlockFace(traceRes.direction);
			this.hitPos 	= new Vector(traceRes.pos.x, traceRes.pos.y, traceRes.pos.z);
		}
	}
	
	public boolean isHit(){
		return isHit;
	}
	
	public BlockVector getBlockVector(){
		return hitBlock;
	}
	public Block getBlock(){
		return isHit ? hitBlock.toLocation(hitWorld).getBlock() : null;
	}
	
	public BlockFace getFace(){
		return hitFace;
	}
	
	public Vector getHitPos(){
		return hitPos;
	}
	
	public String toString(){
		return "[RayTrace:" + ( !isHit ? "MISS" : hitBlock + ";" + hitFace + ";" + hitPos ) + "]";
	}
}