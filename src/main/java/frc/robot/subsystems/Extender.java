package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Extender extends SubsystemBase {
    private WPI_TalonSRX extenderTalonSRX;

    public Extender() {
        extenderTalonSRX = new WPI_TalonSRX(8);
     }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation
    }

    // Command for full extend
    public CommandBase extendCommand() {
        return this.runOnce(() -> extenderTalonSRX.set(ControlMode.Position, Constants.ExtenderConstants.extendPosition));
    }

    // Command for full extend
    public CommandBase retractCommand() {
        return this.runOnce(() -> extenderTalonSRX.set(ControlMode.Position, Constants.ExtenderConstants.retractPosition));
    }
}

