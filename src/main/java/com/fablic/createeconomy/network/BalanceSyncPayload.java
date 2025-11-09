package com.fablic.createeconomy.network;

import com.fablic.createeconomy.CreateEconomyMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record BalanceSyncPayload(long balance) implements CustomPayload {
    public static final Id<BalanceSyncPayload> ID = new Id<>(Identifier.of(CreateEconomyMod.MOD_ID, "balance_sync"));
    public static final PacketCodec<RegistryByteBuf, BalanceSyncPayload> CODEC =
        CustomPayload.codecOf(BalanceSyncPayload::write, BalanceSyncPayload::new);

    public BalanceSyncPayload(RegistryByteBuf buf) {
        this(buf.readVarLong());
    }

    private void write(RegistryByteBuf buf) {
        buf.writeVarLong(balance);
    }

    @Override
    public Id<BalanceSyncPayload> getId() {
        return ID;
    }
}
