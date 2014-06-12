package start;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import car.AdapterAutoToManual;
import car.AutoCar;
import car.ManCar;
import comunication.Net;
import station.Station;
import graphics.*;

public class Simulator extends JFrame{


	static ScenarioGraphic g;

	static int delay;


	public static void main(String [] args) throws IOException 
	{
		int contAuto = 0;
		String s;
		FileReader f;

		f=new FileReader("delay.txt");
		BufferedReader b = new BufferedReader(f);
		s=b.readLine();
		delay=Integer.parseInt(s);


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
		Thread t1 = new Thread(station);
		t1.start();
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
			if ( contAuto < 40 )
			{
				g.addCar( new AutoCar(10, net, i, g));
				contAuto++;
			}
			else
				g.addCar(new AdapterAutoToManual(new ManCar(5, net, i, g)));

			try{
				Thread.sleep(new Random().nextInt(delay));
			}catch(Exception p){}
		}
	} 
}
