package graphics;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import car.Car;

public class ScenarioGraphic {

	WallGraphic wall;
	ArrayList<CarGraphic> cars;
	ArrayList<JLabel> display;

	StationGraphic station;

	public ScenarioGraphic(){

		wall = new WallGraphic();

		cars = new ArrayList<CarGraphic>();

		wall.setSize(800, 600);
		wall.setLocationRelativeTo( null ); 

		wall.setVisible(true);

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

		wall.repaint();

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
