package ru.alexander1248.raspberry_story.configs;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;

import static ru.alexander1248.raspberry_story.RaspberryStory.configId;


public class RaspberryStoryConfig implements CustomPayload {
    private final int bannerLayerLimit;

    public RaspberryStoryConfig(ru.alexander1248.raspberry_story.configs.RaspberryStoryServerConfig config) {
        bannerLayerLimit = config.bannerLayerLimit();
    }

    public RaspberryStoryConfig(PacketByteBuf buf) {
        bannerLayerLimit = buf.readInt();
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return configId;
    }

    public int bannerLayerLimit() {
        return bannerLayerLimit;
    }

    public void write(PacketByteBuf buf) {
        buf.writeInt(bannerLayerLimit);
    }
}
