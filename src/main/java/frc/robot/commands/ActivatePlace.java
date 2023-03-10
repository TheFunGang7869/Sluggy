// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.*;

import frc.robot.Constants.ArmSetpoint;


public class ActivatePlace extends SequentialCommandGroup {
    private static double arm_setpoint;

    public ActivatePlace(Arm arm, Extender extender, Gripper gripper, ArmSetpoint setpoint) {
        if (setpoint == ArmSetpoint.HIGH) {
            arm_setpoint = Constants.ArmConstants.highPlacePosition;
        }
        else if (setpoint == ArmSetpoint.MID){
            arm_setpoint = Constants.ArmConstants.midPlacePosition;
        }
        else {
            arm_setpoint = Constants.ArmConstants.lowPlacePosition;
        }

        addCommands(
            new SetArmPosition(arm, arm_setpoint),
            new SetExtenderPosition(extender, Constants.ExtenderConstants.extendPosition)
        );
    }    
}
