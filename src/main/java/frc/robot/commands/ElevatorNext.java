/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorNext extends Command {

  private int temp = 0;

  public ElevatorNext() {
    requires(Robot.m_lift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    temp = 0;
    Robot.m_lift.nextStage();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    temp++;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (Robot.m_lift.isAtNextStage() || (Robot.m_lift.getVelocity() == 0 && temp > 10));
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_lift.setNextStage();
    if (Robot.m_lift.getVelocity() < .1 && temp > 10) {
      Robot.m_lift.stop();
      Robot.m_lift.cheatStop();
    }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
