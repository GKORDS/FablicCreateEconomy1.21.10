package com.fablic.createeconomy.network;

import com.fablic.createeconomy.CreateEconomyModClient;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public final class ModNetworkClient {
    private ModNetworkClient() {}

    public static void register() {
        ModNetwork.registerCodecs();

        ClientPlayNetworking.registerGlobalReceiver(BalanceSyncPayload.ID, (payload, context) -> {
            long balance = payload.balance();
            context.client().execute(() -> CreateEconomyModClient.setCachedBalance(balance));
        });
    }

    public static void sendSellAllRequest() {
        ClientPlayNetworking.send(new SellAllPayload());
    }
}
