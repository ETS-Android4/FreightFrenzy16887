package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.hardware.*;

/**
 * Template created by Ayaan Govil on 8/21/2021. Last updated on 10/7/21.
 *
 * FTC Java Documentation: http://ftctechnh.github.io/ftc_app/doc/javadoc/index.html
 */

// basebot serves as the first execution point before flowing into the teleop

public class BaseRobot extends OpMode {
    // this function runs when you hit the init button on the app
    @Override
    public void init() {
        // map and initialize devices
        Devices.initDevices(hardwareMap);
    }

    // this function runs when you hit the start button after the init button
    @Override
    public void start() {

    }

    // this function runs when you hit the stop button
    @Override
    public void stop() {

    }

    // this function loops while the bot is running
    @Override
    public void loop() {
        // telemetry (inherited from OpMode class) serves as logging on the phone - we're constantly tracking the drive motor encoders by doing this
        telemetry.addData("D00 Left Front Drive Motor Enc: ", Encoders.getMotorEnc(Devices.leftFrontDriveMotor));
        telemetry.addData("D01 Right Front Drive Motor Enc: ", Encoders.getMotorEnc(Devices.rightFrontDriveMotor));
        telemetry.addData("D02 Left Back Drive Motor Enc: ", Encoders.getMotorEnc(Devices.leftBackDriveMotor));
        telemetry.addData("D03 Right Back Drive Motor Enc: ", Encoders.getMotorEnc(Devices.rightBackDriveMotor));
    }
}
