package org.firstinspires.ftc.teamcode;


import static com.pedropathing.api.Paths.*;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import static com.pedropathing.ivy.Scheduler.schedule;

import com.pedropathing.ivy.Scheduler;
import com.pedropathing.paths.Path;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.interpolator.Interpolator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedro.Constants;

import java.util.Objects;


@Autonomous(preselectTeleOp = "MainTeleOp")
public final class TestAuto extends OpMode {


    /// Autos enum for selecting auto.
    public enum Autos {
        GardenSide ("Right side, near the garden"),
        ParkSide ("Left side, near the parking zone");

        private final String desc;

        private Autos(String description) {this.desc = description;}
    }

    Autos chosenAuto = null;

    public enum ConfigState {
        SelectAuto,
        ReadyToRun
    }

    ConfigState configState = ConfigState.SelectAuto;

    public void printToTelemetry(Telemetry telemetry, int cursorPos) {
        telemetry.addLine("Select an auto: (DPAD to move, A to select)");
        for (int i = 0; i < Autos.values().length; i++) {
            telemetry.addData((i == cursorPos)? "> " : "" + Autos.values()[i], Autos.values()[i].desc);
        }
        telemetry.update();
    }





    private Follower follower;


    /// GardenSide Auto Method
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose gardenSideStart = poseFactory.of(56, 10, 90);
    private final Pose gardenSideWait = poseFactory.of(56, 10, 90);
    private final Pose gardenSideGardenPickupIn = poseFactory.of(10, 10, 180);
    private final Pose gardenSideGardenPickupInControl1 = poseFactory.of(55.5899, 23.6629, 0);
    private final Pose gardenSideGardenPickupInControl2 = poseFactory.of(48.9584, 8.6629, 0);
    private final Pose gardenSideGardenPickupInSegment1Start = poseFactory.of(10, 10, 90);
    private final Pose gardenSideGardenPickupInSegment1End = poseFactory.of(10, 10, 180);
    private final Pose gardenSideGardenPickupInSegment2Heading = poseFactory.of(10, 10, 180);
    private final Pose gardenSideGardenPickupout = poseFactory.of(55.9213, 10.6022, 90);
    private final Pose gardenSideGardenPickupoutControl1 = poseFactory.of(24.3253, 17.7272, 0);
    private final Pose gardenSideGardenPickupoutControl2 = poseFactory.of(49.5483, 15.2753, 0);
    private final Pose gardenSideGardenPickupoutSegment1Start = poseFactory.of(55.9213, 10.6022, 180);
    private final Pose gardenSideGardenPickupoutSegment1End = poseFactory.of(55.9213, 10.6022, 90);
    private final Pose gardenSideFlowerPickupin = poseFactory.of(13.9067, 47.1798, 180);
    private final Pose gardenSideFlowerPickupout = poseFactory.of(54.7551, 10.464, 90);
    private final Pose gardenSidePark = poseFactory.of(11.5247, 94.8573, 0);
    private final Pose gardenSideParkControl1 = poseFactory.of(26.8427, 41.9079, 0);

    public Path gardenSideAuto() {
        return path(line(gardenSideStart, gardenSideWait).constant(gardenSideWait), curve(gardenSideWait, gardenSideGardenPickupInControl1, gardenSideGardenPickupInControl2, gardenSideGardenPickupIn).heading(Interpolator.piecewise().until(0.5, Interpolator.linear(gardenSideGardenPickupInSegment1Start, gardenSideGardenPickupInSegment1End)).until(1, Interpolator.constant(gardenSideGardenPickupInSegment2Heading))), curve(gardenSideGardenPickupIn, gardenSideGardenPickupoutControl1, gardenSideGardenPickupoutControl2, gardenSideGardenPickupout).heading(Interpolator.piecewise().until(1, Interpolator.linear(gardenSideGardenPickupoutSegment1Start, gardenSideGardenPickupoutSegment1End))), line(gardenSideGardenPickupout, gardenSideFlowerPickupin).linear(gardenSideGardenPickupout, gardenSideFlowerPickupin), line(gardenSideFlowerPickupin, gardenSideFlowerPickupout).linear(gardenSideFlowerPickupin, gardenSideFlowerPickupout), curve(gardenSideFlowerPickupout, gardenSideParkControl1, gardenSidePark).linear(gardenSideFlowerPickupout, gardenSidePark));
    }

