package frc.robot;
import edu.wpi.first.wpilibj.Joystick;


public class JoystickBoolean {
    private Joystick m_joystick;
    private boolean m_forward;
    
    public JoystickBoolean (boolean forward, Joystick joystick){
        m_joystick = joystick;
        m_forward = forward;

    }

    public boolean get() {

        if((m_joystick.getX()>.95)&(m_forward==true)){
            return true;
        }

        else if((m_joystick.getX()<-.95)&(m_forward==false)){
            return true;
        }

        else{
            return false;
        }

    }


}
