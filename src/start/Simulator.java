package start;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JFrame;

import graphics.*;

public class Simulator extends JFrame{


	public static void main(String [] args) 
	{
		ScenarioGraphic g = new ScenarioGraphic();

		while(true){
			g.addCar();

			try{
				Thread.sleep(1000);
			}catch(Exception p){}
		}
	} 
}
