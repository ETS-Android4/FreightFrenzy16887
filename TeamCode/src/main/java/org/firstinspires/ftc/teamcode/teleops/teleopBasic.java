package org.firstinspires.ftc.teamcode.teleops;

import static org.firstinspires.ftc.teamcode.hardware.Encoders.driveResetEncs;
import static org.firstinspires.ftc.teamcode.hardware.Encoders.resetMotorEnc;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.BaseRobot;
import org.firstinspires.ftc.teamcode.hardware.Control;
import org.firstinspires.ftc.teamcode.hardware.Devices;

@TeleOp
public class teleopBasic extends BaseRobot {

    int maxSlideValue = 1125;
    int minSlideValue = 0;

    public void init(){
        super.init();
        resetMotorEnc(Devices.linearSlideMotor);
        driveResetEncs();
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {
        super.loop();

        Control.drive.tankanumDrive(gamepad1.right_stick_y, gamepad1.left_stick_y, gamepad1.left_stick_x); // how to move

        //intake
        if(gamepad1.left_bumper){
            Control.motor.intake(-1.0);
        }
        else if(gamepad1.right_bumper){
            Control.motor.intake(1.0); //reverse intake
        }
        else{
            Control.motor.intake(0);
        }

        //outtake
        if (gamepad1.y && Devices.linearSlideMotor.getCurrentPosition()<maxSlideValue){
            Control.motor.moveMotor(Devices.linearSlideMotor, 0.5);
        }
        else if(gamepad1.x && Devices.linearSlideMotor.getCurrentPosition()>minSlideValue){
            Control.motor.moveMotor(Devices.linearSlideMotor, -0.5);
        }
        else{ Devices.linearSlideMotor.setPower(0);}
        telemetry.addData("slide position: ", Devices.linearSlideMotor.getCurrentPosition());

        //dump freight
        if(gamepad1.a){
            Devices.armOuttakeServo.setPosition(0.5);
        } else {
            Devices.armOuttakeServo.setPosition(1);//tune positions
        }
        telemetry.addData("outtake servo pose: ", Devices.armOuttakeServo.getPosition());

        //ducks
        if(gamepad1.right_bumper){
            Devices.duckServo.setPower(1);
        }
        else {
            Devices.duckServo.setPower(0);
        }

    }
}
