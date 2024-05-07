package com.r3charged.common.cobblemongameslike;

import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public class WildCameraInstance {
    private static final WildCameraInstance CAMERA_INSTANCE = new WildCameraInstance();

    private final int TICKS_TO_ZOOM_IN = 100;
    private final int TICKS_IN_CRY = 100;
    private final int TICKS_TO_ZOOM_OUT = 100;

    private boolean inBattle;
    private Vec3 targetOffset;
    private double targetPitchOffset;
    private double targetYawOffset;
    private double targetFov;
    private Vec3 offset;
    private Vec3 lastOffset;

    private double pitchOffset; //rotation offset from player head rotation (to reduce number of mixins) might not be
    private double yawOffset;
    private double fov;

    private double overlayOpacity;

    private WildCameraInstance() {
        super();
    }

    public void tick() {

    }

    public void onBattleStart() {
        offset = new Vec3(0,0,0);
        fov = Minecraft.getInstance().options.fov().get();
    }
    /*
    public void changePerspective(Perspective perspective)
    {
        Minecraft.getInstance().options.setCameraType(perspective.getCameraType());
        this.doShoulderSurfing = Perspective.SHOULDER_SURFING.equals(perspective);
    }*/
    public WildCameraInstance getInstance() {
        return CAMERA_INSTANCE;
    }

    public boolean isInWildBattle() {
        return inBattle;
    }

    public void setInWildBattle(boolean inBattle) {
        this.inBattle = inBattle;
    }
}
