package net.scopiomc.tutorialmod.item;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.scopiomc.tutorialmod.TutorialMod;
import net.scopiomc.tutorialmod.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier SAPPHIRE = TierSortingRegistry.registerTier(
            new ForgeTier(5, 2000, 13f, 4f, 25, ModTags.Blocks.NEEDS_SAPPHIRE_TOOL, () -> Ingredient.of(ModItems.SAPPHIRE.get())),
            ResourceLocation.fromNamespaceAndPath(TutorialMod.MODID, "sapphire"), List.of(Tiers.NETHERITE), List.of());
}
