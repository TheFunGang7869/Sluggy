package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer; // has methods restart() and get()


public class AutonomousCommand extends CommandBase {

    private final DriveTrain m_driveTrain;
    private final Timer m_autoTimer;

    public AutonomousCommand(DriveTrain autoDrive) {
        // m_subsystem = subsystem
        // addRequirements(m_subsystem);
        m_driveTrain = autoDrive;
        addRequirements(m_driveTrain);
        m_autoTimer = new Timer();

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_autoTimer.restart();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_driveTrain.drive(Constants.AutoConstants.autoSpeed, 0);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(m_autoTimer.get() >= Constants.AutoConstants.autoTime){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
