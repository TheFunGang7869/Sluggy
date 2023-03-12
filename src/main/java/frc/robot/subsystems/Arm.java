package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Arm extends SubsystemBase {
    private CANSparkMax m_armMotor1;
    private RelativeEncoder m_relEncoder; //TODO: set up units!

    private final Encoder m_absEncoder; //TODO: set up units!

    private double m_armInit;

    public Arm() {
        m_armMotor1 = new CANSparkMax(10, MotorType.kBrushless);
        m_armMotor1.setIdleMode(IdleMode.kBrake);
        m_armMotor1.setInverted(false);

        m_relEncoder = m_armMotor1.getEncoder();
        m_relEncoder.setPositionConversionFactor(1); //units!!

        m_absEncoder = new Encoder(0, 1, false);

        m_armInit = Constants.ArmConstants.absRelConversion * m_absEncoder.getDistance();
        m_relEncoder.setPosition(m_armInit);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("Arm Position", getArmPosition());
        SmartDashboard.putNumber("Arm Position absolute", m_absEncoder.getDistance());
        SmartDashboard.putNumber("arm percent output", m_armMotor1.getAppliedOutput());
        SmartDashboard.putNumber("test value", 7);
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    public double getArmPosition(){
        return m_relEncoder.getPosition();
    }

    public void setArmPower(double power){
        m_armMotor1.set(power);
    }

    public void abort(){
        m_armMotor1.set(0);
    }

    public void armForward() {}
    public void armBackward() {}
}

