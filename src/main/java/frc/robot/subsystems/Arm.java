package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Arm extends SubsystemBase {
    private CANSparkMax armMotor1;
    private RelativeEncoder relEncoder; //TODO: set up units!

    private final Encoder absEncoder; //TODO: set up units!

    private double armInit;

    private DigitalInput limitSwitch;

    public Arm() {
        armMotor1 = new CANSparkMax(10, MotorType.kBrushless);
        armMotor1.setIdleMode(IdleMode.kBrake);
        armMotor1.setInverted(false);

        relEncoder = armMotor1.getEncoder();
        relEncoder.setPositionConversionFactor(1); //units!!

        absEncoder = new Encoder(0, 1, false);

        armInit = Constants.ArmConstants.absRelConversion * absEncoder.getDistance();
        relEncoder.setPosition(armInit);

        limitSwitch = new DigitalInput(3);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("Arm Position relative", getArmPosition());
        SmartDashboard.putNumber("Arm Position absolute", absEncoder.getDistance());
        SmartDashboard.putNumber("arm percent output", armMotor1.getAppliedOutput());
        SmartDashboard.putBoolean("Limit switch", limitSwitch.get());

        if(limitSwitch.get())
        {
            relEncoder.setPosition(0);
            armMotor1.set(0);
        }
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    public double getArmPosition(){
        return relEncoder.getPosition();
    }

    public void setArmPower(double power){
        armMotor1.set(power);
    }

    public void abort(){
        armMotor1.set(0);
    }

    public void armForward() {}
    public void armBackward() {}
}

