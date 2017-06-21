package com.example;

import java.util.List;

import me.benfah.cu.api.CustomItem;

public class RFItem extends CustomItem{

	public RFItem(List<String> lore) {
		super("rf_item", "block/test_block", "Redstone Flux Item", lore);
	}

	public RFItem() {
		super("rf_item", "block/test_block", "Redstone Flux Item");
	}

}
