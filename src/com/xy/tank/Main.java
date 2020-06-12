package com.xy.tank;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        //TankFrame tf = new TankFrame();
        TankFrame.INSTANCE.setVisible(true);

        for(;;){
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TankFrame.INSTANCE.repaint();
        }
    }


}
