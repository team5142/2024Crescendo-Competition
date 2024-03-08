package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import java.util.Objects;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.ArmSubsystem;

public class LeadScrewBackwardCommand extends SequentialCommandGroup{
    public LeadScrewBackwardCommand(ArmSubsystem m_leadscrew){
        if (Objects.nonNull(m_leadscrew)){
            addCommands(
                new RunCommand(() -> m_leadscrew.leadScrewBackward()));
        }
    }
}

