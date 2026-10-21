package org.firstinspires.ftc.teamcode;

import static com.pedropathing.ivy.groups.Groups.*;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.ivy.commands.Commands;
import com.pedropathing.math.Pose;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Prism.GoBildaPrismDriver;
import org.firstinspires.ftc.teamcode.Prism.PrismAnimations;
import org.firstinspires.ftc.teamcode.pedro.Constants;

import java.util.ArrayList;
import java.util.List;

@TeleOp(group="Opmode")
public class MainTeleOp extends OpMode {

  private final ElapsedTime runtime = new ElapsedTime();

  private List<LynxModule> allHubs;

  private Follower follower;


  // Driving variables
  double driveVar;
  double strafe;
  double turn;

  // Temporary variables


  // --- Motors / Servos / Sensors ---

  DcMotorEx intake;
  DcMotorEx pollenShooter;
  DcMotorEx NectarShooter;


  // Dashboard
  FtcDashboard dash = FtcDashboard.getInstance();
  TelemetryPacket packet = new TelemetryPacket();

  // Roadrunner Actions list queue
  List<Action> runningActions = new ArrayList<>();

  @Override
  public void init() {
    /*=======================================INITIALIZATION=======================================*/

    // Drive
    //MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

    // Camera
    Limelight3A camq = hardwareMap.get(Limelight3A.class, "limelight");

    // --- Variables ---

    allHubs = hardwareMap.getAll(LynxModule.class);

    for (LynxModule module : allHubs) {
      module.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
    }

    follower = Constants.create(hardwareMap);

    camq.pipelineSwitch(0);
    camq.start();


    // --- Other Objects ---

    // Gamepads
    gamepad1.setLedColor(255.0, 0.0, 0.0, Gamepad.LED_DURATION_CONTINUOUS);
    gamepad2.setLedColor(0.0, 0.0, 255.0, Gamepad.LED_DURATION_CONTINUOUS);


    // Prism Led
    GoBildaPrismDriver prism = hardwareMap.get(GoBildaPrismDriver.class, "prism");
    prism.enableDefaultBootArtboard(false);
    final PrismAnimations.Solid OFF = new PrismAnimations.Solid();
    OFF.setPrimaryColor(0, 0, 0);
    OFF.setBrightness(0);
    final PrismAnimations.Solid ON = new PrismAnimations.Solid();
    ON.setPrimaryColor(100, 255, 100);
    ON.setBrightness(100);
    prism.clearAllAnimations();
    prism.insertAndUpdateAnimation(GoBildaPrismDriver.LayerHeight.LAYER_0, OFF);


    // Command building
    Command test = Command.build()
            .setExecute(() -> telemetry.addLine("Executing..."))
            .setDone(() -> gamepad1.aWasPressed())
            .setEnd(endCondition -> telemetry.addLine("Done!"));

    // Commands class (specifically instant())
    Command infinite = Commands.infinite(() -> telemetry.addLine("Wheeeeeeeeeeeeeeeeeeeee..."));
    Command instantTest = Commands.instant(() -> telemetry.addLine("Instant!"));


    Scheduler.schedule(sequential(
        test,
        instantTest
    ));


    telemetry.update();
  }


  /*=======================================WAIT FOR START=======================================*/


  @Override
  public void start() {
    if (RobotPose.lastRobotPose == null) {
      follower.setPose(new Pose(0,0));
    } else {
      follower.setPose(RobotPose.lastRobotPose);
    }
    follower.update();
    runtime.reset();
  }


  /*===================================WHILE OPMODE IS RUNNING==================================*/
  @Override
  public void loop() {

    // --- INPUT ---
/* Input is where we bulk read all of our sensors and gamepads
 * DO NOT put any hardware writes or calculations here, for my sanity, and the robot's cycle time
 */
    for (LynxModule module : allHubs) {
      module.clearBulkCache();
    }


    TelemetryPacket packet = new TelemetryPacket();
    driveVar = -gamepad1.left_stick_y;
    strafe = -gamepad1.left_stick_x;
    turn = -gamepad1.right_stick_x;

    follower.update();
    Scheduler.execute();


    // --- CALCULATIONS ---
  /* Calculations is where we do all the math and logic.
   * ONLY USE VARIABLES AND LOGIC HERE
   */


    DrivePowers powers = ManualDrive.fieldCentric(
        driveVar,
        strafe,
        turn,
        follower.pose().heading()
    );

    // --- OUTPUT ---
    /* Output is where we set all the motor powers and write to telemetry
   * DO NOT get any input here, only use the variables from calculations.
   */

    ManualDrive.driveOrHold(follower, powers);


    // Debug stuff (please ignore)
    /*if ((DriveConstants.p != shooter.getPID().p) || (DriveConstants.i != shooter.getPID().i) || (DriveConstants.d != shooter.getPID().d)) {
      shooter.resetPID(DriveConstants.p,DriveConstants.i,DriveConstants.d);
    }*/

    telemetry.update();
  }






}
