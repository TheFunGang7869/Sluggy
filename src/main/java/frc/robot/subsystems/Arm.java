package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Arm extends SubsystemBase {
    private CANSparkMax m_armMotor1;
    private CANSparkMax m_armMotor2;

    private SparkMaxPIDController m_pidController;
    private RelativeEncoder m_encoder;
    //public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, maxVel, minVel, maxAcc, allowedErr;

    public Arm() {
        m_armMotor1 = new CANSparkMax(10, MotorType.kBrushless);
       
        m_armMotor2 = new CANSparkMax(11, MotorType.kBrushless);
        m_armMotor2.follow(m_armMotor1);

        m_pidController = m_armMotor1.getPIDController();
        m_encoder = m_armMotor1.getEncoder();

        m_pidController.setP(Constants.ArmConstants.kP);
        m_pidController.setI(Constants.ArmConstants.kI);
        m_pidController.setD(Constants.ArmConstants.kD);
        m_pidController.setIZone(Constants.ArmConstants.kIz);
        m_pidController.setFF(Constants.ArmConstants.kFF);
        m_pidController.setOutputRange(Constants.ArmConstants.kMinOutput, Constants.ArmConstants.kMaxOutput);
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
}

