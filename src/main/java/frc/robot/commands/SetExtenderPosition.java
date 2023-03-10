package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Extender;

public class SetExtenderPosition extends CommandBase {
    private final Extender m_extender;
    private double m_setpoint;

    public SetExtenderPosition(Extender subsystem, double setpoint) {
        m_extender = subsystem;
        addRequirements(m_extender);
        m_setpoint = setpoint;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (m_extender.getExtenderPosition() < m_setpoint){
            m_extender.setExtenderPower(Constants.ArmConstants.armPower);
        }
        else {
            m_extender.setExtenderPower(-Constants.ArmConstants.armPower);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_extender.abort();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return Math.abs(m_extender.getExtenderPosition() - m_setpoint) <= Constants.ExtenderConstants.positionTolerance;
    }

    @Override
    public boolean runsWhenDisabled(){
        return false;
    }
}
