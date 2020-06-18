package com.xy.tank;

import java.awt.*;

public class Bullet extends AbstractGameObject{
    private static final int SPEED = 10;
    private int x, y;
    private Dir dir;
    private boolean live = true;
    private Group group;
    private Rectangle rect;
    private int w = ResourceMgr.bulletU.getWidth();
    private int h = ResourceMgr.bulletU.getHeight();
    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        rect = new Rectangle(x, y, w, h);
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void paint(Graphics g) {
        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
        move();
        rect.x = x;
        rect.y = y;
    }

    private void move() {
        switch (dir) {
            case L:
                x -= SPEED;
                break;
            case U:
                y -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case D:
                y += SPEED;
                break;
        }
        boundsCheck();
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void collidsWithTank(Tank tank) {
        if(!this.isLive() || !tank.isLive()) return;
        if(this.group == tank.getGroup()) return;
        Rectangle rect = new Rectangle(x, y, ResourceMgr.bulletU.getWidth(), ResourceMgr.bulletU.getHeight());
        Rectangle rectTank = new Rectangle(tank.getX(), tank.getY(), ResourceMgr.goodTankU.getWidth(),
                ResourceMgr.goodTankU.getHeight());
        if(rect.intersects(rectTank)){
            this.die();
            tank.die();
        }

    }
    public void die(){
        this.setLive(false);
        TankFrame.INSTANCE.add(new Explode(x,y));
    }

    private void boundsCheck() {
        if (x < 0 || y < 30 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            live = false;
        }
    }
    public Rectangle getRect(){
        return rect;
    }
}
