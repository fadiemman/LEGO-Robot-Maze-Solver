package Lab5;

import lejos.utility.Delay;

public class Challange {

	private static Movement robot = new Movement();

	private static Display screen = new Display();

	public static void main(String[] args) {

		screen.Menu();
		screen.Menu2();

		//robot.initialCalibration();

		if(screen.directionDetector==1)
		{
			checkRight();
		}
		
		if(screen.directionDetector==2)
		{
			checkLeft();
		}
		
		robot.distanceSensor.close();
	}

public static void checkRight(){
	
	while (true) {

		robot.turn(robot.ROBOT_ROTATION_RIGHT);

		robot.distance.fetchSample(robot.distanceSample, 0);

		if (robot.distanceSample[0] < robot.TILE_LENGTH) {

			Delay.msDelay(250);

			robot.turn(robot.ROBOT_ROTATION_LEFT);

			robot.distance.fetchSample(robot.distanceSample, 0);

			if (robot.distanceSample[0] < robot.TILE_LENGTH) {

				robot.turn(robot.ROBOT_ROTATION_LEFT);

				robot.distance.fetchSample(robot.distanceSample, 0);

				if (robot.distanceSample[0] < robot.TILE_LENGTH) {

					// deadEnd Detected

					robot.moveToDetectColor();

					if (screen.isColorDetected()) {

						break;
					}

					robot.calibration();

					robot.turn(robot.ROBOT_ROTATION_RIGHT);

					robot.moveToDetectColor();

					if (screen.isColorDetected()) {

						break;
					}

					robot.calibration();

					robot.turn(robot.ROBOT_ROTATION_RIGHT);

					robot.moveToDetectColor();

					if (screen.isColorDetected()) {

						break;
					}

					robot.calibration();

					robot.turn(robot.ROBOT_ROTATION_RIGHT);

					robot.moveForward35cm();

				} else {

					robot.moveForward35cm();

				}
			} else {

				robot.moveForward35cm();
			}
		} else {

			robot.moveForward35cm();

		}

	}

	
}


public static void checkLeft(){
	
	while (true) {

		robot.turn(robot.ROBOT_ROTATION_LEFT);

		robot.distance.fetchSample(robot.distanceSample, 0);

		if (robot.distanceSample[0] < robot.TILE_LENGTH) {

			Delay.msDelay(250);

			robot.turn(robot.ROBOT_ROTATION_RIGHT);

			robot.distance.fetchSample(robot.distanceSample, 0);

			if (robot.distanceSample[0] < robot.TILE_LENGTH) {

				robot.turn(robot.ROBOT_ROTATION_RIGHT);

				robot.distance.fetchSample(robot.distanceSample, 0);

				if (robot.distanceSample[0] < robot.TILE_LENGTH) {

					// deadEnd Detected

					robot.moveToDetectColor();

					if (screen.isColorDetected()) {

						break;
					}

					robot.calibration();

					robot.turn(robot.ROBOT_ROTATION_LEFT);

					robot.moveToDetectColor();

					if (screen.isColorDetected()) {

						break;
					}

					robot.calibration();

					robot.turn(robot.ROBOT_ROTATION_LEFT);

					robot.moveToDetectColor();

					if (screen.isColorDetected()) {

						break;
					}

					robot.calibration();

					robot.turn(robot.ROBOT_ROTATION_LEFT);

					robot.moveForward35cm();

				} else {

					robot.moveForward35cm();

				}
			} else {

				robot.moveForward35cm();
			}
		} else {

			robot.moveForward35cm();

		}

	}

}
}