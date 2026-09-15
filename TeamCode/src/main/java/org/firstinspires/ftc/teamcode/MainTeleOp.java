package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Prism.GoBildaPrismDriver;
import org.firstinspires.ftc.teamcode.Prism.PrismAnimations;

import java.util.ArrayList;
import java.util.List;

@TeleOp(group="Linear Opmode")
public class MainTeleOp extends LinearOpMode {

  private final ElapsedTime runtime = new ElapsedTime();

  @Override
  public void runOpMode() {

    /*=======================================INITIALIZATION=======================================*/

    // --- Variables ---

    // Positions
    Pose2d beginPose = new Pose2d(0,0,0);
    PoseVelocity2d movement = null;

    // Driving variables
    double driveVar;
    double strafe;
    double turn;

    // Temporary variables


    // --- Motors / Servos / Sensors ---

    // Drive
    MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

    // Camera
    Limelight3A camq = hardwareMap.get(Limelight3A.class, "limelight");
    camq.pipelineSwitch(0);
    camq.start();


    // --- Other Objects ---

    // Gamepads
    gamepad1.setLedColor(255.0,0.0,0.0, Gamepad.LED_DURATION_CONTINUOUS);
    gamepad2.setLedColor(0.0,0.0,255.0, Gamepad.LED_DURATION_CONTINUOUS);



    // Dashboard
    FtcDashboard dash = FtcDashboard.getInstance();

    // Roadrunner Actions list queue
    List<Action> runningActions = new ArrayList<>();

    // Prism Led
    GoBildaPrismDriver prism = hardwareMap.get(GoBildaPrismDriver.class, "prism");
    prism.enableDefaultBootArtboard(false);
    final PrismAnimations.Solid OFF = new PrismAnimations.Solid();
    OFF.setPrimaryColor(0, 0, 0);
    OFF.setBrightness(0);
    final PrismAnimations.Solid ON = new PrismAnimations.Solid();
    ON.setPrimaryColor(100,255,100);
    ON.setBrightness(100);
    prism.clearAllAnimations();
    prism.insertAndUpdateAnimation(GoBildaPrismDriver.LayerHeight.LAYER_0, OFF);


    telemetry.update();




    /*=======================================WAIT FOR START=======================================*/
    waitForStart();

    runtime.reset();


    /*===================================WHILE OPMODE IS RUNNING==================================*/
    while (opModeIsActive()) {

      // --- INPUT ---
  /* Input is where we bulk read all of our sensors and gamepads
   * DO NOT put any hardware writes or calculations here, for my sanity, and the robot's cycle time
   */
      TelemetryPacket packet = new TelemetryPacket();
      driveVar = gamepad1.left_stick_x;
      strafe = gamepad1.left_stick_y;
      turn = -gamepad1.right_stick_x;

      boolean gp1_atRest = gamepad1.atRest();
      boolean gp2_atRest = gamepad2.atRest();


      // --- CALCULATIONS ---
    /* Calculations is where we do all the math and logic.
     * ONLY USE VARIABLES AND LOGIC HERE
     */

      double heading = drive.localizer.getPose().heading.toDouble();
      double newDriveVar = strafe * Math.cos(heading) - driveVar * Math.sin(heading);
      double newStrafe = strafe * Math.sin(heading) + driveVar * Math.cos(heading);

      movement = new PoseVelocity2d(
              new Vector2d(
                      newStrafe,
                      newDriveVar
              ),
              turn
      );

      // --- OUTPUT ---
      /* Output is where we set all the motor powers and write to telemetry
     * DO NOT get any input here, only use the variables from calculations.
     */

      drive.setDrivePowers(movement);
      drive.updatePoseEstimate();

      // Debug stuff (please ignore)
      /*if ((DriveConstants.p != shooter.getPID().p) || (DriveConstants.i != shooter.getPID().i) || (DriveConstants.d != shooter.getPID().d)) {
        shooter.resetPID(DriveConstants.p,DriveConstants.i,DriveConstants.d);
      }*/


      // update running actions
      List<Action> newActions = new ArrayList<>();
      for (Action action : runningActions) {
        action.preview(packet.fieldOverlay());
        if (action.run(packet)) {
          newActions.add(action);
        }
      }
      runningActions = newActions;

      dash.sendTelemetryPacket(packet);
    }

    drive.localizer.update();


    telemetry.update();


  }

}
