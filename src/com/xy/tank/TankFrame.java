package com.xy.tank;

import chainofresponsibility.BulletTankCollider;
import chainofresponsibility.BulletWallCollider;
import chainofresponsibility.Collider;
import chainofresponsibility.ColliderChain;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TankFrame extends Frame {
    public static final TankFrame INSTANCE = new TankFrame();
    private Player myTank;
    List<AbstractGameObject> objs = new ArrayList<>();
    List<Collider> colliders;
    public static final int GAME_WIDTH =800, GAME_HEIGHT=600;
    ColliderChain colliderChain = new ColliderChain();
    TankFrame(){
        this.setTitle("tank war");
        this.setLocation(400,100);
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        // Observer
        this.addKeyListener(new TankKeyListener());
        initGameObjects();
    }

    private void initGameObjects() {
        myTank = new Player(100,100, Dir.R,Group.GOOD);
        //enemy = new Tank(200,200,Dir.D, Group.BAD);
        objs = new ArrayList<>();
        int tankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));
        for (int i = 0; i < tankCount; i++) {
            this.add(new Tank(100 + 50 *i,200,Dir.D,Group.BAD));
        }
        this.add(new Wall(300,200,400,50));
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("bullets:"+ bullets.size(),10,50);
//        g.drawString("enemies:"+ tanks.size(),10,70);
        g.setColor(c);
        myTank.paint(g);
        for (int i = 0; i < objs.size(); i++) {
            AbstractGameObject go = objs.get(i);
            if(!go.isLive()) {
                objs.remove(i);
                break;
            }
//            if(go instanceof Bullet){
                for(int j = 0 ; j < objs.size();j++){
                    AbstractGameObject go2 = objs.get(j);
                    colliderChain.collide(go,go2);


                }
//            }
            if(objs.get(i).isLive())objs.get(i).paint(g);
        }
    }

    public void add(AbstractGameObject go){
        objs.add(go);
    }

    private class TankKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }
    }
}
