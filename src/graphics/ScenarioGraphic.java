package graphics;

import java.util.ArrayList;
import java.awt.*;
import java.awt.image.ImageProducer;
import java.awt.*;

public class ScenarioGraphic {

	WallGraphic wall;
	ArrayList<CarGraphic> cars;
	StationGraphic station;
	
	public ScenarioGraphic(){
		
		wall = new WallGraphic();
		
		cars = new ArrayList<CarGraphic>();
		
		wall.setSize(800, 600);
		wall.setLocationRelativeTo( null ); 
		
		
		wall.setVisible(true); 

		station = new StationGraphic();
		
		wall.add(station);
		station.setVisible(true);
		wall.setComponentZOrder(station, 0);

	}
	
	public void addCar(){
		
		cars.add(new CarGraphic(cars.size()));
		wall.add(cars.get(cars.size()-1));
		cars.get(cars.size()-1).setVisible(true);
		wall.setComponentZOrder(cars.get(cars.size()-1), 0);
		//wall.repaint();
		
		Thread t;
		t = new Thread(cars.get(cars.size()-1));
		t.start();
	}
	
}
