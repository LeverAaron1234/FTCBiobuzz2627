package org.firstinspires.ftc.teamcode.codersToolbox;

public class LevisFunctions {
    public static double round(double val, double places) {
        double temp = val * (Math.pow(10, places));
        temp = Math.round(temp);
        return temp / Math.pow(10, places);
    }
}
