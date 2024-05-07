package com.r3charged.common.cobblemongameslike.mixin;

import com.cobblemon.mod.common.api.battles.interpreter.BattleMessage;
import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import kotlin.jvm.functions.Function1;
import org.spongepowered.asm.mixin.Mixin;
import com.cobblemon.mod.common.battles.ShowdownInterpreter;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ShowdownInterpreter.class, remap = false)
public class ShowdownInterpreterMixin {

    @Inject(
            method = "handleSwitchInstruction",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/cobblemon/mod/common/api/battles/model/actor/BattleActor;getStillSendingOutCount()I",
                    shift = At.Shift.BEFORE
            )
    )
    private void onSendPokemonPre(PokemonBattle battle, BattleActor battleActor, BattleMessage publicMessage, BattleMessage privateMessage, CallbackInfo ci) {
        System.out.println("SEND OUT ANIMATION STARTED");
    }

    @Inject(
            method = "handleSwitchInstruction$lambda$6",
            at = @At("HEAD")
    )
    private static void onSendPokemonPost(Function1 $tmp0, Object p0, CallbackInfoReturnable<Integer> cir) {
        PokemonEntity pokemon = (PokemonEntity) p0;
        System.out.println("PostSendPokemon");
        System.out.println("SEND OUT ANIMATION STARTED" + (p0.getClass()));
        //$tmp0.invoke(p0); //actor.stillSendingOutCount--

    }
}
