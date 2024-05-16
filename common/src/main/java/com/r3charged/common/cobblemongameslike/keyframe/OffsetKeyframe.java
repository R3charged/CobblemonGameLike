package com.r3charged.common.cobblemongameslike.keyframe;

import com.r3charged.common.cobblemongameslike.CameraOffseter;

public class OffsetKeyframe implements Keyframe {

    protected int startTick;

    protected int tickTransition;

    protected double x;
    protected double y;
    protected double z;


    public OffsetKeyframe(int ticStart, int tickTransition, double x, double y, double z) {
        this.startTick = ticStart;
        this.tickTransition = tickTransition;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getStartTick() {
        return startTick;
    }

    public void execute() {
        CameraOffseter.getInstance().setTarget(this.x, this.y, this.z, this.tickTransition);
    }

}
