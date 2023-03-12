package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Extender extends SubsystemBase {
    private CANSparkMax m_extenderSparkMax;
    private RelativeEncoder m_encoder; //TODO: set up units!

    public Extender() {
        m_extenderSparkMax = new CANSparkMax(8, MotorType.kBrushless);
        m_extenderSparkMax.setIdleMode(IdleMode.kBrake);
        m_extenderSparkMax.setInverted(false);

        m_encoder = m_extenderSparkMax.getEncoder();
        m_encoder.setPositionConversionFactor(1); //units!!
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
        return m_encoder.getPosition();
    }

    public void setExtenderPower(double power){
        m_extenderSparkMax.set(power);
    }

    public void abort(){
        m_extenderSparkMax.set(0);
    }
}

