package com.r3charged.common.cobblemongameslike.keyframe;

import com.r3charged.common.cobblemongameslike.BattleController;

public class CameraLockKeyframe implements Keyframe {

    private int startTick;
    private boolean lock;

    public CameraLockKeyframe(int startTock, boolean lock) {
        this.startTick = startTock;
        this.lock = lock;
    }

    @Override
    public int getStartTick() {
        return startTick;
    }

    @Override
    public void execute() {
        BattleController.getInstance().setCameraLocked(lock);
    }
}
