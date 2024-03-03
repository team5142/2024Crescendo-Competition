// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.OrientationConstants.Orientations;
import frc.robot.commands.IntakeCommands.IntakeStopCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TwoNoteCenterAuto extends SequentialCommandGroup {
  /** Creates a new TwoNoteAuto. */
  public TwoNoteCenterAuto(
    ArmSubsystem m_arm,
    IntakeSubsystem m_intake,
    ShooterSubsystem m_shooter
  ) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(

      // Turn on the shooter, orient to SUBWOOFER, check that shooter is at speed, feed intake to shoot, wait .5 seconds
      new OneNoteAuto(m_arm, m_shooter, m_intake),
      
      // with the shooter and intake running, orient arm to the intake position AND starting to move to pick up the 2nd note
      new ParallelCommandGroup(
        new AutoMoveToOrientationCommand(m_arm, m_shooter, m_intake, Orientations.AUTO_INTAKE),
        // this trajectory was modified slightly to move through the note to intake it
        new RunTrajectorySequenceRobotAtStartPoint("5142_ThreeNotePart1")
      ),

      // After moving, wait .5 seconds to pretend to pick up a note, REPLACE THIS WITH NOTE DETECTION
      new WaitCommand(.5), 

      // Stop the intake so we don't shoot it - this might just need to always be a part of the intake subsystem
      new IntakeStopCommand(m_intake),

      // with the shooter running, the intake off, and a note loaded, orient arm to the AUTO_PODIUM position 
      new AutoMoveToOrientationCommand(m_arm, m_shooter, m_intake, Orientations.AUTO_PODIUM),
      
      // Make sure the shooter is still at speed
      new CheckToShoot(m_shooter, m_intake),

      // Feed the intake to actually shoot (still using Podium speed and orientation)
      new InstantCommand(() -> m_intake.runIntake()),

      // TEMPORARY Wait .5 seconds for the shot to be fired
      new WaitCommand(1),

      // At the end of auto use the TELEOP orientation command to move to TRAVEL to turn off shooter and intake
      new MoveToOrientationCommand(m_arm, m_shooter, m_intake, Orientations.TRAVEL)
      
      // END AUTO
    );
  }
}
