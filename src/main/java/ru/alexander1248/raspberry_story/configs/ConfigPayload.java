package ru.alexander1248.raspberry_story.configs;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import ru.alexander1248.raspberry_story.RaspberryStory;


public class ConfigPayload implements CustomPayload {
    public static final CustomPayload.Id<ConfigPayload> ID = new CustomPayload.Id<>(
            Identifier.of("raspberry_story:config")
    );
    public static final PacketCodec<PacketByteBuf, ConfigPayload> PACKET_CODEC = new PacketCodec<>() {
        @Override
        public void encode(PacketByteBuf buf, ConfigPayload value) {
            value.write(buf);
        }

        @Override
        public ConfigPayload decode(PacketByteBuf buf) {
            return new ConfigPayload(buf);
        }
    };

    public ConfigPayload() {}
    public ConfigPayload(PacketByteBuf buf) {
        RaspberryStory.CONFIG.bannerLayerLimit(buf.readInt());
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public void write(PacketByteBuf buf) {
        buf.writeInt(RaspberryStory.CONFIG.bannerLayerLimit());
    }
}
