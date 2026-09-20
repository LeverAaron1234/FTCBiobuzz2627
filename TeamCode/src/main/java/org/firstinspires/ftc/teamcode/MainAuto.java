package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.appendages.Angle;
import org.firstinspires.ftc.teamcode.appendages.Intake;
import org.firstinspires.ftc.teamcode.appendages.Pew;
import org.firstinspires.ftc.teamcode.appendages.Shooter;
import org.firstinspires.ftc.teamcode.appendages.Spin;
import org.firstinspires.ftc.teamcode.appendages.Stopper;



@Autonomous(preselectTeleOp = "FCRR")
public final class MainAuto extends LinearOpMode {

    /// --- Setup for a config loop ---
    /// This is an enumeration, which basically means that the name IS the data.
    /// Think of it like a python dictionary, where you store the name (the key)
    /// and then the arguments (the value(s))
    public enum Autos {
        // Defining the actual enumeration values
        GardenSide ("Right side, near the garden"),
        ParkSide ("Left side, near the parking zone");

        // This is how we store the arguments
        // desc stores the description of the autos
        private final String desc;

        // Here is where we actually set the above variables
        // This runs once for every enumeration value
        private Autos(String description) {
            this.desc = description;
        }

    }

    Autos chosenAuto = null;

    public enum ConfigState {
        SelectAuto,
        ReadyToRun
    }

    ConfigState configState = ConfigState.SelectAuto;

    /// Prints values to telemetry, because I wanted to make this more confusing
    public void printToTelemetry(Telemetry telemetry, int cursorPos) {
        telemetry.addLine("Select an auto: (DPAD to move, A to select)");
        for (int i = 0; i < Autos.values().length; i++) {
            telemetry.addData((i == cursorPos)? "> " : "" + Autos.values()[i], Autos.values()[i].desc);
        }
        telemetry.update();
    }


    @Override
    public void runOpMode() throws InterruptedException {

        /// This is the auto selector that should work without missing button presses... maybe...
        // TODO: TEST THOROUGHLY
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


        // The starting position for the robot
        Pose2d beginPose = new Pose2d(0.0,0.0,0.0);
        Pose2d firingPose = new Pose2d(0,0,Math.toRadians(0));

        // Instantiating the classes from the appendages folder
        Shooter shooter = new Shooter(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        Pew pew = new Pew(hardwareMap);
        Angle angle = new Angle(hardwareMap);
        Stopper stopper = new Stopper(hardwareMap);

        // More instantiating, this time, the camera and the turret
        //Limelight3A camq = hardwareMap.get(LimelightCam.class, "limelight");
        Spin spin = new Spin(hardwareMap);
        spin.resetTimer();



        // The limit switches on the turret
        TouchSensor leftLimit = hardwareMap.get(TouchSensor.class, "leftLimit");
        TouchSensor rightLimit = hardwareMap.get(TouchSensor.class, "rightLimit");

        // Quick making sure the robot isn't moving
        Actions.runBlocking(pew.set());

        // Make the camera work correctly, by putting it on the correct pipeline.
        // The pseudocode dictionary shows what numbers correspond to the different targets
        //camq.pipelineSwitch(3);// {0: "goal", 1: "obelisk", 2: "RedGoal", 3: "BlueGoal"}
        //camq.start(); // start the camera

        // Instantiating the chassis and its motors
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

        // The Roadrunner Dashboard
        FtcDashboard dash = FtcDashboard.getInstance();


        // Make really sure that nothing moves
        Actions.runBlocking(new ParallelAction(
                shooter.stop(),
                pew.set(),
                intake.off(),
                angle.close(),
                stopper.Out()
        ));



        /*=======================================WAIT FOR START=======================================*/

        waitForStart();



        telemetry.update();


/*        Actions.runBlocking(camq.update())

        ;
        telemetry.addData("Tag Area", camq.getTagArea());
        telemetry.addData("Tagx", camq.getTagx());
        telemetry.addData("Tagy", camq.getTagy());
        telemetry.addData("TagID", camq.getTagid());
        telemetry.update();
*/

        switch (chosenAuto) {
            case ParkSide:
                telemetry.addLine("Running ParkSide Auto");
                telemetry.update();
                // auto code goes here
                break;
            case GardenSide:
                telemetry.addLine("Running GardenSide Auto");
                telemetry.update();
                // auto code goes here
                break;
            default:
                telemetry.addLine(":( :( :( :( :( :( :(");
                telemetry.addLine("ERROR - Something broke... please tell Levi.");
                telemetry.addLine("Error: chosenAuto is "+((chosenAuto == null)? null : chosenAuto.name()));
                telemetry.addLine(":( :( :( :( :( :( :(");
                telemetry.update();
        }




        RobotPose.lastRobotPose = drive.localizer.getPose(); // update the robot pose
        RobotPose.redTeam = false;
        RobotPose.updated = true; // tell the updated pose that it was changed, because yes.
    }
}
