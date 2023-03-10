package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Extender;

public class SetExtenderPosition extends CommandBase {

    private final Extender m_extender;
    private double m_position;

    public SetExtenderPosition(Extender subsystem, double position) {
        m_extender = subsystem;
        m_position = position;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (m_extender.getExtenderPosition() < m_position){
            m_extender.setExtenderPower(Constants.ExtenderConstants.extenderPower);

        }
        else {
            m_extender.setExtenderPower(-Constants.ExtenderConstants.extenderPower);
        }


    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_extender.setExtenderPower(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if ( Math.abs(m_position - m_extender.getExtenderPosition())< Constants.ExtenderConstants.positionTolerance){
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
