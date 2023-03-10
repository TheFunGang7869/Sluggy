// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.*;

/** Add your docs here. */
public class ExecuteIntake extends SequentialCommandGroup {
    
    public ExecuteIntake(Arm arm, Extender extender, Gripper gripper) {
        addCommands(

    gripper.closeCommand(),

    new SetExtenderPosition(extender, Constants.ExtenderConstants.retractPosition),

    new SetArmPosition(arm, Constants.ArmConstants.stowPosition)

        );
    }
}    
