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

import org.firstinspires.ftc.teamcode.pedro.Constants;

import java.util.ArrayList;


@Autonomous(preselectTeleOp = "MainTeleOp")
public final class MainAuto extends OpMode {


    /// Autos enum for selecting auto.
    public enum Autos {
        GardenSide ("Right side, near the garden. Assumed to be working with a robot that is running ParkSide."),
        ParkSide ("Left side, near the parking zone. Assumed to be working with a robot that is running GardenSide"),
        GardenSideDoAll ("Similar start to GardenSide, meant for a teammate that ONLY can park"),
        Test ("Testing, testing 123...");

        private final String desc;
        private Pose start;
        private Path path;

        private Autos(String description) {this.desc = description;}
        public void setStart(Pose pose) {start = pose;}
        public Pose getStart() {return start;}
        public void setPath(Path p) {path = p;}
        public Path getPath() {return path;}
    }


    Autos chosenAuto = null;

    public enum ConfigState {
        SelectAuto,
        ReadyToRun
    }

    ConfigState configState = ConfigState.SelectAuto;

    public int cursorPos = 0;





    private Follower follower;


    /// GardenSide Auto Method
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose gardenSideStart = poseFactory.of(56, 10, 90);
    private final Pose gardenSideWait = poseFactory.of(56, 10.1, 90);
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

    /// GardenSide Auto, but we do everything (requires min 2sec pickup from flowers, and 1.5sec firing)
    private final Pose gardenSideDoAllStart = poseFactory.of(57, 10, 90);
    private final Pose gardenSideDoAllPath0 = poseFactory.of(57, 10.1, 90);
    private final Pose gardenSideDoAllPath1 = poseFactory.of(8.9348, 9.8326, 180);
    private final Pose gardenSideDoAllPath1Control1 = poseFactory.of(56.4416, 18.2438, 0);
    private final Pose gardenSideDoAllPath1Control2 = poseFactory.of(50.1652, 9.0449, 0);
    private final Pose gardenSideDoAllPath1Segment1Start = poseFactory.of(8.9348, 9.8326, 90);
    private final Pose gardenSideDoAllPath1Segment1End = poseFactory.of(8.9348, 9.8326, 180);
    private final Pose gardenSideDoAllPath1Segment2Heading = poseFactory.of(8.9348, 9.8326, 180);
    private final Pose gardenSideDoAllPath2 = poseFactory.of(57.2067, 120.1764, 270);
    private final Pose gardenSideDoAllPath2Control1 = poseFactory.of(33.409, 123.1708, 0);
    private final Pose gardenSideDoAllPath3 = poseFactory.of(47.1798, 128.0933, 90);
    private final Pose gardenSideDoAllPath3Segment1Start = poseFactory.of(47.1798, 128.0933, 270);
    private final Pose gardenSideDoAllPath3Segment1End = poseFactory.of(47.1798, 128.0933, 90);
    private final Pose gardenSideDoAllPath3Segment2Heading = poseFactory.of(47.1798, 128.0933, 90);
    private final Pose gardenSideDoAllPath4 = poseFactory.of(57.8337, 119.1618, 270);
    private final Pose gardenSideDoAllPath4Segment1Heading = poseFactory.of(57.8337, 119.1618, 90);
    private final Pose gardenSideDoAllPath4Segment2Start = poseFactory.of(57.8337, 119.1618, 90);
    private final Pose gardenSideDoAllPath4Segment2End = poseFactory.of(57.8337, 119.1618, 270);
    private final Pose gardenSideDoAllPath5 = poseFactory.of(13.2202, 47.5798, 180);
    private final Pose gardenSideDoAllPath5Control1 = poseFactory.of(21.6494, 103.609, 0);
    private final Pose gardenSideDoAllPath5Control2 = poseFactory.of(30.9303, 50.0876, 0);
    private final Pose gardenSideDoAllPath6 = poseFactory.of(58.1101, 9.5202, 90);
    private final Pose gardenSideDoAllPath7 = poseFactory.of(11.2112, 93.927, 90);
    private final Pose gardenSideDoAllPath7Control1 = poseFactory.of(29.4966, 28.4831, 0);

