package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.levisToolbox.TelemetrySelector;

import java.util.List;
import java.util.Locale;

@TeleOp
public class TestMotor extends LinearOpMode {

    private List<LynxModule> allHubs;

    @Override
    public void runOpMode() {

        allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule module : allHubs) {
            module.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }
        //try {
        //    TelemetrySelector t = new TelemetrySelector(telemetry);
        //} catch (Exception e) {}
        DcMotorEx motor = hardwareMap.get(DcMotorEx.class, "leftFly");
        //DcMotorEx motor2 = hardwareMap.get(DcMotorEx.class, "rightFly");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        telemetry.addLine("Ready (click START)");
        telemetry.update();

        FtcDashboard dashboard = FtcDashboard.getInstance();
        TelemetryPacket packet = new TelemetryPacket();



        waitForStart();

        telemetry.update();



        double speed = 0.0;
        int stepSize = 10;
        int cursorPose = 0;
        double kP = 0;
        double kI = 0;
        double kD = 0;
        double kFf = 0;

        double lastKp = 0;
        double lastKi = 0;
        double lastKd = 0;
        double lastKff = 0;

        while (opModeIsActive()) {
            for (LynxModule module : allHubs) {
                module.clearBulkCache();
            }

            if (gamepad1.aWasPressed()) {
                stepSize--;
            }
            if (gamepad1.yWasPressed()) {
                stepSize++;
            }
            if (gamepad1.dpadDownWasPressed()) {
                speed-=0.01*stepSize;
            }
            if (gamepad1.dpadUpWasPressed()) {
                speed+=0.01*stepSize;
            }

            /*
            if (gamepad1.dpadLeftWasPressed()) {cursorPose -= 1;}
            if (gamepad1.dpadRightWasPressed()) {cursorPose += 1;}
            cursorPose = (cursorPose >= 0)? cursorPose % 4 : 4-(Math.abs(cursorPose) % 4);

            if (gamepad1.leftBumperWasPressed()) {
                switch (cursorPose) {
                    case 0:
                        kP = Math.max(kP - (0.01*stepSize), 0);
                        break;
                    case 1:
                        kI = Math.max(kI - (0.01*stepSize), 0);
                        break;
                    case 2:
                        kD = Math.max(kD - (0.01*stepSize), 0);
                        break;
                    case 3:
                        kFf = Math.max(kFf - (0.01*stepSize), 0);
                        break;
                }
            }

            if (gamepad1.rightBumperWasPressed()) {
                switch (cursorPose) {
                    case 0:
                        kP += (0.01*stepSize);
                        break;
                    case 1:
                        kI += (0.01*stepSize);
                        break;
                    case 2:
                        kD += (0.01*stepSize);
                        break;
                    case 3:
                        kFf += (0.01*stepSize);
                        break;
                }
            }

            if (kP != lastKp || kI != lastKi || kD != lastKd || kFf != lastKff) {
                motor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(kP, kI, kD, kFf));
                lastKp = kP;
                lastKi = kI;
                lastKd = kD;
                lastKff = kFf;
            }*/

            motor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, DriveConstants.getPIDF());

            speed = Math.min(Math.max(speed,-1.0),1.0);
            stepSize = (int) Math.min(Math.max(stepSize,1.0),10.0);
            motor.setVelocity(speed * 2800);
            telemetry.addData("Motor Speed in %", speed);
            telemetry.addData("Motor Speed in RPM", (Math.abs(speed) * 2800) * 60/28.0);
            telemetry.addData("Motor Power", motor.getPower());
            telemetry.addData("Motor Velocity", motor.getVelocity());
            telemetry.addData("Tick count", motor.getCurrentPosition());
            telemetry.addData("Step Size", stepSize / 100.0);
            /*
            telemetry.addLine("Cursor Pose "+cursorPose);
            telemetry.addLine(((cursorPose%4 == 0)? ">" :"")+"kP: "+kP);
            telemetry.addLine(((cursorPose%4 == 1)? ">" :"")+"kI: "+kI);
            telemetry.addLine(((cursorPose%4 == 2)? ">" :"")+"kD: "+kD);
            telemetry.addLine(((cursorPose%4 == 3)? ">" :"")+"kFf: "+kFf);
             */
            telemetry.update();

            /*
            packet.put("kP", String.format(Locale.ENGLISH,".%2f",kP));
            packet.put("kI", String.format(Locale.ENGLISH,".%2f",kI));
            packet.put("kD", String.format(Locale.ENGLISH,".%2f",kD));
            packet.put("kFf", String.format(Locale.ENGLISH,".%2f",kFf));
             */
            packet.put("Motor target RPM", String.format(Locale.ENGLISH,"%2f",(speed * 2800) * 60/28.0));
            packet.put("Motor Velocity in RPM", String.format(Locale.ENGLISH,"%2f",motor.getVelocity() * 60/28.0));
            dashboard.sendTelemetryPacket(packet);


        }
    }
}
