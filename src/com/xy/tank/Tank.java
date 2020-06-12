package com.xy.tank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Tank {
    private int x,y;
    private Dir dir;
    private boolean bL,bU,bR,bD;
    private boolean moving = false;
    public static final int SPEED = 5;
    private Group group;
    private TankFrame tf;
    // 记录键盘是否被按下的变量

    public Tank(int x, int y, Dir dir,Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if(this.group == Group.GOOD) {
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
        }
        else {
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

    private void fire() {
        tf.add(new Bullet(x,y,dir,group));
    }
}
