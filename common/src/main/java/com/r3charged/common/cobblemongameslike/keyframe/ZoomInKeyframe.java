package com.r3charged.common.cobblemongameslike.keyframe;

import com.r3charged.common.cobblemongameslike.CameraModifier;
import net.minecraft.client.Minecraft;

public class ZoomInKeyframe implements Keyframe {

    private int startTick;
    private double zoom;
    private double initzoom;

    private int tickDuration;

    public ZoomInKeyframe(int startTick,double initzoom, double zoom, int tickDuration) {
        this.startTick = startTick;
        this.zoom = zoom;
        this.initzoom = initzoom;
        this.tickDuration = tickDuration;
    }


    @Override
    public int getStartTick() {
        return startTick;
    }

    @Override
    public void execute() {
        CameraModifier.getInstance().setZoom(initzoom, zoom, tickDuration);
    }
}
