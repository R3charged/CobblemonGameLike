package com.r3charged.common.cobblemongameslike;

import net.minecraft.client.Camera;
import net.minecraft.world.level.Level;

public class WildCameraRenderer {

    private static final WildCameraRenderer INSTANCE = new WildCameraRenderer();

    private WildCameraRenderer() {

    }
    public void offsetCamera(Camera camera, Level level, float partialTick) {

    }
    public static WildCameraRenderer getInstance() {
        return INSTANCE;
    }
}
