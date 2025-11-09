package com.fablic.createeconomy.network.payload;

import com.fablic.createeconomy.MoneyMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record SellAllInventoryPayload() implements CustomPayload {
    public static final Id<SellAllInventoryPayload> ID = new Id<>(MoneyMod.id("sell_all"));
    public static final PacketCodec<RegistryByteBuf, SellAllInventoryPayload> CODEC = PacketCodec.of(
        (value, buf) -> {
        },
        buf -> new SellAllInventoryPayload()
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
