package ru.alexander1248.raspberry_story.client.mixin;

import net.minecraft.client.gui.screen.ingame.LoomScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import ru.alexander1248.raspberry_story.RaspberryStory;

@Mixin(LoomScreen.class)
public class LoomScreenMixin {
    @ModifyConstant(method = "onInventoryChanged", constant = @Constant(intValue = 6))
    private int getLimit(int original) {
        return RaspberryStory.CONFIG.bannerLayerLimit();
    }
}
