package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.ArmSubsystem;
public class ArmTo360Degrees extends SequentialCommandGroup{
    public ArmTo360Degrees(
        ArmSubsystem m_arm

    ){
    addCommands(
        new RunCommand(() -> m_arm.armTo360Degrees()));
    }
}