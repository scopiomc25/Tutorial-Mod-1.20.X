package net.scopiomc.tutorialmod.item.custom;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SapphireStaffItem extends Item {
    public SapphireStaffItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide()) {
            int hitCount = 0;
            Vec3 look = pPlayer.getLookAngle();
            Vec3 start = pPlayer.getEyePosition();
            Vec3 end = start.add(look.multiply(30, 30, 30));

            for (int i = 0; i < 120 && hitCount < 5; i++) {
                Vec3 pos = start.add(look.multiply(i * 0.25, i * 0.25, i * 0.25));
                AABB searchBox = new AABB(pos.subtract(1, 1, 1), pos.add(1, 1, 1));
                for (LivingEntity target : pLevel.getEntitiesOfClass(LivingEntity.class, searchBox)) {
                    if (target != pPlayer && !target.isDeadOrDying()) {
                        target.hurt(pLevel.damageSources().magic(), 8 - hitCount);
                        target.setSecondsOnFire(10);
                        hitCount++;
                        if (hitCount >= 5) break;
                    }
                }
            }

            pPlayer.getCooldowns().addCooldown(this, 20);
        } if (pLevel.isClientSide()) {
            Vec3 look = pPlayer.getLookAngle();
            Vec3 start = pPlayer.getEyePosition();

            for (int i = 0; i < 120; i++) {
                Vec3 pos = start.add(look.multiply(i * 0.25, i * 0.25, i * 0.25));
                pLevel.addParticle(ParticleTypes.SONIC_BOOM, pos.x, pos.y, pos.z, 0, 0, 0);
            }
        }

        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.tutorialmod.staff.sapphire.1"));
        pTooltipComponents.add(Component.translatable("tooltip.tutorialmod.staff.sapphire.2"));
        pTooltipComponents.add(Component.translatable("tooltip.tutorialmod.staff.sapphire.3"));
        pTooltipComponents.add(Component.translatable("tooltip.tutorialmod.staff.sapphire.4"));

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