    public Path gardenSideDoAll() {
        return path(line(gardenSideDoAllStart, gardenSideDoAllPath0).linear(gardenSideDoAllStart, gardenSideDoAllPath0), curve(gardenSideDoAllPath0, gardenSideDoAllPath1Control1, gardenSideDoAllPath1Control2, gardenSideDoAllPath1).heading(Interpolator.piecewise().until(0.5, Interpolator.linear(gardenSideDoAllPath1Segment1Start, gardenSideDoAllPath1Segment1End)).until(1, Interpolator.constant(gardenSideDoAllPath1Segment2Heading))), curve(gardenSideDoAllPath1, gardenSideDoAllPath2Control1, gardenSideDoAllPath2).linear(gardenSideDoAllPath1, gardenSideDoAllPath2), line(gardenSideDoAllPath2, gardenSideDoAllPath3).heading(Interpolator.piecewise().until(0.25, Interpolator.linear(gardenSideDoAllPath3Segment1Start, gardenSideDoAllPath3Segment1End)).until(1, Interpolator.constant(gardenSideDoAllPath3Segment2Heading))), line(gardenSideDoAllPath3, gardenSideDoAllPath4).heading(Interpolator.piecewise().until(0.75, Interpolator.constant(gardenSideDoAllPath4Segment1Heading)).until(1, Interpolator.linear(gardenSideDoAllPath4Segment2Start, gardenSideDoAllPath4Segment2End))), curve(gardenSideDoAllPath4, gardenSideDoAllPath5Control1, gardenSideDoAllPath5Control2, gardenSideDoAllPath5).linear(gardenSideDoAllPath4, gardenSideDoAllPath5), line(gardenSideDoAllPath5, gardenSideDoAllPath6).linear(gardenSideDoAllPath5, gardenSideDoAllPath6), curve(gardenSideDoAllPath6, gardenSideDoAllPath7Control1, gardenSideDoAllPath7).linear(gardenSideDoAllPath6, gardenSideDoAllPath7));
    }

    /// ParkSide Auto Method
    private final Pose parkSideStart = poseFactory.of(61, 132, 90);
    private final Pose parkSideWaitStart = poseFactory.of(61, 132.1, 270);
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
        /// --- To add an auto: ---
        /// 1. Add name and description to enum
        /// 2. Paste poses and paths (all uniquely named and GROUPED into ONE path)
        /// 3. Add a line here. Template: starts.add(newStart);paths.add(newPath);
        ArrayList<Pose> starts = new ArrayList<>();
        ArrayList<Path> paths = new ArrayList<>();
        starts.add(gardenSideStart);paths.add(gardenSideAuto());
        starts.add(parkSideStart);paths.add(parkSideAuto());
        starts.add(gardenSideDoAllStart);paths.add(gardenSideDoAll());
        starts.add(poseFactory.of(0,0,0));paths.add(path(line(poseFactory.of(0,0,0), poseFactory.of(0,10,0)).linear(poseFactory.of(0,0,0),poseFactory.of(0,10,0))));
        for (int i = 0; i < Autos.values().length; i++) {
            Autos.values()[i].setStart(starts.get(i));
            Autos.values()[i].setPath(paths.get(i));
        }


    }

    @Override
    public void init_loop() {

        // User input to choose auto
        switch (configState) {
            case SelectAuto:
                // if 'A' is pressed, they have selected the auto, nothing else is required here.
                if (gamepad1.aWasPressed()) {
                    chosenAuto = Autos.values()[cursorPos];
                    configState = ConfigState.ReadyToRun;
                }
                // 0 position at top, going down
                // if 'UP' is pressed, -1, if 'DOWN' is pressed, +1
                if (gamepad1.dpadUpWasPressed()) {
                    cursorPos--;
                }
                if (gamepad1.dpadDownWasPressed()) {
                    cursorPos++;
                }
                // clamp the cursor between 0 and the index of the last auto. (aka length-1, bc it starts at 0)
                cursorPos = Math.min(Math.max(0, cursorPos), Autos.values().length - 1);

                telemetry.addLine("Select an auto: (DPAD to move, A to select)");
                for (int i = 0; i < Autos.values().length; i++) {
                    telemetry.addData(((i == cursorPos)? "> " : "") + Autos.values()[i], Autos.values()[i].desc);
                }
                break;
            case ReadyToRun:
                // We have selected an auto, so here we just display it, then wait for start.
                telemetry.addData("Running Auto", chosenAuto);
                telemetry.update();
                break;
        }
    }

    @Override
    public void start() {

        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(chosenAuto.getStart());

        schedule(follow(follower, chosenAuto.getPath()));
    }

    @Override
    public void loop() {

        follower.update();
        Scheduler.execute();
        telemetry.addData("Time elapsed:", getRuntime());
        telemetry.update();
    }

}
