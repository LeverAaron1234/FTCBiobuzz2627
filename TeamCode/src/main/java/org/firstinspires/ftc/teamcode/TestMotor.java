package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.levisToolbox.TelemetrySelector;

import java.util.List;

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

        waitForStart();

        telemetry.update();



        double speed = 0.0;
        int stepSize = 10;

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

            speed = Math.min(Math.max(speed,-1.0),1.0);
            stepSize = (int) Math.min(Math.max(stepSize,1.0),10.0);
            motor.setVelocity(speed * 2800);
            telemetry.addData("Motor Speed", speed);
            telemetry.addData("Motor Power", motor.getPower());
            telemetry.addData("Motor Velocity", motor.getVelocity());
            telemetry.addData("Tick count", motor.getCurrentPosition());
            telemetry.addData("Step Size", stepSize / 100.0);
            telemetry.update();
        }
    }
}
