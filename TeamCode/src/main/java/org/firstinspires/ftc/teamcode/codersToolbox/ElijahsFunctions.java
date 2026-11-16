package org.firstinspires.ftc.teamcode.codersToolbox;

import androidx.annotation.NonNull;

import java.util.HashMap;

public class ElijahsFunctions {
    @NonNull
    public static HashMap<String, Double> toFieldCentric(double drive, double strafe, double turn, double heading) {
        HashMap<String, Double> tempArray = new HashMap<String, Double>();
        tempArray.put("drive", strafe * Math.cos(heading) - drive * Math.sin(heading));
        tempArray.put("strafe", strafe * Math.sin(heading) + drive * Math.cos(heading));
        tempArray.put("turn", turn);
        return tempArray;
    }
    
}