package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Gripper;

public class SetGripperTimed extends CommandBase {

    private final Gripper m_gripper;
    private boolean m_open;
    private final Timer m_gripperTimer;
    
    public SetGripperTimed(Gripper subsystem, boolean open) {
        m_gripper = subsystem;
        m_open = open;
        m_gripperTimer = new Timer();
    }


    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_gripperTimer.restart();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (m_open == true){
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
        if(m_gripperTimer.get() >= Constants.GripperConstants.gripperTime){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean runsWhenDisabled(){
        return false;
    }

}
