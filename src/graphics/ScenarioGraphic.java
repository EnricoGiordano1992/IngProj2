package graphics;

import java.util.ArrayList;

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
		

	}
	
	
	public void addStation(){
		
		station = new StationGraphic();
		
		wall.add(station, 0);
		station.setVisible(true);
		wall.repaint();
		
	}
	
	public void addCar(){
		
		cars.add(new CarGraphic(cars.size()));
		wall.add(cars.get(cars.size()-1), 1);
		cars.get(cars.size()-1).setVisible(true);
		wall.repaint();
		
		Thread t;
		t = new Thread(cars.get(cars.size()-1));
		t.start();
	}
	
}
