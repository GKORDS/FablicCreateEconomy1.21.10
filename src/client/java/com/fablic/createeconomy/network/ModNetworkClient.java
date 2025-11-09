package com.fablic.createeconomy.network;

import com.fablic.createeconomy.MoneyModClient;
import com.fablic.createeconomy.network.payload.SyncBalancePayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public final class ModNetworkClient {
    private ModNetworkClient() {
    }

    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(SyncBalancePayload.ID, (payload, context) ->
            context.client().execute(() -> MoneyModClient.setClientBalance(payload.balance()))
        );
    }
}
