package frc.robot; //implicitly imports Robot, Constants, JoystickBoolean
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.Constants.ArmSetpoint;

public class RobotContainer {
  private static RobotContainer m_robotContainer = new RobotContainer();

  // The robot's subsystems
  public final Extender m_extender = new Extender();
    public final Arm m_arm = new Arm();
    public final DriveTrain m_driveTrain = new DriveTrain();

// Joysticks
private final Joystick joystick = new Joystick(0);
private final XboxController xboxController = new XboxController(1);
  
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  // Current place setpoint
  ArmSetpoint m_armSetpoint = ArmSetpoint.LOW;

  private AutonomousCommand autoCommand = new AutonomousCommand(m_driveTrain);

  /**
  * The container for the robot.  Contains subsystems, OI devices, and commands.
  */
  private RobotContainer() {
    // Smartdashboard stuff... when we get to it
    /* SmartDashboard.putData(m_driveTrain);
    SmartDashboard.putData("Autonomous Command", new AutonomousCommand(m_driveTrain));
    m_chooser.setDefaultOption("Autonomous Command", new AutonomousCommand(m_driveTrain));
    SmartDashboard.putData("Auto Mode", m_chooser); */
    SmartDashboard.putData("arm position intake", new SetArmPosition(m_arm, Constants.ArmConstants.intakePosition));
    SmartDashboard.putData("arm position medium", new SetArmPosition(m_arm, Constants.ArmConstants.midPlacePosition));
    SmartDashboard.putData("arm position stow", new SetArmPosition(m_arm, Constants.ArmConstants.stowPosition));


    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    m_driveTrain.setDefaultCommand(new TankDrive(() -> getXboxController().getLeftY(),
      () -> getXboxController().getRightX(), m_driveTrain));
  }

  public static RobotContainer getInstance() {
    return m_robotContainer;
  }

  private void configureButtonBindings() {
    // on pushing the joystick fully forward, trigger "move forward" command
    JoystickBoolean jbForward=new JoystickBoolean(true, joystick);
    Trigger forwardTrigger = new Trigger(jbForward::get);
    forwardTrigger.onTrue(new SetArmPosition(m_arm, Constants.ArmConstants.forwardPosition));

    // on pushing the joystick fully backward, trigger "move backward" command
    JoystickBoolean jbBackward=new JoystickBoolean(false, joystick);
    Trigger backwardTrigger = new Trigger(jbBackward::get);
    backwardTrigger.onTrue(new SetArmPosition(m_arm, Constants.ArmConstants.backwardPosition));

    new JoystickButton(joystick, 8).onTrue(Commands.runOnce(() -> setArmSetpointLow()));
    new JoystickButton(joystick, 7).onTrue(Commands.runOnce(() -> setArmSetpointMid()));
    new JoystickButton(joystick, 6).onTrue(Commands.runOnce(() -> setArmSetpointHigh()));

    new JoystickButton(joystick, 1).onTrue(new ActivatePlace(m_arm, m_extender, m_armSetpoint));

    //new JoystickButton(joystick, 1).onTrue(new ExecutePlace(m_arm, m_extender));

    new JoystickButton(joystick, 3).onTrue(new ActivateIntake(m_arm, m_extender));

    //new JoystickButton(joystick, 5). onTrue(new ExecuteIntake(m_arm, m_extender));

    /*new JoystickButton(xboxController, Button.kLeftBumper.value).whileTrue(new HalfSpeedTankDrive(() -> xboxController.getLeftY(), 
            () -> xboxController.getRightX(), m_driveTrain));*/
  }

  public void setArmSetpointLow(){
    m_armSetpoint = ArmSetpoint.LOW;
  }

  public void setArmSetpointMid(){
    m_armSetpoint = ArmSetpoint.MID;
  }

  public void setArmSetpointHigh(){
    m_armSetpoint = ArmSetpoint.HIGH;
  }


public XboxController getXboxController() {
      return xboxController;
    }

public Joystick getJoystick() {
        return joystick;
    }



  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
  */
  public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    // return m_chooser.getSelected();
    return autoCommand;
  }
  

}

