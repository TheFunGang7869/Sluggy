package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class Extender extends SubsystemBase {
    private WPI_TalonSRX extenderTalonSRX;

    private Encoder m_encoder;

    public Extender() {
        extenderTalonSRX = new WPI_TalonSRX(8);
        // extenderTalonSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.defaultTimeout);

        m_encoder = new Encoder(0, 0);
        m_encoder.setDistancePerPulse(1);
     }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("Extender Position", getExtenderPosition());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation
    }

    public double getExtenderPosition(){
        //return extenderTalonSRX.getSelectedSensorPosition();
        return m_encoder.getDistance();
    }

    public void setExtenderPower(double power){
        extenderTalonSRX.set(power);
    }

    public void abort(){
        extenderTalonSRX.set(0);
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

