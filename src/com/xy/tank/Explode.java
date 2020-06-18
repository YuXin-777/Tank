package com.xy.tank;

import java.awt.*;

public class Explode extends AbstractGameObject{
    private int x, y;
    private int width,high;
    private int step;
    private boolean live = true;
    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = ResourceMgr.explodes[0].getWidth();
        this.high = ResourceMgr.explodes[0].getHeight();
        step = 0;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        if(!isLive()) return;
        g.drawImage(ResourceMgr.explodes[step],x,y,null);
        step++;
        if(step >= ResourceMgr.explodes.length){
            this.over();
        }
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    private void over() {
        this.live = false;
    }

}
