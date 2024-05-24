package com.r3charged.common.cobblemongameslike.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {

    private float alpha = .5f;  // Initial alpha value (fully opaque)

    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(GuiGraphics guiGraphics, float f, CallbackInfo ci) {
        // Slowly decrease the alpha value

        // Set the transparency for rendering HUD elements
        guiGraphics.pose().pushPose();
        RenderSystem.getShaderColor();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        guiGraphics.setColor(1f,1f,1f, 0f);
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void onRenderEnd(GuiGraphics guiGraphics, float f, CallbackInfo ci) {
        // Reset the transparency after rendering HUD elements
        guiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();

        RenderSystem.disableBlend();
        guiGraphics.pose().popPose();
    }
}