package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj2.command.CommandBase;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class Arm extends SubsystemBase {
    private final CANSparkMax m_armSparkMAX1;
    // private final CANSparkMax m_armSparkMAX2;
    private final SparkMaxPIDController m_pidController;
    private final RelativeEncoder m_encoder;

    private double horizontalFF;
    private double ff;
    private final double slopeFF = (Constants.ArmConstants.maxFF - Constants.ArmConstants.minFF) 
            / (Constants.ExtenderConstants.extendPosition - Constants.ExtenderConstants.retractPosition);

    private WPI_TalonSRX m_extenderTalonSRX;

    public Arm() {
        //create arm motor and set constants
        m_armSparkMAX1 = new CANSparkMax(10, MotorType.kBrushless);

        m_armSparkMAX1.setInverted(false);

        m_encoder = m_armSparkMAX1.getEncoder();
        m_encoder.setPositionConversionFactor(2 * Math.PI / Constants.ArmConstants.conversion);
        m_encoder.setPosition(0);

        m_pidController = m_armSparkMAX1.getPIDController();
        m_pidController.setP(Constants.ArmConstants.kP);
        m_pidController.setI(Constants.ArmConstants.kI);
        m_pidController.setD(Constants.ArmConstants.kD);
        m_pidController.setIZone(Constants.ArmConstants.kIz);
        m_pidController.setFF(Constants.ArmConstants.kFF);
        m_pidController.setOutputRange(Constants.ArmConstants.kMinOutput, Constants.ArmConstants.kMaxOutput);

        m_armSparkMAX1.setIdleMode(IdleMode.kBrake);
        
        //create extender motor and set constants
        m_extenderTalonSRX = new WPI_TalonSRX(8);
        m_extenderTalonSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.defaultTimeout);
        m_extenderTalonSRX.setSelectedSensorPosition(0);
        m_extenderTalonSRX.setInverted(false);
        m_extenderTalonSRX.setNeutralMode(NeutralMode.Brake);

        m_extenderTalonSRX.configForwardSoftLimitThreshold(Constants.ExtenderConstants.forwardLimit);
        m_extenderTalonSRX.configForwardSoftLimitEnable(true);
        m_extenderTalonSRX.configForwardSoftLimitThreshold(Constants.ExtenderConstants.backwardLimit);
        m_extenderTalonSRX.configForwardSoftLimitEnable(true);
    
        m_extenderTalonSRX.config_kF(0, Constants.ExtenderConstants.kFF);
        m_extenderTalonSRX.config_kP(0, Constants.ExtenderConstants.kP);
        m_extenderTalonSRX.config_kI(0, Constants.ExtenderConstants.kI);
        m_extenderTalonSRX.config_kD(0, Constants.ExtenderConstants.kD);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void setArmPosition(double angle) {
        horizontalFF = slopeFF * (m_extenderTalonSRX.getSelectedSensorPosition() - Constants.ExtenderConstants.retractPosition) + Constants.ArmConstants.minFF;
        ff = horizontalFF * Math.cos(m_encoder.getPosition());
        m_pidController.setReference(angle, ControlType.kPosition, 0, ff);
    }

    public void armForward() {
        m_pidController.setReference(Constants.ArmConstants.forwardPosition, CANSparkMax.ControlType.kPosition);
        //TODO: set arbitrary feed forward! w*cos(angle)

    }

    public void armBackward() {
        m_pidController.setReference(Constants.ArmConstants.backwardPosition, CANSparkMax.ControlType.kPosition);
        //armTalonSRX1.set(ControlMode.Position, 0);
    }

    public void abort() {
      //  armTalonSRX1.set()
        //to do for later, cancel movement
    }

    /* INLINE COMMANDS */

    // Command for full extend
    public CommandBase extendCommand() {
        return this.runOnce(() -> m_extenderTalonSRX.set(ControlMode.Position, Constants.ExtenderConstants.extendPosition));
    }
    
    // Command for full extend
    public CommandBase retractCommand() {
        return this.runOnce(() -> m_extenderTalonSRX.set(ControlMode.Position, Constants.ExtenderConstants.retractPosition));
    }
    
}

