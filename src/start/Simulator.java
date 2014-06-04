package start;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JFrame;

import graphics.*;

public class Simulator extends JFrame{


	public static void main(String [] args) 
	{
		ScenarioGraphic g = new ScenarioGraphic();

		g.addStation();
		
		while(true){
			g.addCar();

			try{
				Thread.sleep(new Random().nextInt(3000));
			}catch(Exception p){}
		}
	} 
}