    /// ParkSide Auto Method
    private final Pose parkSideStart = poseFactory.of(61, 132, 90);
    private final Pose parkSideWaitStart = poseFactory.of(61, 132, 270);
    private final Pose parkSideWait = poseFactory.of(61, 132, 270);
    private final Pose parkSidePickupIn = poseFactory.of(48.4697, 125.0281, 90);
    private final Pose parkSidePickupInControl1 = poseFactory.of(64.3449, 104.4101, 0);
    private final Pose parkSidePickupOut = poseFactory.of(61, 132, 270);
    private final Pose parkSidePickupOutControl1 = poseFactory.of(64.9719, 105.1067, 0);
    private final Pose parkSidePark = poseFactory.of(10.0225, 116.3876, 0);
    private final Pose parkSideParkControl1 = poseFactory.of(66.2539, 115.9034, 0);

    public Path parkSideAuto() {
        return path(line(parkSideWaitStart, parkSideWait).linear(parkSideWaitStart, parkSideWait), curve(parkSideWait, parkSidePickupInControl1, parkSidePickupIn).linear(parkSideWait, parkSidePickupIn), curve(parkSidePickupIn, parkSidePickupOutControl1, parkSidePickupOut).linear(parkSidePickupIn, parkSidePickupOut), curve(parkSidePickupOut, parkSideParkControl1, parkSidePark).linear(parkSidePickupOut, parkSidePark));
    }

    @Override
    public void init() {

        // User input to choose auto
        int cursorPos = 0;
        completeLoop:
        while (true) {
            switch (configState) {
                case SelectAuto:
                    // if 'A' is pressed, they have selected the auto, nothing else is required here.
                    if (gamepad1.aWasPressed()) {
                        chosenAuto = Autos.values()[cursorPos];
                        configState = ConfigState.ReadyToRun;
                    }
                    // 0 position at top, going down
                    // if 'UP' is pressed, -1, if 'DOWN' is pressed, +1
                    cursorPos += ((gamepad1.dpadUpWasPressed())? -1 : 0) + ((gamepad1.dpadDownWasPressed())? 1 : 0);
                    // clamp the cursor between 0 and the index of the last auto. (aka length-1, bc it starts at 0)
                    cursorPos = Math.min(Math.max(0,cursorPos),Autos.values().length - 1);
                    // see above (CTRL + click it)
                    printToTelemetry(telemetry, cursorPos);
                    break;
                case ReadyToRun:
                    // We have selected an auto, so here we just display it, then wait for start.
                    telemetry.addData("Running Auto", chosenAuto);
                    telemetry.update();
                    break completeLoop;
            }

        }

        Scheduler.reset();
        follower = Constants.create(hardwareMap);

        switch (chosenAuto) {
            case GardenSide:
                follower.setPose(gardenSideStart);
                break;
            case ParkSide:
                follower.setPose(parkSideStart);
                break;
            default:
                throw new IllegalStateException("ERROR: method start() --- No chosen auto");

        }

    }

    @Override
    public void start() {
        switch (chosenAuto) {
            case GardenSide:
                schedule(follow(follower, gardenSideAuto()));
                break;
            case ParkSide:
                schedule(follow(follower, parkSideAuto()));
                break;
            default:
                throw new IllegalStateException("ERROR: method start() --- No chosen auto");
        }

    }

    @Override
    public void loop() {

        follower.update();
        Scheduler.execute();
    }

}
