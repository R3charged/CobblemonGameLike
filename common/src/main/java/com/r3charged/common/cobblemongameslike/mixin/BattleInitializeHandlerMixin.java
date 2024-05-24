package com.r3charged.common.cobblemongameslike.mixin;

import com.cobblemon.mod.common.client.net.battle.BattleInitializeHandler;
import com.cobblemon.mod.common.net.messages.client.battle.BattleInitializePacket;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.r3charged.common.cobblemongameslike.BattleController;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value=BattleInitializeHandler.class)
public class BattleInitializeHandlerMixin {

    @Inject(method = "handle(Lcom/cobblemon/mod/common/net/messages/client/battle/BattleInitializePacket;Lnet/minecraft/client/Minecraft;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/Minecraft;setScreen(Lnet/minecraft/client/gui/screens/Screen;)V"),
                    cancellable = true)
    private void injectSetScreen(BattleInitializePacket packet, Minecraft client, CallbackInfo ci) {
        // Your custom statement here
        // For example, setting a custom screen
        BattleController.getInstance().onStartBattle();
        // Cancel the original call to setScreen
        ci.cancel();
    }

}
