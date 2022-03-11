package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.teamcode.BaseRobot;
import org.firstinspires.ftc.teamcode.hardware.Control;
import org.firstinspires.ftc.teamcode.hardware.Devices;

import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;

@TeleOp

public class SampleTeleOp extends BaseRobot {

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void start() { super.start(); }

    @Override
    public void loop() {
        super.loop();

        // drive using tankanum
        Control.drive.tankanumDrive(gamepad1.right_stick_y, gamepad1.left_stick_y, gamepad1.right_stick_x);

        // control armLiftMotor
//        if (gamepad1.dpad_down || gamepad1.a) {
//            Control.moveMotor(Devices.armLiftMotor, 1.0);
//        } else if (gamepad1.dpad_up || gamepad1.b) {
//            Control.moveMotor(Devices.armLiftMotor, -1.0);
//        } else {
//            Control.moveMotor(Devices.armLiftMotor, 0.0);
//        }
    }
}