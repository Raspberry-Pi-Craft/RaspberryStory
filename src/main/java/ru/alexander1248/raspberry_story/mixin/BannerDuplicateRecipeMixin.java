package ru.alexander1248.raspberry_story.mixin;

import net.minecraft.recipe.BannerDuplicateRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import ru.alexander1248.raspberry_story.RaspberryStory;

@Mixin(BannerDuplicateRecipe.class)
public class BannerDuplicateRecipeMixin {
    @ModifyConstant(method = "matches(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/world/World;)Z", constant = @Constant(intValue = 6))
    public int getLimitMatches(int original) {
        return RaspberryStory.CONFIG.bannerLayerLimit();
    }

    @ModifyConstant(method = "craft(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/registry/RegistryWrapper$WrapperLookup;)Lnet/minecraft/item/ItemStack;", constant = @Constant(intValue = 6))
    public int getLimitCraft(int original) {
        return RaspberryStory.CONFIG.bannerLayerLimit();
    }
}