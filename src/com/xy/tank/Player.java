package com.xy.tank;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    private int x,y;
    private Dir dir;
    private boolean bL,bU,bR,bD;
    private boolean moving = false;
    public static final int SPEED = 5;
    private Group group = Group.GOOD;
    private TankFrame tf;
    private boolean live = true;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
// 记录键盘是否被按下的变量

    public Player(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if(!this.isLive()) return;
        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.goodTankL, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.goodTankU, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.goodTankR, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.goodTankD, x, y, null);
                break;
        }
        move();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_LEFT: bL = true;  break;
            case KeyEvent.VK_RIGHT: bR = true;  break;
            case KeyEvent.VK_UP: bU = true;  break;
            case KeyEvent.VK_DOWN: bD = true;  break;
        }
        setMainDir();
    }

    private void setMainDir() {
        if(!bL && !bU && !bR && !bD){
            moving = false;
        }
        else {
            moving = true;
            if (bL && !bU && !bR && !bD) {
                dir = Dir.L;
            }
            if (!bL && bU && !bR && !bD) {
                dir = Dir.U;
            }
            if (!bL && !bU && bR && !bD) {
                dir = Dir.R;
            }
            if (!bL && !bU && !bR && bD) {
                dir = Dir.D;
            }
        }
    }

    private void move(){
        if(!moving) return;
        switch (dir){
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
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_LEFT: bL = false;  break;
            case KeyEvent.VK_RIGHT: bR = false;  break;
            case KeyEvent.VK_UP: bU = false;  break;
            case KeyEvent.VK_DOWN: bD = false;  break;
            case KeyEvent.VK_CONTROL: fire();break;
        }
        setMainDir();
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
        int bX = x + ResourceMgr.badTankU.getWidth()/2 - ResourceMgr.bulletU.getWidth()/2;
        int bY = y + ResourceMgr.badTankU.getHeight()/2 - ResourceMgr.bulletU.getHeight()/2;
        TankFrame.INSTANCE.add(new Bullet(bX,bY,dir,group));
    }

    public void die() {
        this.setLive(false);
    }
}
