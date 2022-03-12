package org.firstinspires.ftc.teamcode.autos;

import static org.firstinspires.ftc.teamcode.hardware.ConstantVariables.SERVO_INTAKE_POSITION;
import static org.firstinspires.ftc.teamcode.hardware.ConstantVariables.SERVO_OUTTAKE_POSITION;
import static org.firstinspires.ftc.teamcode.hardware.ConstantVariables.SLIDE_POS_ONE;
import static org.firstinspires.ftc.teamcode.hardware.ConstantVariables.SLIDE_POS_THREE;
import static org.firstinspires.ftc.teamcode.hardware.ConstantVariables.SLIDE_POS_TWO;
import static org.firstinspires.ftc.teamcode.hardware.Control.motor.extendArm;
import static org.firstinspires.ftc.teamcode.hardware.Control.servo.setServoPosition;
import static org.firstinspires.ftc.teamcode.hardware.Devices.armOuttakeServo;
import static org.firstinspires.ftc.teamcode.hardware.Devices.linearSlideMotor;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.ConstantVariables;
import org.firstinspires.ftc.teamcode.hardware.Control;
import org.firstinspires.ftc.teamcode.hardware.Devices;
import org.firstinspires.ftc.teamcode.hardware.Encoders;

@Autonomous
public class blueWarehouseAuto extends OpMode {
    SampleMecanumDrive drive;
    Trajectory trajStart, trajWarehouseStart, trajAlliShip, trajPark, trajWarehouse;
    ElapsedTime runtime;
    double timeMarker;
    boolean armExtension, intake;
    int slidePosition;

    public void init(){
        Devices.initDevices(hardwareMap);
        drive = new SampleMecanumDrive(hardwareMap);
        runtime = new ElapsedTime();
        slidePosition = 3;
        armExtension = false;
        intake = false;

        Pose2d startPos = new Pose2d(10, -61, Math.toRadians(-90));
        drive.setPoseEstimate(startPos);

        //to alliance shipping hub from starting point
        trajStart = drive.trajectoryBuilder(startPos)
                .splineTo(new Vector2d(-11, 50), Math.toRadians(-90)) //TODO: tune positions
                .addDisplacementMarker(10, () -> { //displacement in inches
                    armExtension = true;
                })
                .build();
        //to warehouse from post preloaded starting point
        trajWarehouseStart = drive.trajectoryBuilder(trajStart.end())
                .splineTo(new Vector2d(50, 50), Math.toRadians(0))
                .addDisplacementMarker(20, () -> {
                    armExtension = false;
                })
                .build();
        //to alliance shipping hub
        trajAlliShip = drive.trajectoryBuilder(trajWarehouseStart.end())
                .splineTo(new Vector2d(40, 16), Math.toRadians(-90))
                .addDisplacementMarker(20, () -> {
                    armExtension = true;
                })
                .build();
        //park at warehouse
        trajPark = drive.trajectoryBuilder(trajAlliShip.end())
                .splineTo(new Vector2d(55, 55), Math.toRadians(-90))
                .addDisplacementMarker(0, () -> {
                    intake = false;
                    armExtension = false;
                })
                .build();
        //to warehouse
        trajWarehouse = drive.trajectoryBuilder(trajAlliShip.end())
                .splineTo(new Vector2d(20, 20), Math.toRadians(-90))
                .addDisplacementMarker(20, () -> {
                    intake = true;
                })
                .build();

        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
    }

    public void start() {
        drive.followTrajectoryAsync(trajStart);
        armExtension = true;


        return;
    }
    public void loop(){
        /*
        Sequence:
        1. drop preloaded block at the alliance shipping hub
           - trajStart
           - extend arm
           - drop freight
        2. drive to the warehouse and intake freight
           - trajWarehouseStart
           - lower arm
           - intake
        3. drive to shared shipping hub
           - trajAlliShip
           - slidePosition = 1
           - raise arm
        4. drive to warehouse and intake freight
           - trajWarehouse
           - lower arm
           - intake
        5. repeat steps 3 & 4 until time is ending
        6. park in the warehouse
        */

        int sequence = 0;

        drive.update();
        if(!drive.isBusy()){
            if(sequence == 0){
                drive.followTrajectoryAsync(trajWarehouseStart);
                armExtension = false;
                sequence++;
            }
            else if(sequence == 1){
                drive.followTrajectoryAsync(trajAlliShip);
                armExtension = true;
                sequence++;
            }
            else if(sequence == 2){
                drive.followTrajectoryAsync(trajWarehouse);
                armExtension = false;
                sequence++;
                if(runtime.seconds() > 25){ //TODO: find the time it takes to park
                    sequence++;
                }
                else sequence--;
            }
            else if(sequence == 3){
                drive.followTrajectoryAsync(trajPark);
                armExtension = false;
                sequence++;
            }

        }

        //extend arm & drop freight
        if(armExtension){
            switch(slidePosition){
                case 1:
                    extendArm(SLIDE_POS_ONE);
                    setServoPosition(armOuttakeServo, SERVO_OUTTAKE_POSITION);
                    break;
                case 2:
                    extendArm(SLIDE_POS_TWO);
                    setServoPosition(armOuttakeServo, SERVO_OUTTAKE_POSITION);
                    break;
                case 3:
                    extendArm(SLIDE_POS_THREE);
                    setServoPosition(armOuttakeServo, SERVO_OUTTAKE_POSITION);
                    break;
            }
        }
        else{
            //retract arm
            if(Encoders.getMotorEnc(linearSlideMotor) > 0 ) linearSlideMotor.setPower(-1.0);
            setServoPosition(armOuttakeServo, SERVO_INTAKE_POSITION); //reset servo position

        }

        //intake
        if (intake){
            Control.motor.intake(1.0);
        } else {
            Control.motor.intake(0.0);
        }

    }
}
