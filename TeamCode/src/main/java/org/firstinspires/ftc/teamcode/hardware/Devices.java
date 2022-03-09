package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class Devices {
    // to add a hardware device, initialize the device here and map them in BaseBot
    public static DcMotor leftFrontDriveMotor, rightFrontDriveMotor, leftBackDriveMotor, rightBackDriveMotor, armLiftMotor;
    public static Servo armAdjustServo;
    public static RevBlinkinLedDriver lightStrip;
    public static DistanceSensor distanceSensor;
    public static BNO055IMU imu;
    public static WebcamName webcam;

    // NOTE: deviceName should be the same as the name specified on the configuration
    public static void initDevices(HardwareMap hardwareMap) {
        // comment out the drive and imu initialization if you plan on using roadrunner
        Devices.leftBackDriveMotor = hardwareMap.get(DcMotor.class, "leftBackDriveMotor");
        Devices.rightBackDriveMotor = hardwareMap.get(DcMotor.class, "rightBackDriveMotor");
        Devices.leftFrontDriveMotor = hardwareMap.get(DcMotor.class, "leftFrontDriveMotor");
        Devices.rightFrontDriveMotor = hardwareMap.get(DcMotor.class, "rightFrontDriveMotor");
        Control.drive.configureDriveMotors();

        Devices.imu = hardwareMap.get(BNO055IMU.class, "imu");
        Devices.webcam = hardwareMap.get(WebcamName.class, "Webcam 1");

//        Devices.armLiftMotor = hardwareMap.get(DcMotor.class, "armLiftMotor");
//        Devices.armLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        Devices.armAdjustServo = hardwareMap.get(Servo.class,"armAdjustServo");

//        Devices.lightStrip = hardwareMap.get(RevBlinkinLedDriver.class, "lightStrip");
//        Devices.distanceSensor = hardwareMap.get(DistanceSensor.class, "distanceSensor");
    }
}
