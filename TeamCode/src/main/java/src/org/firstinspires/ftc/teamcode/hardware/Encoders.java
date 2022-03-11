package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.teamcode.hardware.Devices;

public class Encoders extends Devices {

    // gets current motor encoder value
    public static int getMotorEnc(DcMotor motor) {
        if (motor.getMode() != DcMotor.RunMode.RUN_USING_ENCODER) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        return motor.getCurrentPosition();
    }

    // reset motor encoder value
    public static void resetMotorEnc(DcMotor motor) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    // reset all drive motor encoders
    public static void driveResetEncs() {
        leftFrontDriveMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDriveMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackDriveMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDriveMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public static void driveRunUsingEncoder() {
        leftFrontDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public static void driveRunToPosition() {
        leftFrontDriveMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackDriveMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDriveMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackDriveMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
