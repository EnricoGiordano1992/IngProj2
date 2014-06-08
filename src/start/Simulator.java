package start;

import java.util.Random;

import javax.swing.JFrame;

import car.Car;
import comunication.Net;
import station.Station;
import graphics.*;

public class Simulator extends JFrame{


	
	
	public static void main(String [] args) 
	{
		Thread t;
		ScenarioGraphic g = new ScenarioGraphic();
		int i = 1;
		
		try{
		Thread.sleep(500);
		}catch(Exception e){}
		
		/**
		 * Creo la rete
		 */
		Net net = new Net(50, 1, g);
		/**
		 * Creo la stazione
		 */
		Station station = new Station(20, net, g);
		/**
		 * Avvio la stazione
		 */
		t = new Thread(station);
		t.start();
		/**
		 * Disegno la stazione
		 */
		g.addStation();

		g.setDeadCars();

		
		
		while(true){
			g.addCar(new Car(10, net, i++, g));

			try{
				Thread.sleep(new Random().nextInt(3000));
			}catch(Exception p){}
		}
	} 
}
