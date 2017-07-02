package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;

import com.gargoylesoftware.htmlunit.javascript.host.geo.Geolocation;
import com.google.common.collect.Lists;

import me.benfah.cu.api.CustomBlock;
import me.benfah.cu.api.CustomRegistry;

public class RFCable extends CustomBlock implements IVisualCable, IEnergyContainer
{
	private ArrayList<Location> rfCableLocation = new ArrayList<>();
	
	public RFCable() {
		super("cable", "block/rf_cable");
	}
	
	
	public void addConnectant(Location loc, Location connectant)
	{
		System.out.println("lolxofneifgbiergbierbg");
		Block b = connectant.getBlock();
		if(CustomRegistry.isCustomBlock(b))
		{
			String stringLocation = locToString(connectant);
			CustomBlock cb = CustomRegistry.getCustomBlockByBlock(b);
			List<String> connectants = hasMetadataValue(loc, "connectants") ? (List<String>) getMetadataValue(loc, "connectants") : new ArrayList<>();
			if(!connectants.contains(stringLocation))
			connectants.add(stringLocation);
			setMetadataValue(loc, "connectants", connectants);
		}
	}
	
	private static String locToString(Location loc)
	{
		return loc.getWorld().getName() + ";" + loc.getBlockX() + ";" + loc.getBlockY() + ";" + loc.getBlockZ();
	}
	
	private static Location stringToLoc(String s)
	{
		String[] sarray = s.split(";");
		return new Location(Bukkit.getWorld(sarray[0]), Integer.parseInt(sarray[1]), Integer.parseInt(sarray[2]), Integer.parseInt(sarray[3]));
	}
	
	private static List<Integer> splitIntoParts(int whole, int parts) {
	    int[] arr = new int[parts];
	    int remain = whole;
	    int partsLeft = parts;
	    for (int i = 0; partsLeft > 0; i++) {
	        int size = (remain + partsLeft - 1) / partsLeft; // rounded up, aka ceiling
	        arr[i] = size;
	        remain -= size;
	        partsLeft--;
	    }
	    List<Integer> intList = new ArrayList<Integer>();
	    for (int index = 0; index < arr.length; index++)
	    {
	        intList.add(arr[index]);
	    }
	    return intList;
	   }
	
	public List<Location> getConnectants(Location loc)
	{
		List<String> locStringList = hasMetadataValue(loc, "connectants") ? (List<String>) getMetadataValue(loc, "connectants") : new ArrayList<>();
		List<Location> locList = new ArrayList<>();
		for(String s : locStringList)
		{
			locList.add(stringToLoc(s));
		}

		return locList;
	}
	
	@Override
	public void onUpdate(Location loc)
	{
		System.out.println("hai");
		List<Location> locList = getConnectants(loc);
		List<Integer> il = null;
		for(Location l : locList)
		{
			
			transferEnergy(loc, l, getTransferRatePerTick(loc));
			
		}
		super.onUpdate(loc);
	}
	
	@Override
	public BlockPos getRelativeContactPoint() {
		return new BlockPos(0, 0, 0);
	}
	
	public void transferEnergy(Location from, Location to, int toTransfer)
	{
		int rAm = getAmountFromCapacity(from, toTransfer);
		System.out.println(rAm);
		int e = receiveEnergy(to, rAm);
			
		setCapacity(from, getCapacity(from) - rAm + e);
		
		
	}
	
	
	public int getAmountFromCapacity(Location loc, int amount)
	{
		if(getCapacity(loc) >= amount)
		{
			setCapacity(loc, getCapacity(loc) - amount);
			return amount;
		}
		else
		{
			int result = getCapacity(loc);
			setCapacity(loc, 0);
			return result;
		}
	}
	
	@Override
	public int getMaxCapacity(Location loc) {
		return 600;
	}

	@Override
	public int getTransferRatePerTick(Location loc) {
		return 20;
	}

	@Override
	public int getCapacity(Location loc) {
		return hasMetadataValue(loc, "energy") ? (int) getMetadataValue(loc, "energy") : 0;
	}

	@Override
	public int receiveEnergy(Location loc, int energyToReceive)
	{
		int energy = getCapacity(loc);
		if(energy + energyToReceive > getMaxCapacity(loc))
		{
			setCapacity(loc, getMaxCapacity(loc));
			return energy + energyToReceive - getMaxCapacity(loc);
		}
		else
		{
			setCapacity(loc, energy + energyToReceive);
			return 0;
		}
	}

	@Override
	public void setCapacity(Location loc, int energyToSet)
	{
		setMetadataValue(loc, "energy", energyToSet);
	}

	
}
