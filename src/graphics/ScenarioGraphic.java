package graphics;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

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


	public void addStation(){

		station = new StationGraphic();

		wall.add(station, 0);
		station.setVisible(true);
		wall.repaint();

	}

	public void addCar(){


		cars.add(new CarGraphic(cars.size()));

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
		t = new Thread(cars.get(cars.size()-1));
		t.start();

	}

}
