package start;

import java.util.Random;

import javax.swing.JFrame;

import comunication.Net;

import station.Station;
import graphics.*;

public class Simulator extends JFrame{


	public static void main(String [] args) 
	{
		ScenarioGraphic g = new ScenarioGraphic();
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
		station.run();
		/**
		 * Disegno la stazione
		 */
		g.addStation();
		
		while(true){
			g.addCar();

			try{
				Thread.sleep(new Random().nextInt(3000));
			}catch(Exception p){}
		}
	} 
}
