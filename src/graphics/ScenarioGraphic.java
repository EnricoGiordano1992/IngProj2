package graphics;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import car.Car;

public class ScenarioGraphic {

	WallGraphic wall;
	DebugInterface debug;
	
	ArrayList<CarGraphic> cars;
	ArrayList<JLabel> display;

	StationGraphic station;

	public ScenarioGraphic(){

		wall = new WallGraphic();

		debug = new DebugInterface();
		debug.setSize(500,500);
		
		cars = new ArrayList<CarGraphic>();

		wall.setSize(800, 600);
		wall.setLocationRelativeTo( null ); 

		wall.setVisible(true);

	}

	public DebugInterface getDebug(){
		return this.debug;
	}
	
	public void print(String s){
		debug.print(s);
		debug.setVisible(true);
	}

	public void print(String s, Color c){
		debug.print(s, c);
		debug.setVisible(true);
	}

	
	public void setDeadCars() {

		final int constant = 25;
		final int numCar = 6;
		ArrayList<DeadCar> c = new ArrayList<DeadCar>();

		for(int i = 0; i < numCar; i ++){
			c.add(new DeadCar());
			c.get(c.size()-1).setBounds(590, 510-(constant*i), 176, 88);
			wall.add(c.get(c.size()-1), 1);
		}

		for(int i = 0; i < numCar; i ++){
			c.add(new DeadCar());
			c.get(c.size()-1).setBounds(630, 510-(constant*i), 176, 88);
			wall.add(c.get(c.size()-1), 1);
		}

		for(int i = 0; i < numCar; i ++){
			c.add(new DeadCar());
			c.get(c.size()-1).setBounds(670, 510-(constant*i), 176, 88);
			wall.add(c.get(c.size()-1), 1);
			
			
		}

		
	}


	public void addStation(){

		station = new StationGraphic();

		wall.add(station, 0);
		station.setVisible(true);
		wall.repaint();

	}

	public void addCar(Car newCar){


		cars.add(newCar.getMyCarGraphic());

		/*
		 * aggiungo la macchinina
		 */
		wall.add(cars.get(cars.size()-1).getCar(), 1);
		cars.get(cars.size()-1).getCar().setVisible(true);

//		wall.repaint();
		//TODO

		/*
		 * aggiungo il display della macchinina
		 */
		wall.add(cars.get(cars.size()-1).getDisplay(), 1);
		cars.get(cars.size()-1).getDisplay().setVisible(true);
		wall.repaint();


		Thread t;
		t = new Thread(newCar);
		t.start();

	}

}
