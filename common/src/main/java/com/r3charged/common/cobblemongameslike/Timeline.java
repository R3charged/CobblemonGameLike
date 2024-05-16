package com.r3charged.common.cobblemongameslike;

import com.r3charged.common.cobblemongameslike.keyframe.Keyframe;

import java.util.LinkedList;
import java.util.Queue;

public class Timeline {

    private Queue<Keyframe> queue = new LinkedList<>();
    private Queue<Keyframe> completed_queue = new LinkedList<>();
    private int tick;

    public void tick() {
        int nextTick = Integer.MAX_VALUE;
        if (queue.peek() != null)
            nextTick = queue.peek().getStartTick();
        if (nextTick <= tick) {
            Keyframe frame = queue.remove();
            frame.execute();
            completed_queue.add(frame);
        }
        tick++;
    }

    public void reset() {
        tick = 0;
        while (!queue.isEmpty()) {
            completed_queue.add(queue.remove());
        }
        queue = completed_queue;
        completed_queue = new LinkedList<>();
    }

    public void add(Keyframe keyframe) {
        queue.add(keyframe);
    }
}
