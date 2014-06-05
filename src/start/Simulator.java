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
		
		try{
		Thread.sleep(500);
		}catch(Exception e){}
		/**
		 * Creo la rete
		 */
		Net net = new Net(100, 5);
		/**
		 * Creo la stazione
		 */
		Station station = new Station(20, net);
		/**
		 * Agginungo alla rete la stazione creata
		 */
		net.setStation(station);
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
			g.addCar(new Car(10));

			try{
				Thread.sleep(new Random().nextInt(3000));
			}catch(Exception p){}
		}
	} 
}
