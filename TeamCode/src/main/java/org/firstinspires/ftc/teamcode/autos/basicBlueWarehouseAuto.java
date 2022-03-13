package org.firstinspires.ftc.teamcode.autos;

import static org.firstinspires.ftc.teamcode.hardware.Control.auto.moveWithEncoder;
import static org.firstinspires.ftc.teamcode.hardware.Control.auto.strafeToPosition;
import static org.firstinspires.ftc.teamcode.hardware.Devices.duckServo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Devices;

public class basicBlueWarehouseAuto extends LinearOpMode {
    ElapsedTime timer;
    public void runOpMode() {
        Devices.initDevices(hardwareMap);
        timer = new ElapsedTime();

        waitForStart();

        //carousel spinner is on the front left corner of the bot.

        strafeToPosition(30, 0.5);
        moveWithEncoder(70,0.5);
    }
}
