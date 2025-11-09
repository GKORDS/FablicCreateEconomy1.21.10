package com.fablic.createeconomy.network;

import com.fablic.createeconomy.economy.PlayerBalanceManager;
import com.fablic.createeconomy.economy.TradingLogic;
import com.fablic.createeconomy.network.payload.SellAllInventoryPayload;
import com.fablic.createeconomy.network.payload.SyncBalancePayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;

public final class ModNetwork {
    private ModNetwork() {
    }

    public static void register() {
        PayloadTypeRegistry.playC2S().register(SellAllInventoryPayload.ID, SellAllInventoryPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(SyncBalancePayload.ID, SyncBalancePayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(SellAllInventoryPayload.ID, (payload, context) -> {
            ServerPlayerEntity player = context.player();
            context.server().execute(() -> {
                long earned = TradingLogic.sellInventory(player);
                if (earned > 0) {
                    PlayerBalanceManager.addBalance(player, earned);
                } else {
                    PlayerBalanceManager.syncBalance(player);
                }
            });
        });
    }

    public static void sendBalance(ServerPlayerEntity player, long balance) {
        ServerPlayNetworking.send(player, new SyncBalancePayload(balance));
    }
}
