package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;

import frc.robot.Constants;

import frc.robot.subsystems.DriveTrain;

public class HalfSpeedTankDrive extends CommandBase {

    private final DriveTrain m_driveTrain;
    private DoubleSupplier m_left;
    private DoubleSupplier m_right;

    public HalfSpeedTankDrive(DoubleSupplier left, DoubleSupplier right, DriveTrain subsystem) {
        m_left = left;
        m_right = right;

        m_driveTrain = subsystem;
        addRequirements(m_driveTrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {  
        double left = m_left.getAsDouble();
        double right = m_right.getAsDouble();

        left = axisFilter(left, Constants.DriveConstants.joystickYPower);
        left = left * Constants.DriveConstants.joystickYScale;
        right = axisFilter(right, Constants.DriveConstants.joystickXPower);

        m_driveTrain.drive(Constants.DriveConstants.halfSpeed * left, right);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_driveTrain.drive(0.0, 0.0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }

    // function to invert / filter joystick inputs before driving
    private double axisFilter(double x, double n){
        if(x > 0){
            x = Math.pow(x, n);
            x = x * -1;
        }
        else{
            x = x * -1;
            x = Math.pow(x, n);
        }
        return x;
    }
}
