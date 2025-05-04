package ru.alexander1248.raspberry_story;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alexander1248.raspberry_story.configs.RaspberryStoryConfig;
import ru.alexander1248.raspberry_story.configs.RaspberryStoryServerConfig;

public class RaspberryStory implements ModInitializer {
    public static final String MOD_ID = "Raspberry Story";
    public static final CustomPayload.Id<RaspberryStoryConfig> configId = new CustomPayload.Id<>(Identifier.of("raspberry_story:config"));

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static RaspberryStoryConfig CONFIG;
    @Override
    public void onInitialize() {
        var serverConfig = RaspberryStoryServerConfig.createAndLoad();
        CONFIG = new RaspberryStoryConfig(serverConfig);


        PayloadTypeRegistry.configurationS2C().register(configId, new PacketCodec<>() {
            @Override
            public RaspberryStoryConfig decode(PacketByteBuf buf) {
                return new RaspberryStoryConfig(buf);
            }

            @Override
            public void encode(PacketByteBuf buf, RaspberryStoryConfig value) {
                value.write(buf);
            }
        });

        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
            ServerPlayConnectionEvents.JOIN.register(
                    (handler, sender, server) -> {
                        RaspberryStory.LOGGER.info("Sending config to client {}!", handler.player.getDisplayName());
                        sender.sendPacket(CONFIG);
                    });
        }
    }
}
