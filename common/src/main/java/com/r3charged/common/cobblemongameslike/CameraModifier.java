package com.r3charged.common.cobblemongameslike;

import com.r3charged.common.cobblemongameslike.mixin.CameraAccessor;
import net.minecraft.client.Camera;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CameraModifier {

    private static final CameraModifier INSTANCE = new CameraModifier();

    int tick = 0;

    private int tickDuration;

    private double oldX;
    private double oldY;
    private double oldZ;
    private float oldYRot;
    private float oldXRot;

    private double x;
    private double y;
    private double z;
    private float yRot;
    private float xRot;

    private Entity oldEntity;
    private double targetX;
    private double targetY;
    private double targetZ;
    private float targetYRotOffset;
    private float targetXRotOffset;

    private float targetYRot;
    private float targetXRot;
    public boolean doTurn = false;

    private double zoom;
    private double ozoom;
    private double targetzoom;
    private boolean doZoom = true;



    private CameraModifier() {
        //initializeOffset(-2, .25, -2);
        tickDuration = 1;
        zoom = 1;
        ozoom = 1;
        targetzoom = 1;
        initializeOffset(0, 0, 0);
    }

    public void tick() {
        tick++;
        oldX = x;
        oldY = y;
        oldZ = z;

        double progress = ((double) tick) / ((double) tickDuration);
        x = Mth.lerp(progress, x, targetX);
        y = Mth.lerp(progress, y, targetY);
        z = Mth.lerp(progress, z, targetZ);

        oldYRot = yRot;
        oldXRot = xRot;
        yRot = Mth.rotLerp((float) progress, yRot, targetYRotOffset);
        xRot = Mth.rotLerp((float) progress, xRot, targetXRotOffset);

        ozoom = zoom;

        zoom = Mth.lerp(progress, zoom, targetzoom);

        if (doTurn) {
            Entity cameraEntity = Minecraft.getInstance().getCameraEntity();
            cameraEntity.setXRot(Mth.rotLerp((float) progress, cameraEntity.getXRot(), targetXRot));
            cameraEntity.setYRot(Mth.rotLerp((float) progress, cameraEntity.getYRot(), targetYRot));
            if (targetXRot == cameraEntity.getXRot() && targetYRot == cameraEntity.getYRot()) {
                doTurn = false;
            }
        }
        //System.out.println(zoom);
    }

    public void initializeOffset(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        oldX = x;
        oldY = y;
        oldZ = z;
        targetX = x;
        targetY = y;
        targetZ = z;
    }

    public void setTarget(double x, double y, double z, int tickDuration) {
        targetX = x;
        targetY = y;
        targetZ = z;
        this.tick = 0;
        this.tickDuration = tickDuration;
    }


    public void rotateToFace(Entity entity, int tickDuration) {
        Vec3 position = entity.getEyePosition();
        Entity cameraEntity = Minecraft.getInstance().getCameraEntity();
        if (cameraEntity != null) {
            Vec3 eyePos = cameraEntity.getEyePosition();
            // Calculate the difference in position between the camera and the target entity
            double dx = position.x - eyePos.x;
            double dy = position.y - eyePos.y;
            double dz = position.z - eyePos.z;

            // Calculate the distance to the target entity on the XZ plane
            double distanceXZ = Math.sqrt(dx * dx + dz * dz);

            // Calculate the target yaw (horizontal rotation)
            double targetYaw = Math.toDegrees(Math.atan2(dz, dx)) - 90.0;

            // Calculate the target pitch (vertical rotation)
            double targetPitch = -Math.toDegrees(Math.atan2(dy, distanceXZ));

            this.targetXRot = (float) targetPitch;
            this.targetYRot = (float) targetYaw;
            this.tickDuration = tickDuration;
            this.tick = 0;
            doTurn = true;
        }
    }


    public boolean shouldModifyCamera() {
        return BattleController.getInstance().isInBattle();
    }
    public void offsetCamera(Camera camera, Level level, float partialTick) {
        if (!shouldModifyCamera()) {
            return;
        }
        if (x*y*z == 0) {
            Minecraft.getInstance().options.setCameraType(CameraType.FIRST_PERSON);
        } else {
            Minecraft.getInstance().options.setCameraType(CameraType.THIRD_PERSON_BACK);
        }
        CameraAccessor accessor = ((CameraAccessor) camera);
        float pitch = Mth.rotLerp(partialTick, oldXRot, xRot) + camera.getEntity().getViewXRot(partialTick);
        float yaw = Mth.rotLerp(partialTick, oldYRot, yRot) + camera.getEntity().getViewYRot(partialTick);
        float cameraXRotWithOffset = Mth.clamp(pitch, -90F, 90F);
        float cameraYRotWithOffset = yaw;
        accessor.invokeSetRotation(cameraYRotWithOffset, cameraXRotWithOffset);

        double x = Mth.lerp(partialTick, camera.getEntity().xo, camera.getEntity().getX());
        double y = Mth.lerp(partialTick, camera.getEntity().yo, camera.getEntity().getY()) + Mth.lerp(partialTick, accessor.getEyeHeightOld(), accessor.getEyeHeight());
        double z = Mth.lerp(partialTick, camera.getEntity().zo, camera.getEntity().getZ());
        accessor.invokeSetPosition(x, y, z); //non offset Camera Pos

        double xoffset = Mth.lerp(partialTick, oldX, this.x);
        double yoffset = Mth.lerp(partialTick, oldY, this.y);
        double zoffset = Mth.lerp(partialTick, oldZ, this.z);
        accessor.invokeMove(xoffset, yoffset, zoffset);



    }

    public void setZoom(double initialZoom, double targetzoom, int tickDuration) {
        this.ozoom = initialZoom;
        this.zoom = initialZoom;
        this.targetzoom = targetzoom;
        this.tickDuration = tickDuration;
        this.tick=0;
    }

    public double getZoom(float partialTick, double originalZoom) {
        return BattleController.getInstance().isInBattle() ? Mth.lerp(partialTick, ozoom, zoom) * originalZoom : originalZoom;
    }

    public static CameraModifier getInstance() {
        return INSTANCE;
    }
}
