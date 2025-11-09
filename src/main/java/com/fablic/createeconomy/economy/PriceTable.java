package com.fablic.createeconomy.economy;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public final class PriceTable {
    private static final Map<Item, Integer> PRICES = new HashMap<>();

    static {
        PRICES.put(Items.DIAMOND, 250);
        PRICES.put(Items.EMERALD, 200);
        PRICES.put(Items.GOLD_INGOT, 80);
        PRICES.put(Items.IRON_INGOT, 30);
        PRICES.put(Items.NETHERITE_SCRAP, 500);
        PRICES.put(Items.ANCIENT_DEBRIS, 650);
        PRICES.put(Items.LAPIS_LAZULI, 10);
    }

    private PriceTable() {}

    public static long price(ItemStack stack) {
        int price = PRICES.getOrDefault(stack.getItem(), 0);
        return (long) price * stack.getCount();
    }
}
