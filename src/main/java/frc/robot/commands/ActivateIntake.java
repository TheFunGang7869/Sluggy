// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.*;

/** Add your docs here. */
public class ActivateIntake extends SequentialCommandGroup {

    public ActivateIntake(Arm arm, Extender extender) {
        addCommands(
            
    new SetArmPosition(arm, Constants.ArmConstants.intakePosition)

    //new SetExtenderPosition(extender, Constants.ExtenderConstants.extendPosition) 
    );
    
    }

}