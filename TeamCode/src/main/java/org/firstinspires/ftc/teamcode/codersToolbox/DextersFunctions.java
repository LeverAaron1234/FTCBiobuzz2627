package org.firstinspires.ftc.teamcode.codersToolbox;

public class DextersFunctions {
    public static double clamp(double val, double mincemeat,double maxcemeat) {
        return Math.max(Math.min(val, maxcemeat), mincemeat);
    }
}