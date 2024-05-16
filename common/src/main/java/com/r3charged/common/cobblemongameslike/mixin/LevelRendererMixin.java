package com.r3charged.common.cobblemongameslike.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.r3charged.common.cobblemongameslike.BattleController;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {

    @Inject
            (
                    method = "renderEntity",
                    at = @At("HEAD"),
                    cancellable = true
            )
    public void preRender(Entity entity, double x, double y, double z, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, CallbackInfo ci)
    {
    }
}
