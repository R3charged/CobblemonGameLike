package com.r3charged.common.cobblemongameslike.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.r3charged.common.cobblemongameslike.CameraOffseter;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Shadow
    private Camera mainCamera;

    @Inject
            (
                    method = "renderLevel",
                    at = @At
                            (
                                    value = "INVOKE",
                                    target = "Lnet/minecraft/client/Camera;setup(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/world/entity/Entity;ZZF)V",
                                    shift = At.Shift.AFTER
                            )
            )
    private void onCameraSetup(float f, long l, PoseStack poseStack, CallbackInfo ci)
    {

        CameraOffseter.getInstance().offsetCamera(this.mainCamera, Minecraft.getInstance().level, f);
    }
}
