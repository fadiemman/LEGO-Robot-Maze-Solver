package Lab5;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class Display {

	private int colorDetector;
	int directionDetector;

	private EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);

	private SensorMode colorID = colorSensor.getColorIDMode();

	private float colorIDSample[] = new float[colorID.sampleSize()];

	public void beep() {

		Sound.setVolume(80);
		Sound.playTone(200, 1000);
	}

	public void Menu() {

		int cursor_location_row = 1;
		final int red_location_row = 2;
		final int blue_location_row = 1;
		final int green_location_row = 3;
		final int yellow_location_row = 4;
		final int Cursor_location_colmn = 0;
		final int red_location_colmn = 1;
		final int blue_location_colmn = 1;
		final int green_location_colmn = 1;
		final int yellow_location_colmn = 1;

		final int delay = 300;
		String r = "Red";
		String b = "Blue";
		String g = "Green";
		String cursor = ">";
		String nul = " ";
		String y = "Yellow";
		LCD.drawString(r, red_location_colmn, red_location_row);
		LCD.drawString(b, blue_location_colmn, blue_location_row);
		LCD.drawString(g, green_location_colmn, green_location_row);
		LCD.drawString(y, yellow_location_colmn, yellow_location_row);
		LCD.drawString(cursor, Cursor_location_colmn, cursor_location_row);

		while (true) {
			if (Button.ENTER.isDown() && cursor_location_row == 2) {

				//beep();

				colorDetector = Color.RED;
				break;
			}

			if (Button.ENTER.isDown() && cursor_location_row == 1) {

				//beep();
				colorDetector = Color.BLUE;
				break;
			}

			if (Button.ENTER.isDown() && cursor_location_row == 3) {

				//beep();
				colorDetector = Color.GREEN;
				break;
			}

			if (Button.ENTER.isDown() && cursor_location_row == 4) {

				//beep();
				colorDetector = Color.YELLOW;
				break;
			}

			if (Button.DOWN.isDown() && cursor_location_row < 4) {

				LCD.drawString(nul, Cursor_location_colmn, cursor_location_row);
				cursor_location_row = cursor_location_row + 1;
				LCD.drawString(cursor, Cursor_location_colmn,
						cursor_location_row);
			}

			if (Button.UP.isDown() && cursor_location_row > 1) {

				LCD.drawString(nul, Cursor_location_colmn, cursor_location_row);
				cursor_location_row = cursor_location_row - 1;
				LCD.drawString(cursor, Cursor_location_colmn,
						cursor_location_row);
			}
			Delay.msDelay(delay);
		}

		LCD.clearDisplay();

	}

	public boolean isColorDetected() {

		Delay.msDelay(2500);
		
		for(int i=0; i<200; i++){
		colorID.fetchSample(colorIDSample, 0);
		}
		Delay.msDelay(250);

		if ((int) colorIDSample[0] == colorDetector) {
			beep();
			return true;
		} else {
			return false;
		}
	}

	public void Menu2(){
		
		int cursor_location_row = 1;
		final int left_location_row = 2;
		final int right_location_row = 1;
		
		
		final int Cursor_location_colmn = 0;
		final int right_location_colmn = 1;
		final int left_location_colmn = 1;
		
		

		final int delay = 300;
		String l = "Left";
		String r = "Right";
		
		String cursor = ">";
		String nul = " ";
		
		LCD.drawString(l, left_location_colmn, left_location_row);
		LCD.drawString(r, right_location_colmn, right_location_row);
		
		
		LCD.drawString(cursor, Cursor_location_colmn, cursor_location_row);

		Delay.msDelay(delay);
		while (true) {
			if (Button.ENTER.isDown() && cursor_location_row == 2) {

				beep();

				directionDetector = 2;
				break;
			}

			if (Button.ENTER.isDown() && cursor_location_row == 1) {

				beep();
				directionDetector = 1;
				break;
			}

			
			if (Button.DOWN.isDown() && cursor_location_row < 2) {

				LCD.drawString(nul, Cursor_location_colmn, cursor_location_row);
				cursor_location_row = cursor_location_row + 1;
				LCD.drawString(cursor, Cursor_location_colmn,
						cursor_location_row);
			}

			if (Button.UP.isDown() && cursor_location_row > 1) {

				LCD.drawString(nul, Cursor_location_colmn, cursor_location_row);
				cursor_location_row = cursor_location_row - 1;
				LCD.drawString(cursor, Cursor_location_colmn,
						cursor_location_row);
			}
			Delay.msDelay(delay);
		}

		LCD.clearDisplay();
		
	}


}
