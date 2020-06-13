package com.xy.tank.strategy;

import com.xy.tank.*;

public class DefaultFireStrategy implements FireStrategy{
    @Override
    public void fire(Player t){
        int bX = t.getX() + ResourceMgr.badTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int bY = t.getY() + ResourceMgr.badTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
//        Dir[] dirs = Dir.values();
//        for(Dir d: dirs)
        TankFrame.INSTANCE.add(new Bullet(bX, bY, t.getDir(), t.getGroup()));
    }
}
