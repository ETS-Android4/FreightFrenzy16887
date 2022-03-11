package org.firstinspires.ftc.teamcode.teleops;

import static org.firstinspires.ftc.teamcode.hardware.Devices.armOuttakeServo;
import static org.firstinspires.ftc.teamcode.hardware.Devices.duckServo;
import static org.firstinspires.ftc.teamcode.hardware.Devices.linearSlideMotor;
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
        resetMotorEnc(linearSlideMotor);
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
        if (gamepad1.dpad_up && linearSlideMotor.getCurrentPosition()<maxSlideValue){ //TODO: Tilt box as slide goes up to keep freight in
            Control.motor.moveMotor(linearSlideMotor, 0.5);
        }
        else if(gamepad1.dpad_down && linearSlideMotor.getCurrentPosition()>minSlideValue){
            Control.motor.moveMotor(linearSlideMotor, -0.5);
        }
        else{ linearSlideMotor.setPower(0);}
        telemetry.addData("slide position: ", linearSlideMotor.getCurrentPosition());



        //dump freight
        if(gamepad1.dpad_left){
            armOuttakeServo.setPosition(0.5);
        }
        else if(linearSlideMotor.getCurrentPosition()>300){
            armOuttakeServo.setPosition(0.75);
        }
        else {
            armOuttakeServo.setPosition(1);//tune positions
        }
        telemetry.addData("outtake servo pose: ", armOuttakeServo.getPosition());

        //ducks
        if(gamepad1.a){//TODO: change button
            duckServo.setPower(1);
        }
        else if(gamepad1.b){
            duckServo.setPower(-1);
        }
        else {
            duckServo.setPower(0);
        }

    }
}
