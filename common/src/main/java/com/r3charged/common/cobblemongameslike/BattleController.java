package com.r3charged.common.cobblemongameslike;

import com.cobblemon.mod.common.client.CobblemonClient;
import com.cobblemon.mod.common.client.battle.ClientBattle;
import com.r3charged.common.cobblemongameslike.keyframe.OffsetKeyframe;

public class BattleController {

    private static final BattleController INSTANCE = new BattleController();
    private static Timeline timeline = new Timeline();

    private int tick;

    private BattleController() {
        tick = 0;
        timeline.add(new OffsetKeyframe(40, 60, -2, .25, -2));
    }

    public void tick() {
        if (!isInBattle()) {
            return;
        }
        timeline.tick();
        CameraOffseter.getInstance().tick();

    }

    public int getTick() {
        return tick;
    }

    public boolean isInBattle() {
        ClientBattle battle = CobblemonClient.INSTANCE.getBattle();
        if (battle == null) return false;
        return !battle.getMinimised();
    }

    public void startBattle() {
        timeline.reset();
    }

    public boolean isPvW() {
        return false;
    }

    public static BattleController getInstance() {
        return INSTANCE;
    }
}
