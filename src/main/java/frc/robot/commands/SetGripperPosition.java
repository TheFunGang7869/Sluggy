package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Gripper;

public class SetGripperPosition extends CommandBase {

    private final Gripper m_gripper;
    private double m_position;

    public SetGripperPosition(Gripper subsystem, double position) {
        m_gripper = subsystem;
        m_position = position;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (m_gripper.getGripperPosition() < m_position){
            m_gripper.setGripperPower(Constants.GripperConstants.gripperPower);

        }
        else {
            m_gripper.setGripperPower(-Constants.GripperConstants.gripperPower);
        }


    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_gripper.setGripperPower(0);
    }

    

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if ( Math.abs(m_position - m_gripper.getGripperPosition())< Constants.GripperConstants.positionTolerance){
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public boolean runsWhenDisabled(){
        return false;
    }

}
