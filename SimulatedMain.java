package Lab5;

import MazebotSim.MazebotSimulation;

public class SimulatedMain {

	public static void main(String[] args) {

		MazebotSimulation sim = new MazebotSimulation("Mazes/maze_1_3by4.png", 1.5,  1.13);
		sim.setRobotPosition(0.17, 0.60, 90);
		sim.startSimulation();
		
		Challange.main(new String[0]);		

		
		sim.stopSimulation();
		

	}

}
