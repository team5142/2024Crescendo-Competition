package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.ClimberSubsystem;
public class ClimbersStop extends SequentialCommandGroup{
    public ClimbersStop(
        ClimberSubsystem m_climber

    ){
    addCommands(
        new InstantCommand(() -> m_climber.climber1Stop()),
        new InstantCommand(() -> m_climber.climber2Stop()));
    }
}