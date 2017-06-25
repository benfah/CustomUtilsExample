package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;

import com.google.common.collect.Lists;

import me.benfah.cu.api.CustomBlock;
import me.benfah.cu.api.CustomRegistry;

public class RFCable extends CustomBlock implements IVisualCable, IEnergyContainer
{
	private ArrayList<Location> rfCableLocation = new ArrayList<>();
	
	public RFCable() {
		super("cable", "block/cable");
	}
	
	
	public void addConnectant(Location loc, Location connectant)
	{
		Block b = connectant.getBlock();
		if(CustomRegistry.isCustomBlock(b))
		{
			CustomBlock cb = CustomRegistry.getCustomBlockByBlock(b);
			List<SerializableLocation> connectants = hasMetadataValue(loc, "connectants") ? (List<SerializableLocation>) getMetadataValue(loc, "connectants") : new ArrayList<>();
			if(!connectants.contains(connectant))
			connectants.add(new SerializableLocation(loc));
			setMetadataValue(loc, "connectants", connectants);
		}
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
	
	public List<SerializableLocation> getConnectants(Location loc)
	{
		return hasMetadataValue(loc, "connectants") ? (ArrayList<SerializableLocation>) getMetadataValue(loc, "connectants") : new ArrayList<>();
	}
	
	@Override
	public void onUpdate(Location loc)
	{
		List<SerializableLocation> l = getConnectants(loc);
		List<Integer> il = null;
		for(SerializableLocation sl : l)
		{
			if(getCapacity(loc) >= getTransferRatePerTick(loc))
			{
				if(il == null) il = splitIntoParts(getTransferRatePerTick(loc), l.size());
				
				setCapacity(sl, );
				
				
			}
		}
		super.onUpdate(loc);
	}
	
	@Override
	public Location getRelativeContactPoint() {
		return new Location(null, 0, 0, 0);
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
