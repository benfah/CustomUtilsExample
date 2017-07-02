package com.example;

import org.bukkit.inventory.ItemStack;

import me.benfah.cu.util.NBTCompound;

public class NBTItem extends NBTCompound{
    
    private ItemStack bukkitItem;
    
    public NBTItem(ItemStack item) {
        super(null, null);
        bukkitItem = item.clone();
    }

    @Override
    public ItemStack getItem() {
        return bukkitItem;
    }

    @Override
    protected void setItem(ItemStack item) {
        bukkitItem = item;
    }
    
    

}