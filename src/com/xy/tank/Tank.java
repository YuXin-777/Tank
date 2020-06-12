package com.xy.tank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Tank {
    private int x, y;
    private int oldX,oldY;
    private Dir dir;
    private boolean bL, bU, bR, bD;
    private boolean moving = true;
    public static final int SPEED = 5;
    private Group group = Group.BAD;
    private boolean live = true;
    private int width,height;
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
// 记录键盘是否被按下的变量

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.width = ResourceMgr.badTankU.getWidth();
        this.height = ResourceMgr.badTankU.getHeight();
    }

    public void paint(Graphics g) {
        if (!this.isLive()) return;
        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.badTankL, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.badTankU, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.badTankR, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.badTankD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        if (!moving) return;
        this.oldX = this.x;
        this.oldY = this.y;
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
        randomDir();
        if (r.nextInt(100) > 90)
            fire();
    }

    private Random r = new Random();
    private Random random = new Random();

    private void randomDir() {
        if (r.nextInt(100) > 90)
            this.dir = Dir.ramdomDir();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private void fire() {
        int bX = x + ResourceMgr.badTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int bY = y + ResourceMgr.badTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        TankFrame.INSTANCE.add(new Bullet(bX, bY, this.dir, this.group));
    }

    public void die() {
        this.setLive(false);
    }

    private void boundsCheck() {
        if (x < 0 || y < 0 || x + width > TankFrame.GAME_WIDTH || y + height> TankFrame.GAME_HEIGHT) {
            this.x = oldX;
            this.y = oldY;
        }
    }
}
