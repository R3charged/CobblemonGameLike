package com.r3charged.common.cobblemongameslike.mixin;

import com.r3charged.common.cobblemongameslike.BattleController;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject
            (
                    method = "<init>",
                    at = @At("TAIL")
            )
    private void init(GameConfig gameConfig, CallbackInfo ci)
    {
        /*
        ShoulderInstance.getInstance().changePerspective(Config.CLIENT.getDefaultPerspective());
        ForgeModConfigEvents.reloading(ShoulderSurfing.MODID).register(config ->
        {
            if(ModConfig.Type.CLIENT == config.getType())
            {
                Config.onConfigReload();
            }
        });*/
    }

    @Inject
            (
                    at = @At("HEAD"),
                    method = "tick"
            )
    private void onStartTick(CallbackInfo info)
    {
        if(Minecraft.getInstance().level != null)
        {
            BattleController.getInstance().tick();
        }
    }

    @Inject
            (
                    at = @At("HEAD"),
                    method = "handleKeybinds"
            )
    private void handleKeybinds(CallbackInfo info)
    {
        //KeyHandler.tick();
    }
}
