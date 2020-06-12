package com.xy.tank;

import java.util.Random;

public enum Dir {
    L,U,R,D;
    private static Random random = new Random();
    public static Dir ramdomDir(){
        return values()[random.nextInt(values().length)];
    }
}
