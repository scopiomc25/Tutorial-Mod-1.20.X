package net.scopiomc.tutorialmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties STRAWBERRY = (new FoodProperties.Builder().alwaysEat().nutrition(5)
            .saturationMod(0.5F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20, 3), 0.1F)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 2),1F)).build();
}
