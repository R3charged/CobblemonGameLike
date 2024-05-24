package com.r3charged.common.cobblemongameslike;

import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class Transform {

    public static Vec3 rotateVec(Vec3 vec, double pitch, double yaw) {
        // Convert pitch and yaw to radians
        double pitchRad = Math.toRadians(pitch);
        double yawRad = Math.toRadians(yaw);

        // Apply yaw rotation
        double cosYaw = Math.cos(yawRad);
        double sinYaw = Math.sin(yawRad);
        double xYaw = vec.x * cosYaw - vec.z * sinYaw;
        double zYaw = vec.x * sinYaw + vec.z * cosYaw;

        // Apply pitch rotation
        double cosPitch = Math.cos(pitchRad);
        double sinPitch = Math.sin(pitchRad);
        double yPitch = vec.y * cosPitch - zYaw * sinPitch;
        double zPitch = vec.y * sinPitch + zYaw * cosPitch;

        return new Vec3(xYaw, yPitch, zPitch);
    }
}
