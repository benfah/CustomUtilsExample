package com.example;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import me.benfah.cu.api.CustomBlock;

public class RFCable extends CustomBlock implements EnergyContainer
{
	private ArrayList<Inventory> invlist = new ArrayList<>();
	
	public RFCable() {
		super("cable", "block/cable");
	}

	@Override
	public Location getRelativeContactPoint() {
		return null;
	}

	
}
