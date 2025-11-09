package com.fablic.createeconomy;

import com.fablic.createeconomy.economy.PlayerBalanceManager;
import com.fablic.createeconomy.network.ModNetwork;
import com.fablic.createeconomy.registry.ModItems;
import com.fablic.createeconomy.registry.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateEconomyMod implements ModInitializer {
    public static final String MOD_ID = "createeconomy";
    public static final Logger LOGGER = LoggerFactory.getLogger(CreateEconomyMod.class);

    @Override
    public void onInitialize() {
        ModItems.register();
        ModScreenHandlers.register();
        PlayerBalanceManager.register();
        ModNetwork.register();

        LOGGER.info("CreateEconomy initialized");
    }
}
