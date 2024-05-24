package com.r3charged.common.cobblemongameslike.keyframe;

import com.cobblemon.mod.common.battles.BattleRegistry;
import com.r3charged.common.cobblemongameslike.BattleController;
import com.r3charged.common.cobblemongameslike.CameraModifier;
import net.minecraft.world.entity.Entity;

public class FaceEnemyKeyframe implements Keyframe {

    private int startTick;
    private int duration;

    public FaceEnemyKeyframe(int startTick, int duration) {
        this.startTick = startTick;
        this.duration = duration;
    }

    @Override
    public int getStartTick() {
        return startTick;
    }

    @Override
    public void execute() {
        Entity entity = BattleController.getInstance().getEnemy();
        CameraModifier.getInstance().rotateToFace(entity, duration);
    }
}
