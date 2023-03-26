package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class Gripper extends SubsystemBase {
    private WPI_TalonSRX m_gripperTalon;

    public Gripper() {
        m_gripperTalon = new WPI_TalonSRX(9);

        /* m_gripperTalon.configFactoryDefault();
        m_gripperTalon.configPeakCurrentLimit(15, Constants.defaultTimeout);
        m_gripperTalon.configPeakCurrentDuration(500, Constants.defaultTimeout);
        m_gripperTalon.configContinuousCurrentLimit(10, Constants.defaultTimeout);
        m_gripperTalon.enableCurrentLimit(true); */

        m_gripperTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        m_gripperTalon.setSelectedSensorPosition(0);
        m_gripperTalon.setSensorPhase(false);
        m_gripperTalon.setInverted(false);

        m_gripperTalon.setNeutralMode(NeutralMode.Brake);

        /* m_gripperTalon.configNominalOutputForward(0, Constants.defaultTimeout);
        m_gripperTalon.configNominalOutputReverse(0, Constants.defaultTimeout);
        m_gripperTalon.configPeakOutputForward(1, Constants.defaultTimeout);
        m_gripperTalon.configPeakOutputReverse(-1, Constants.defaultTimeout);

        m_gripperTalon.configForwardSoftLimitThreshold(Constants.GripperConstants.forwardSoftLimit, Constants.defaultTimeout);
        m_gripperTalon.configForwardSoftLimitEnable(true);

        m_gripperTalon.configForwardSoftLimitThreshold(Constants.GripperConstants.backwardSoftLimit, Constants.defaultTimeout);
        m_gripperTalon.configForwardSoftLimitEnable(true);

        m_gripperTalon.selectProfileSlot(0, 0);
        m_gripperTalon.config_kF(0, 0, Constants.defaultTimeout);
        m_gripperTalon.config_kP(0, 0, Constants.defaultTimeout);
        m_gripperTalon.config_kI(0, 0, Constants.defaultTimeout);
        m_gripperTalon.config_kD(0, 0, Constants.defaultTimeout);
        m_gripperTalon.configMotionCruiseVelocity(4667, Constants.defaultTimeout);
        m_gripperTalon.configMotionAcceleration(4667, Constants.defaultTimeout); */
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("Gripper Position)", getGripperPosition());

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    public double getGripperPosition(){
        //return eripperTalonSRX.getSelectedSensorPosition();
        return m_gripperTalon.getSelectedSensorPosition();
    }

    public void setGripperPower(double power){
        m_gripperTalon.set(power);
    }

    public void abort(){
        m_gripperTalon.set(0);
    }

    /* // command for open gripper
    public CommandBase openCommand() {
        return this.runOnce(() -> m_gripperTalon.set(ControlMode.Position, Constants.GripperConstants.openPosition));
    }

    // Command for close gripper
    public CommandBase closeCommand() {
        return this.runOnce(() -> m_gripperTalon.set(ControlMode.Position, Constants.GripperConstants.closePosition));
    } */
}



