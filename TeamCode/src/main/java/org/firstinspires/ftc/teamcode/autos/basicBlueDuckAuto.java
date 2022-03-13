package org.firstinspires.ftc.teamcode.autos;

import static org.firstinspires.ftc.teamcode.hardware.Control.auto.moveWithEncoder;
import static org.firstinspires.ftc.teamcode.hardware.Control.auto.strafeToPosition;
import static org.firstinspires.ftc.teamcode.hardware.Devices.duckServo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.Devices;

@Autonomous
public class basicBlueDuckAuto extends LinearOpMode {
    ElapsedTime timer;
    double timeMarker;
    public void runOpMode() {
        Devices.initDevices(hardwareMap);
        timer = new ElapsedTime();

        waitForStart();

        //carousel spinner is on the front left corner of the bot.

        moveWithEncoder(-10, 0.5);
        strafeToPosition(30, 0.2);
        //spin duck
        timeMarker = timer.seconds();

        duckServo.setPower(1.0); //TODO: might be negative
        while(timer.seconds() < timeMarker + 5){

        }
        duckServo.setPower(0);
        moveWithEncoder(-40,0.5);
    }

}
