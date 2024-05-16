package com.r3charged.common.cobblemongameslike.keyframe;

import com.r3charged.common.cobblemongameslike.CameraOffseter;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class OffsetTransitionKeyframe extends OffsetKeyframe {

    private Entity targetEntity;
    public OffsetTransitionKeyframe(int ticStart, int tickTransition, double x, double y, double z, Entity newEntity) {
        super(ticStart, tickTransition, x, y, z);
        targetEntity = newEntity;
    }

    public void execute() {
        CameraOffseter.getInstance().transisitionTo(this.targetEntity,
                new Vec3(this.x, this.y, this.z), this.tickTransition);
    }
}
