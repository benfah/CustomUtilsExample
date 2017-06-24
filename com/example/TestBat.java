package com.example;

import net.minecraft.server.v1_12_R1.EntityBat;
import net.minecraft.server.v1_12_R1.EntityItem;
import net.minecraft.server.v1_12_R1.Item;
import net.minecraft.server.v1_12_R1.ItemStack;
import net.minecraft.server.v1_12_R1.Items;
import net.minecraft.server.v1_12_R1.World;

public class TestBat extends EntityBat{
	public TestBat(World w)
	{
		super(w);
	}
	@Override
	public EntityItem a(Item item, int i) {
		if(!item.equals(Items.LEAD))
		return super.a(item, i);
		return null;
	}
	
}
