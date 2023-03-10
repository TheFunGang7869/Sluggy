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
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.Constants.ArmSetpoint;

public class RobotContainer {
  private static RobotContainer m_robotContainer = new RobotContainer();

  // The robot's subsystems
  public final Extender m_extender = new Extender();
    public final Arm m_arm = new Arm();
    public final Gripper m_intake = new Gripper();
    public final DriveTrain m_driveTrain = new DriveTrain();

// Joysticks
private final Joystick joystick = new Joystick(0);
private final XboxController xboxController = new XboxController(1);
  
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  // Current place setpoint
  ArmSetpoint m_armSetpoint = ArmSetpoint.LOW;

  /**
  * The container for the robot.  Contains subsystems, OI devices, and commands.
  */
  private RobotContainer() {
    // Smartdashboard stuff... when we get to it
    /* SmartDashboard.putData(m_driveTrain);
    SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
    m_chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());
    SmartDashboard.putData("Auto Mode", m_chooser); */

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
    forwardTrigger.onTrue(new MoveArmForward(m_arm));

    // on pushing the joystick fully backward, trigger "move backward" command
    JoystickBoolean jbBackward=new JoystickBoolean(false, joystick);
    Trigger backwardTrigger = new Trigger(jbBackward::get);
    backwardTrigger.onTrue(new MoveArmBackward(m_arm));

    new JoystickButton(joystick, ButtonType.kTrigger.value).onTrue(Commands.runOnce(() -> setArmSetpointLow()));
    new JoystickButton(joystick, ButtonType.kTrigger.value).onTrue(Commands.runOnce(() -> setArmSetpointMid()));
    new JoystickButton(joystick, ButtonType.kTrigger.value).onTrue(Commands.runOnce(() -> setArmSetpointHigh()));

    new JoystickButton(joystick, ButtonType.kTrigger.value).onTrue(new ActivatePlace(m_arm, m_extender, m_intake, m_armSetpoint));
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
    return m_chooser.getSelected();
  }
  

}

