package frc.robot.commands.GPMCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import java.util.Objects;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants.OrientationConstants.Orientations;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class MoveToOrientationCommand extends SequentialCommandGroup {
    public MoveToOrientationCommand(
            ArmSubsystem m_arm,
            ShooterSubsystem m_shooter,
            IntakeSubsystem m_intake,
            Orientations orientation) {

        if ((Objects.nonNull(m_arm)) && (Objects.nonNull(m_shooter)) && (Objects.nonNull(m_intake))){
            //System.out.println("*** m_leadscrew and arm were not null");   
            addCommands(
                new ParallelCommandGroup(
                    //new InstantCommand(() -> System.out.println("----->>> ORIENTING TO: " + orientation.label)),
                    new InstantCommand(() -> m_arm.moveToPosition(orientation)),
                    //new InstantCommand(() -> System.out.println("**TURN OFF SHOOTER" + orientation.label)),
                    new InstantCommand(() -> m_shooter.stopShooter(orientation)),
                    //new InstantCommand(() -> System.out.println("**TURN OFF INTAKE" + orientation.label)),
                    //new InstantCommand(m_intake::stopIntake),
                    // CHANGE TO DELAY INTAKE
                    new InstantCommand(() -> m_intake.stopIntake(false)),
                    new WaitCommand(1)));
        }
        if (Objects.nonNull(m_shooter)){
            if (orientation.shooterOn) {
                    addCommands(
                        //new InstantCommand(() -> System.out.println("**TURN ON SHOOTER" + orientation.label)),
                        new InstantCommand(() -> m_shooter.runShooter(orientation)));
            } 
        }
        if (Objects.nonNull(m_intake)){
            if (orientation.intakeOn) {
                addCommands(
                    //new InstantCommand(() -> System.out.println("**TURN ON INTAKE" + orientation.label)),
                    new InstantCommand(() -> m_intake.runIntake(false)));
                    
            }
            
        }
    }
}