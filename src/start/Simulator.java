package start;

import java.util.Random;

import javax.swing.JFrame;

import car.AdapterAutoToManual;
import car.AutoCar;
import car.ManCar;
import comunication.Net;
import station.Station;
import graphics.*;

public class Simulator extends JFrame{


	static ScenarioGraphic g;

	public static void main(String [] args) 
	{
		Thread t;
		int contAuto = 0;

		g = new ScenarioGraphic();             

		try{
			Thread.sleep(1000);
		}catch(Exception e){}

		g.print("SYSTEM: added scenario");

		/**
		 * Creo la rete
		 */
		Net net = new Net(100, 5);
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

			for( int i = 1 ; i <= 90 ; i++ )
			{
				if ( contAuto <= 40 )
					{
						g.addCar( new AutoCar(10, net, i, g));
						contAuto++;
					}
				else
					g.addCar(new AdapterAutoToManual(new ManCar(5, net, i, g)));
					
					try{
						Thread.sleep(new Random().nextInt(2000));
					}catch(Exception p){}
			}
	} 
}
