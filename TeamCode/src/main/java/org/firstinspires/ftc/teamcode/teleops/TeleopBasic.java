package org.firstinspires.ftc.teamcode.teleops;

import static org.firstinspires.ftc.teamcode.hardware.Devices.armOuttakeServo;
import static org.firstinspires.ftc.teamcode.hardware.Devices.duckServo;
import static org.firstinspires.ftc.teamcode.hardware.Devices.leftBackDriveMotor;
import static org.firstinspires.ftc.teamcode.hardware.Devices.leftFrontDriveMotor;
import static org.firstinspires.ftc.teamcode.hardware.Devices.linearSlideMotor;
import static org.firstinspires.ftc.teamcode.hardware.Devices.rightBackDriveMotor;
import static org.firstinspires.ftc.teamcode.hardware.Devices.rightFrontDriveMotor;
import static org.firstinspires.ftc.teamcode.hardware.Devices.imu;
import static org.firstinspires.ftc.teamcode.hardware.Encoders.driveResetEncs;
import static org.firstinspires.ftc.teamcode.hardware.Encoders.resetMotorEnc;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.BaseRobot;
import org.firstinspires.ftc.teamcode.hardware.Control;
import org.firstinspires.ftc.teamcode.hardware.Devices;

@TeleOp
public class TeleopBasic extends BaseRobot {

    int maxSlideValue = 1000;
    int minSlideValue = 0;
    RevBlinkinLedDriver lights;
    int temp = 1;

    public void init() {
        super.init();
//        resetMotorEnc(linearSlideMotor);
        driveResetEncs();
        linearSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry.addData("left rear", leftBackDriveMotor.getDirection().toString());
        telemetry.addData("left front", leftFrontDriveMotor.getDirection().toString());
        telemetry.addData("right rear", rightBackDriveMotor.getDirection().toString());
        telemetry.addData("right front", rightFrontDriveMotor.getDirection().toString());
        telemetry.update();

        lights = hardwareMap.get(RevBlinkinLedDriver.class, "lights");
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.ORANGE);//TODO: Find better orange color
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {
        super.loop();

        /*if(temp == 1){
            resetStartTime();
            temp = 2;
        }
        if(time >= 90 && time < 115){
            lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.GOLD);
        }
        else{
            lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.ORANGE);
        }*/



        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double yaw = -angles.firstAngle;
        telemetry.addData("angle", yaw);

        Control.drive.tankanumDrive(gamepad1.right_stick_y, gamepad1.left_stick_y, gamepad1.right_stick_x); // how to move

        //intake
        Control.motor.intake(gamepad1.left_trigger - gamepad1.right_trigger);


        //outtake
//        if (gamepad1.dpad_up && linearSlideMotor.getCurrentPosition() < maxSlideValue) { //TODO: Tilt box as slide goes up to keep freight in
//            Control.motor.moveMotor(linearSlideMotor, 0.5);
//        } else if (gamepad1.dpad_down && linearSlideMotor.getCurrentPosition() > minSlideValue) {
//            Control.motor.moveMotor(linearSlideMotor, -0.5);
//        } else {
//            linearSlideMotor.setPower(0);
//        }

        if (gamepad1.dpad_up) {
            linearSlideSetPosition(maxSlideValue);
        } else if (gamepad1.dpad_down) {
            linearSlideSetPosition(minSlideValue);
        } else linearSlideMotor.setPower(0);



        telemetry.addData("slide position: ", linearSlideMotor.getCurrentPosition());


        //dump freight
        if (gamepad1.a) {
            armOuttakeServo.setPosition(0.5);
        } else {
            armOuttakeServo.setPosition(1);//tune positions
        }
        telemetry.addData("outtake servo pose: ", armOuttakeServo.getPosition());

        //ducks
        if (gamepad1.right_bumper) {
            duckServo.setPower(0.8);
        } else if (gamepad1.left_bumper) {
            duckServo.setPower(-0.8);
        } else {
            duckServo.setPower(0);
        }

    }

    // inefficient but quick way to do this
    public void linearSlideSetPosition(int targetPosition) {
        double power = 1.0;
        if (linearSlideMotor.getCurrentPosition() > targetPosition) power = -power;

        linearSlideMotor.setTargetPosition(targetPosition);
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearSlideMotor.setPower(power);
        while (linearSlideMotor.isBusy()) {
            Control.drive.tankanumDrive(gamepad1.right_stick_y, gamepad1.left_stick_y, gamepad1.right_stick_x); // how to move
        }
        linearSlideMotor.setPower(0);
    }
}
