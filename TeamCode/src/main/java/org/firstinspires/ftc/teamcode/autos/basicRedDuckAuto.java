package org.firstinspires.ftc.teamcode.autos;

import static org.firstinspires.ftc.teamcode.hardware.Control.auto.moveWithEncoder;
import static org.firstinspires.ftc.teamcode.hardware.Control.auto.strafeToPosition;
import static org.firstinspires.ftc.teamcode.hardware.Devices.duckServo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Devices;

public class basicRedDuckAuto extends LinearOpMode {
    ElapsedTime timer;
    double timeMarker;
    public void runOpMode() {
        Devices.initDevices(hardwareMap);
        timer = new ElapsedTime();

        waitForStart();

        //carousel spinner is on the front left corner of the bot.

        strafeToPosition(10, 0.5);
        moveWithEncoder(30, 0.2);

        //spin duck
        timeMarker = timer.seconds();

        duckServo.setPower(1.0); //TODO: might be negative
        while(timer.seconds() < timeMarker + 5){

        }
        duckServo.setPower(0);
        moveWithEncoder(40,0.5);
    }
}
