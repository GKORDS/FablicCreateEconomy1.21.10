package com.fablic.createeconomy.network.payload;

import com.fablic.createeconomy.MoneyMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record SyncBalancePayload(long balance) implements CustomPayload {
    public static final Id<SyncBalancePayload> ID = new Id<>(MoneyMod.id("sync_balance"));
    public static final PacketCodec<RegistryByteBuf, SyncBalancePayload> CODEC = PacketCodec.of(
        (value, buf) -> buf.writeVarLong(value.balance()),
        buf -> new SyncBalancePayload(buf.readVarLong())
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
