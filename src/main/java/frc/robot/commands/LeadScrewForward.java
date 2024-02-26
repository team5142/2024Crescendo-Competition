package frc.robot.commands;
import java.util.Objects;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;

import frc.robot.subsystems.LeadScrewSubsystem;
public class LeadScrewForward extends SequentialCommandGroup{
    public LeadScrewForward(LeadScrewSubsystem m_leadscrew){
        if (Objects.nonNull(m_leadscrew)){
            addCommands(
                new InstantCommand(() -> m_leadscrew.leadScrewForward()));
        }
    }
}

