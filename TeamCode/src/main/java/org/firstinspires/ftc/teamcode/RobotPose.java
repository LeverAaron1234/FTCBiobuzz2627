package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;

public class RobotPose {
    /// Stores the robot pose from autonomous, which is read in teleop.
    public static Pose2d lastRobotPose = null;


    /// Stores any settings for the robot
    public static boolean redTeam = false;
    /// Do not use! Only kept for compatibility with the DECODE robot.
    public static boolean startFar = false;

    /// This tells the teleOp that a change happened, and to use the changed pose and/or settings
    public static boolean updated = false;
}