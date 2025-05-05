package ru.alexander1248.raspberry_story.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import ru.alexander1248.raspberry_story.RaspberryStory;
import ru.alexander1248.raspberry_story.configs.ConfigPayload;

import static ru.alexander1248.raspberry_story.RaspberryStory.CONFIG;


public class RaspberryStoryClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientConfigurationNetworking.registerGlobalReceiver(
                ConfigPayload.ID,
                (configPayload, context) ->
                        RaspberryStory.LOGGER.info("Loading config from server!")
        );
        ClientPlayConnectionEvents.DISCONNECT.register(
                (handler, client) -> {
                    RaspberryStory.LOGGER.info("Loading default config!");
                    CONFIG.load();
                }
        );
    }
}
