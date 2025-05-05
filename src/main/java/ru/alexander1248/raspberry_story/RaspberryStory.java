package ru.alexander1248.raspberry_story;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerConfigurationConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerConfigurationNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerConfigurationNetworkHandler;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alexander1248.raspberry_story.configs.RaspberryStoryConfig;
import ru.alexander1248.raspberry_story.configs.ConfigPayload;


public class RaspberryStory implements ModInitializer {
    public static final String MOD_ID = "Raspberry Story";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static RaspberryStoryConfig CONFIG;
    @Override
    public void onInitialize() {
        CONFIG = RaspberryStoryConfig.createAndLoad();
        PayloadTypeRegistry.configurationS2C().register(ConfigPayload.ID, ConfigPayload.PACKET_CODEC);

        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER)
            ServerConfigurationConnectionEvents.CONFIGURE.register(
                    (handler, server) ->
                            ServerConfigurationNetworking.send(handler, new ConfigPayload()));

    }
}
