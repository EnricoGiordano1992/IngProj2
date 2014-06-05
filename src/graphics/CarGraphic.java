package graphics;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CarGraphic implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int ID;
	int xPos;
	int yPos;
	final int init_xPos = 720;
	final int init_yPos = 235;

	final int dimX = 120;
	final int dimY = 20;

	JLabel car;
	JLabel display;

	public CarGraphic(int ID){

		car= new JLabel(newCarImage("car.png"));

		this.ID = ID;

		xPos = init_xPos;
		yPos = init_yPos;

		car.setBounds(xPos, yPos, 176, 88);

		display = new JLabel();

		display.setOpaque(true);

		Color c = new Color(new Random().nextFloat(),new Random().nextFloat(),new Random().nextFloat());

		display.setBackground(c);
		display.setForeground(Color.WHITE);
		display.setText("ID:" + ID);
		display.setBorder(BorderFactory.createEtchedBorder());

		display.setBounds(xPos, yPos, dimX, dimY);
	}

	public JLabel getCar(){
		return car;
	}

	public JLabel getDisplay(){
		return display;
	}

	public void setDisplay(String s){
		display.setText(s);
	}

	private static ImageIcon newCarImage(String name){

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(name));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image dimg = img.getScaledInstance(50, 30,
				Image.SCALE_SMOOTH);

		ImageIcon imageIcon = new ImageIcon(dimg);

		return imageIcon;
	}

	public void run(){
		move();
	}

	public void move(){

		final int sleep = 10;
		final int sleep_curve = 10;
		boolean restart = true;

		/*
		 * entra nella pista
		 * 
		 */
		
		while(xPos >= 630)
		{
			car.setBounds(xPos--, yPos, 176, 88);
			display.setBounds(xPos--, yPos, dimX, dimY);

			try{
				Thread.sleep(sleep+50);
			}catch(Exception e){}

		}
		
		/*
		 * entra nel parcheggio?
		 */
		if(new Random().nextBoolean()){
			
			while(yPos <= (375 + new Random().nextInt(300))){
				car.setBounds(xPos, yPos+=2, 176, 88);
				display.setBounds(xPos, yPos+=2, dimX, dimY);
				try{
					Thread.sleep(sleep+sleep_curve);
				}catch(Exception e){}

			}

			try{
				Thread.sleep(5000);
			}catch(Exception e){}

			while(new Random().nextBoolean())
				try{
					Thread.sleep(5000);
				}catch(Exception e){}

			while(yPos >= init_yPos){
				car.setBounds(xPos, yPos-=2, 176, 88);
				display.setBounds(xPos, yPos-=2, dimX, dimY);
				try{
					Thread.sleep(sleep+sleep_curve);
				}catch(Exception e){}
			}
				xPos = 636;
				yPos = init_yPos;
				car.setBounds(xPos, yPos, 176, 88);
				display.setBounds(xPos, yPos, dimX, dimY);


		}

		
		/*
		 * comincia a girare
		 * 
		 */
		while(restart)
		{

			while(xPos >= 590)
			{
				car.setBounds(xPos--, yPos-= 2, 176, 88);
				display.setBounds(xPos--, yPos-=2, dimX, dimY);

				try{
					Thread.sleep(sleep+sleep_curve);
				}catch(Exception e){}

			}

			while(xPos >= 410){
				if(xPos % 2 == 0)
					yPos--;
				car.setBounds(xPos--, yPos, 176, 88);
				display.setBounds(xPos--, yPos, dimX, dimY);

				try{
					Thread.sleep(sleep);
				}catch(Exception e){}

			}					
			while(xPos >= 150)
			{
				car.setBounds(xPos--, yPos, 176, 88);
				display.setBounds(xPos--, yPos, dimX, dimY);

				try{
					Thread.sleep(sleep);
				}catch(Exception e){}
			}

			while(xPos >= 10){
				if(xPos % 2 == 0)
					yPos++;
				car.setBounds(xPos--, yPos, 176, 88);
				display.setBounds(xPos--, yPos, dimX, dimY);

				try{
					Thread.sleep(sleep);
				}catch(Exception e){}

			}
			while(xPos >= -25)
			{
				car.setBounds(xPos--, yPos+= 2, 176, 88);
				display.setBounds(xPos--, yPos+=2, dimX, dimY);

				try{
					Thread.sleep(sleep+sleep_curve);
				}catch(Exception e){}

			}

			/*
			 * 
			 * ora torna indietro
			 */
			car.setIcon(newCarImage("car2.png"));
			while(xPos <= 30)
			{
				car.setBounds(xPos++, yPos+= 2, 176, 88);
				display.setBounds(xPos++, yPos+=2, dimX, dimY);

				try{
					Thread.sleep(sleep+sleep_curve);
				}catch(Exception e){}

			}


			while(xPos <= 180){
				if(xPos % 2 == 0)
					yPos++;
				car.setBounds(xPos++, yPos, 176, 88);
				display.setBounds(xPos++, yPos, dimX, dimY);

				try{
					Thread.sleep(sleep);
				}catch(Exception e){}


			}

			while(xPos <= 400 )
			{
				car.setBounds(xPos++, yPos, 176, 88);
				display.setBounds(xPos++, yPos, dimX, dimY);

				try{
					Thread.sleep(sleep);
				}catch(Exception e){}

			}


			while(xPos <= 580 )
			{
				if(xPos % 2 == 0)
					yPos--;
				car.setBounds(xPos++, yPos, 176, 88);
				display.setBounds(xPos++, yPos, dimX, dimY);

				try{
					Thread.sleep(sleep);
				}catch(Exception e){}

			}

			while(xPos <= 600)
			{
				car.setBounds(xPos++, yPos-= 2, 176, 88);
				display.setBounds(xPos++, yPos-=2, dimX, dimY);
				try{
					Thread.sleep(sleep+sleep_curve);
				}catch(Exception e){}

			}


			/*
			 * decido a random se deve uscire dal circuito o no
			 */

			if(new Random().nextBoolean())
			{
				/*
				 * esce dal circuito
				 */
				xPos = 636;
				yPos = 260;
				car.setBounds(xPos, yPos, 176, 88);
				display.setBounds(xPos, yPos, dimX, dimY);

				while(xPos < 720)
				{
					car.setBounds(xPos++, yPos, 176, 88);
					display.setBounds(xPos++, yPos, dimX, dimY);
					try{
						Thread.sleep(sleep);
					}catch(Exception e){}
					car.repaint();
				}
				restart = false;
				//				this.car.setIcon(null);
				//				car.setVisible(false);
				//				display.setVisible(false);
				display.setBounds(0,0,0,0);
				car.setBounds(0,0,0,0);
				//				display.setBounds(1000, 1000, 0, 0);
				//				car.repaint();

			}

			else
			{

				while(xPos <= 610)
				{
					car.setBounds(xPos++, yPos-= 2, 176, 88);
					display.setBounds(xPos++, yPos-=2, dimX, dimY);
					try{
						Thread.sleep(sleep+sleep_curve);
					}catch(Exception e){}

				}


				xPos = 636;
				yPos = init_yPos;
				car.setBounds(xPos, yPos, 176, 88);
				display.setBounds(xPos, yPos, dimX, dimY);

				car.setIcon(newCarImage("car.png"));

				car.repaint();
			}
		}

	}
}
