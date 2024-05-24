package com.r3charged.common.cobblemongameslike.keyframe;

import com.r3charged.common.cobblemongameslike.CameraModifier;

public class CameraModifierKeyframe {

    private int startTick;
    private int duration;

    private double xOffset;
    private double yOffset;
    private double zOffset;

    private float yRot;
    private float xRot;

    private double zoom;


    public CameraModifierKeyframe() {
        startTick = 0;
        duration = 1;
        xOffset = 0;
        yOffset = 0;
        zOffset = 0;
        yRot = 0;
        xRot = 0;
        zoom = 1;
    }

    public CameraModifierKeyframe setOffset(double x, double y, double z) {
        this.xOffset = x;
        this.yOffset = y;
        this.zOffset = z;

        return this;
    }

    public CameraModifierKeyframe setRotation(float yRot, float xRot) {
        this.xRot = xRot;
        this.yRot = yRot;
        return this;
    }

    public CameraModifierKeyframe setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public CameraModifierKeyframe setStartTick(int startTick) {
        this.startTick = startTick;
        return this;
    }

    public CameraModifierKeyframe setZoom(double zoom) {
        this.zoom = zoom;
        return this;
    }

    public void execute() {

    }
}
