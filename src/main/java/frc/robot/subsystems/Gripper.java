package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Gripper extends SubsystemBase {
    private WPI_TalonSRX gripperMotor;

    public Gripper() {
        gripperMotor = new WPI_TalonSRX(4);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // command for open gripper
    public CommandBase openCommand() {
        return this.runOnce(() -> gripperMotor.set(ControlMode.Position, Constants.GripperConstants.openPosition));
    }

    // Command for close gripper
    public CommandBase closeCommand() {
        return this.runOnce(() -> gripperMotor.set(ControlMode.Position, Constants.GripperConstants.closePosition));
    }
}

