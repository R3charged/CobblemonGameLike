package com.r3charged.common.cobblemongameslike;

import com.r3charged.common.cobblemongameslike.mixin.CameraAccessor;
import net.minecraft.client.Camera;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CameraOffseter {

    private static final CameraOffseter INSTANCE = new CameraOffseter();

    int tick = 0;

    private int tickDuration;

    private double oldX;
    private double oldY;
    private double oldZ;
    private double oldYRot;
    private double oldXRot;

    private double x;
    private double y;
    private double z;
    private double yRot;
    private double xRot;

    private Entity oldEntity;
    private double targetX;
    private double targetY;
    private double targetZ;
    private double targetYRot;
    private double targetXRot;




    private CameraOffseter() {
        //initializeOffset(-2, .25, -2);
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

        this.tickDuration = tickDuration;
    }

    public void transisitionTo(Entity newEntity, Vec3 newOffset, int tickDuration) {
        Entity oldEntity = Minecraft.getInstance().cameraEntity;
        if (oldEntity == null) {
            return;
        }
        Minecraft.getInstance().setCameraEntity(newEntity);

        // camera on new entity, at offset:
        double x = (oldEntity.getX() + this.x) - newEntity.getX();
        double y = oldEntity.getY() + this.y - newEntity.getY();
        double z = oldEntity.getZ() + this.z - newEntity.getZ();

        initializeOffset(x, y, z);
        setTarget(newOffset.x, newOffset.y, newOffset.z, tickDuration);
    }

    public void offsetCamera(Camera camera, Level level, float partialTick) {
        if (!BattleController.getInstance().isInBattle()) {
            return;
        }
        Minecraft.getInstance().options.setCameraType(CameraType.THIRD_PERSON_BACK);
        CameraAccessor accessor = ((CameraAccessor) camera);

        double x = Mth.lerp(partialTick, camera.getEntity().xo, camera.getEntity().getX());
        double y = Mth.lerp(partialTick, camera.getEntity().yo, camera.getEntity().getY()) + Mth.lerp(partialTick, accessor.getEyeHeightOld(), accessor.getEyeHeight());
        double z = Mth.lerp(partialTick, camera.getEntity().zo, camera.getEntity().getZ());
        accessor.invokeSetPosition(x, y, z); //non offset Camera Pos

        double xoffset = Mth.lerp(partialTick, oldX, this.x);
        double yoffset = Mth.lerp(partialTick, oldY, this.y);
        double zoffset = Mth.lerp(partialTick, oldZ, this.z);
        accessor.invokeMove(xoffset, yoffset, zoffset);
    }

    public static CameraOffseter getInstance() {
        return INSTANCE;
    }
}
