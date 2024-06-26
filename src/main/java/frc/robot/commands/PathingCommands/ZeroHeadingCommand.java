// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.PathingCommands;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPoint;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.LEDSubsystem.BlinkinPattern;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ZeroHeadingCommand extends SequentialCommandGroup {
  /** Creates a new ZeroHeadingCommand. */
  public ZeroHeadingCommand() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new InstantCommand(() -> RobotContainer.LEDs.setPattern(BlinkinPattern.DARK_RED)),            
      new AutonomousTrajectoryRioCommand(
        PathPlanner.generatePath(
          new PathConstraints(Constants.SwerveChassis.MAX_VELOCITY,Constants.SwerveChassis.MAX_ACCELERATION ), 
          new PathPoint(
            RobotContainer.driveSubsystem.getPose().getTranslation(), 
            RobotContainer.driveSubsystem.getPose().getRotation(),
            Rotation2d.fromDegrees(0)
          ), // position, heading(direction of travel), holonomic rotation
          new PathPoint(
            RobotContainer.driveSubsystem.getPose().getTranslation(), 
            Rotation2d.fromDegrees(0), 
            Rotation2d.fromDegrees(0) // position, heading(direction of travel), holonomic rotation
    )
        )
      ) // Run a trajectory
    );
  }
}
