package com.fablic.createeconomy.network;

import com.fablic.createeconomy.economy.PlayerBalanceManager;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;

public final class ModNetwork {
    private static boolean codecsRegistered;

    private ModNetwork() {}

    public static void register() {
        registerCodecs();

        ServerPlayNetworking.registerGlobalReceiver(SellAllPayload.ID, (payload, context) -> {
            ServerPlayerEntity player = context.player();
            context.server().execute(() -> {
                long gained = PlayerBalanceManager.sellInventory(player);
                if (gained == 0) {
                    PlayerBalanceManager.syncBalance(player);
                }
            });
        });
    }

    public static void sendBalance(ServerPlayerEntity player, long balance) {
        ServerPlayNetworking.send(player, new BalanceSyncPayload(balance));
    }

    public static void registerCodecs() {
        if (!codecsRegistered) {
            PayloadTypeRegistry.playS2C().register(BalanceSyncPayload.ID, BalanceSyncPayload.CODEC);
            PayloadTypeRegistry.playC2S().register(SellAllPayload.ID, SellAllPayload.CODEC);
            codecsRegistered = true;
        }
    }
}
