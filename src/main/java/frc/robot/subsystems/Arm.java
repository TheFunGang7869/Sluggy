package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Arm extends SubsystemBase {
    private CANSparkMax m_armMotor1;
    private RelativeEncoder m_encoder; //TODO: set up units!

    public Arm() {
        m_armMotor1 = new CANSparkMax(10, MotorType.kBrushless);
        m_armMotor1.setIdleMode(IdleMode.kBrake);
        m_armMotor1.setInverted(false);

        m_encoder = m_armMotor1.getEncoder();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    public double getArmPosition(){
        return m_encoder.getPosition();
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

