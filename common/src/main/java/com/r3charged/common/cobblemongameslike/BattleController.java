package com.r3charged.common.cobblemongameslike;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.battles.BattleRegistry;
import com.cobblemon.mod.common.battles.BattleSide;
import com.cobblemon.mod.common.client.CobblemonClient;
import com.cobblemon.mod.common.client.battle.ClientBattle;
import com.r3charged.common.cobblemongameslike.keyframe.FaceEnemyKeyframe;
import com.r3charged.common.cobblemongameslike.keyframe.OffsetKeyframe;
import com.r3charged.common.cobblemongameslike.keyframe.ZoomInKeyframe;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;

public class BattleController {

    private static final BattleController INSTANCE = new BattleController();
    private static Timeline timeline;

    private boolean cameraLocked = false;

    private BattleController() {
        timeline = new Timeline();
        timeline.add(new FaceEnemyKeyframe(0, 5));
        timeline.add(new ZoomInKeyframe(20, 1,.5, 5));
        timeline.add(new ZoomInKeyframe(50, .5,1, 30));

        timeline.add(new OffsetKeyframe(50, 30, -2, .25, -2));
    }

    public void tick() {
        if (!isInBattle()) {
            return;
        }
        timeline.tick();
        CameraModifier.getInstance().tick();

    }


    public boolean isInBattle() {
        ClientBattle battle = CobblemonClient.INSTANCE.getBattle();
        if (battle == null) return false;
        return !battle.getMinimised();
    }

    public void onStartBattle() {
        //TODO Check type of battle
        timeline.reset();
        Minecraft.getInstance().options.hideGui = true;
    }

    public void onExitBattle() {

    }

    public boolean isPvW() {
        return false;
    }

    public void setCameraLocked(boolean cameraLocked) {
        this.cameraLocked = cameraLocked;
    }

    public boolean isCameraLocked() {
        return cameraLocked;
    }

    public boolean canShowGUI() {
        return timeline.finishedPlaying();
    }

    public Entity getEnemy() {

        PokemonBattle battle = BattleRegistry.INSTANCE.getBattleByParticipatingPlayerId(Minecraft.getInstance().player.getUUID());

        BattleActor actor = battle.getActor(Minecraft.getInstance().player.getUUID());
        for (BattleSide side : battle.getSides()) {
            if (!actor.getSide().equals(side)) {
                return side.getActivePokemon().get(0).getBattlePokemon().getEntity();
            }
        }
        System.out.println(actor);
        /*

        ClientBattleSide[] sides = CobblemonClient.INSTANCE.getBattle().getSides();
        ClientBattleActor enemy;
        for (ClientBattleSide side : sides) {
            for (ClientBattleActor actor : side.getActors()) {
                if (actor.getUuid() != Minecraft.getInstance().player.getUUID()) {
                    enemy = actor;
                }
            }
        }

        enemy.getActivePokemon().get(0).getBattlePokemon().*/
        return null;
    }

    public static BattleController getInstance() {
        return INSTANCE;
    }
}
