package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetArmPosition extends CommandBase {

    private final Arm m_arm;
    private double m_angle;

    private PIDController pid = new PIDController(0, 0, 0);

    public SetArmPosition(Arm subsystem, double angle) {
        m_arm = subsystem;
        m_angle = angle;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (m_arm.getArmPosition() < m_angle){
            m_arm.setArmPower(Constants.ArmConstants.armPower);

        }
        else {
            m_arm.setArmPower(-Constants.ArmConstants.armPower);
        }

        // PID stuff
       /*double ff = 0.01 * Math.cos(m_arm.getArmPosition() *2*Math.PI/360);
       double pidVal = pid.calculate(m_arm.getArmPosition(), m_angle);
       pidVal = Constants.ArmConstants.kP * ( m_angle - m_arm.getArmPosition());

       m_arm.setArmPower(ff + pidVal);*/
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_arm.setArmPower(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if ( Math.abs(m_angle - m_arm.getArmPosition())< Constants.ArmConstants.positionTolerance){
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
