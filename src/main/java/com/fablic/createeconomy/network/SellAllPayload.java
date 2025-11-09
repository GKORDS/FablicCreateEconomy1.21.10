package com.fablic.createeconomy.network;

import com.fablic.createeconomy.CreateEconomyMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record SellAllPayload() implements CustomPayload {
    public static final Id<SellAllPayload> ID = new Id<>(Identifier.of(CreateEconomyMod.MOD_ID, "sell_all"));
    public static final PacketCodec<RegistryByteBuf, SellAllPayload> CODEC =
        CustomPayload.codecOf(SellAllPayload::write, SellAllPayload::new);

    public SellAllPayload(RegistryByteBuf buf) {
        this();
    }

    private void write(RegistryByteBuf buf) {
        // No payload data
    }

    @Override
    public Id<SellAllPayload> getId() {
        return ID;
    }
}
