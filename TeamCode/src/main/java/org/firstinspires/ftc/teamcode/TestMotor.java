package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TestMotor extends LinearOpMode {

    @Override
    public void runOpMode() {

        DcMotor motor = hardwareMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        telemetry.addLine("Ready (click START)");
        telemetry.update();

        waitForStart();

        telemetry.update();


        double speed = 0.0;
        int stepSize = 10;
        while (opModeIsActive()) {
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
            motor.setPower(speed);
            telemetry.addData("Motor Speed", speed);
            telemetry.addData("Step Size", stepSize / 100.0);
            telemetry.update();
        }
    }
}
