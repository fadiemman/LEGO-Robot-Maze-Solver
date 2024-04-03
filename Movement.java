package Lab5;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Movement {

	private final float CALIBRATION_DIST = 0.15f;
	private final float COLOR_IDENTIFICATION_RANGE = 0.04f;
	final float TILE_LENGTH = 0.35f;
	final int ROBOT_ROTATION_RIGHT = -90; 
	final int ROBOT_ROTATION_LEFT = 90;

	private EV3GyroSensor gyroSensor = new EV3GyroSensor(SensorPort.S3);

	private SampleProvider angle = gyroSensor.getAngleMode(); 
	private float angleSample[] = new float[angle.sampleSize()]; 

	private EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(
			MotorPort.C);
	private EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(
			MotorPort.B);

	EV3UltrasonicSensor distanceSensor = new EV3UltrasonicSensor(SensorPort.S4);

	SampleProvider distance = distanceSensor.getDistanceMode();

	float distanceSample[] = new float[distance.sampleSize()];

	public void calibration() {
		distance.fetchSample(distanceSample, 0);
		if (distanceSample[0] < TILE_LENGTH)

		{
			Delay.msDelay(150);
			if (distanceSample[0] < CALIBRATION_DIST) {

				moveBackward();
				while (distanceSample[0] <= CALIBRATION_DIST) {
					distance.fetchSample(distanceSample, 0);

				}
				stop();
			} else {
				{
					moveForward();
					while (distanceSample[0] >= CALIBRATION_DIST) {
						distance.fetchSample(distanceSample, 0);

					}
					stop();
				}

			}
		}

	}

	public void turn(int degrees) {

		final int ANGLE_LOCATION_ROW = 2;
		final int ANGLE_LOACTION_COLUMN = 1;

		angleSample[0] = 0;
		gyroSensor.reset();
		Delay.msDelay(150);

		while (Math.abs(angleSample[0]) < Math.abs(degrees)) {

			angle.fetchSample(angleSample, 0);

			int varyingSpeed = (int) (10 * (Math.abs(degrees) - Math
					.abs(angleSample[0])));
			rightMotor.setSpeed(varyingSpeed);
			leftMotor.setSpeed(varyingSpeed);

			if (degrees > 0) {

				rightMotor.backward();
				leftMotor.forward();
				
				//Delay.msDelay(110);

				//stop();
				
			}
			if (degrees < 0) {

				rightMotor.forward();
				leftMotor.backward();
				
				//Delay.msDelay(110);

				//stop();
				
			}
			angle.fetchSample(angleSample, 0);

			LCD.drawString("Angle " + angleSample[0], ANGLE_LOACTION_COLUMN,
					ANGLE_LOCATION_ROW);

		}

		stop();
		
		calibration();

		// gyroSensor.close();

	}

	public void moveForward35cm() {

		int speed = 200;
		final int DEGREES = -2188; // motor should be rotated 2188 degrees so
									// the robot can move 35cm (a tile).
		Delay.msDelay(150);
		rightMotor.setSpeed(speed);
		leftMotor.setSpeed(speed);
		rightMotor.rotate(DEGREES, true);
		leftMotor.rotate(DEGREES, false);
		calibration();
	}

	public void moveBackward() {
		Delay.msDelay(150);
		rightMotor.setSpeed(200);
		leftMotor.setSpeed(200);
		rightMotor.forward();
		leftMotor.forward();

	}

	public void moveForward() {
		Delay.msDelay(150);
		rightMotor.setSpeed(200);
		leftMotor.setSpeed(200);
		rightMotor.backward();
		leftMotor.backward();
	}

	public void stop() {
		rightMotor.stop(true);
		leftMotor.stop(false);
	}

	public void initialCalibration() {
		boolean frontNotCalibrated = true;
		boolean rightNotCalibrated = true;

		distance.fetchSample(distanceSample, 0);
		if (distanceSample[0] < TILE_LENGTH) {
			calibration();
			frontNotCalibrated = false;
		}
		turn(ROBOT_ROTATION_RIGHT);

		distance.fetchSample(distanceSample, 0);
		if (distanceSample[0] < TILE_LENGTH) {
			calibration();
			rightNotCalibrated = false;
		}

		turn(ROBOT_ROTATION_RIGHT);

		if (frontNotCalibrated) {
			calibration();
		}
		turn(ROBOT_ROTATION_RIGHT);

		if (rightNotCalibrated) {
			calibration();
		}

		turn(ROBOT_ROTATION_RIGHT);
	}

	public void moveToDetectColor() {

		distance.fetchSample(distanceSample, 0);

		moveForward();
		while (distanceSample[0] >= COLOR_IDENTIFICATION_RANGE) {
			distance.fetchSample(distanceSample, 0);
		}

		stop();
	}

}
