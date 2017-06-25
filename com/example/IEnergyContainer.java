package com.example;

import org.bukkit.Location;

public interface IEnergyContainer
{
	public int getMaxCapacity(Location loc);
	
	public int getTransferRatePerTick(Location loc);
	
	public int getCapacity(Location loc);
	
	public void setCapacity(Location loc, int energyToSet);


	public int receiveEnergy(Location loc, int energyToReceive);
}
