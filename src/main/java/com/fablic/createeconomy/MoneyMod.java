package com.fablic.createeconomy;

import com.fablic.createeconomy.economy.PlayerBalanceManager;
import com.fablic.createeconomy.network.ModNetwork;
import com.fablic.createeconomy.registry.ModItems;
import com.fablic.createeconomy.registry.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoneyMod implements ModInitializer {
    public static final String MOD_ID = "fablic_create_economy";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.register();
        ModScreenHandlers.register();
        ModNetwork.register();

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.player;
            PlayerBalanceManager.syncBalance(player);
        });

        ServerLifecycleEvents.SERVER_STARTED.register(server -> PlayerBalanceManager.onServerStarted(server));

        LOGGER.info("MoneyMod initialized");
    }

    public static Identifier id(String value) {
        return Identifier.of(MOD_ID, value);
    }
}
