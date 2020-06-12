package com.xy.tank;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Frame tf = new TankFrame();
        tf.setVisible(true);

        for(;;){
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tf.repaint();
        }
    }


}
