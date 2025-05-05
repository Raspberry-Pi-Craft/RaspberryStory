package ru.alexander1248.raspberry_story.configs;

import io.wispforest.owo.config.annotation.Config;


@Config(name = "raspberry_story", wrapperName = "RaspberryStoryConfig", saveOnModification = false)
public class RaspberryStoryConfigModel {
    public int bannerLayerLimit = 16;
}
