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
		
        EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {                
            	g = new ScenarioGraphic();             
            }
        });

		try{
<<<<<<< HEAD
			Thread.sleep(500);
=======
		Thread.sleep(1000);
>>>>>>> branch 'master' of https://github.com/EnricoGiordano1992/IngProj2.git
		}catch(Exception e){}
		
		g.print("SYSTEM: added scenario");
		
		/**
		 * Creo la rete
		 */
<<<<<<< HEAD
		Net net = new Net(50, 2, g);
=======
		Net net = new Net(100, 5, g);
>>>>>>> branch 'master' of https://github.com/EnricoGiordano1992/IngProj2.git
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
		g.print("SYSTEM: added useless cars");

		
		while(true){
			g.addCar(new Car(10, net, i++, g));

			try{
				Thread.sleep(new Random().nextInt(3000));
			}catch(Exception p){}
		}
	} 
}
