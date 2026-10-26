package org.firstinspires.ftc.teamcode.levisToolbox;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

public class TelemetrySelector {
    Telemetry telemetry;
    ArrayList<String> labels;
    int selector;
    static boolean multipleObjects = false;

    public TelemetrySelector(Telemetry t) throws Exception {
        if (multipleObjects) {
            throw new Exception("Cannot have multiple TelemetrySelector objects!");
        }
        telemetry = t;
        selector = 0;
        multipleObjects = true;
    }

    public void addLabel(String label) {

    }

}
