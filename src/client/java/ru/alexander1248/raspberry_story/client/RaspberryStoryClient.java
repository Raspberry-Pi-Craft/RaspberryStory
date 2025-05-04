package ru.alexander1248.raspberry_story.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import ru.alexander1248.raspberry_story.RaspberryStory;
import ru.alexander1248.raspberry_story.configs.RaspberryStoryConfig;

import static ru.alexander1248.raspberry_story.RaspberryStory.configId;


public class RaspberryStoryClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientConfigurationNetworking.registerGlobalReceiver(
                configId,
                (raspberryStoryConfig, context) -> {
                    RaspberryStory.LOGGER.info("Loading config from server!");
                    RaspberryStory.CONFIG = raspberryStoryConfig;
                }
        );
    }
}
