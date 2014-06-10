package start;

import java.awt.EventQueue;
import java.util.Random;

import javax.swing.JFrame;

import car.Car;
import comunication.Net;
import station.Station;
import graphics.*;

public class Simulator extends JFrame{


	static ScenarioGraphic g;

	public static void main(String [] args) 
	{
		Thread t;

		int i = 1;

		g = new ScenarioGraphic();             

		try{
			Thread.sleep(1000);
		}catch(Exception e){}

		g.print("SYSTEM: added scenario");

		/**
		 * Creo la rete
		 */
		Net net = new Net(100, 5, g);
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

		try{
			Thread.sleep(1000);
		}catch(Exception e){}
		g.print("SYSTEM: added station");

		g.setDeadCars();

		try{
			Thread.sleep(1000);
		}catch(Exception e){}
		g.print("SYSTEM: added useless cars in the park");

int j = 500;
		while(j-- > -1){
			g.addCar(new Car(10, net, i++, g));

			try{
				Thread.sleep(new Random().nextInt(2000));
			}catch(Exception p){}
		}
	} 
}
