package car;

import java.util.Random;

import comunication.Comunication;
import graphics.CarGraphic;

public class Car implements Runnable{

	private int speed_meter;
	private String display;
	private int p_rate;
	private Comunication com;
	private int ID;
	private CarGraphic myGraphic;
	
	int xPos;
	int yPos;
	final int init_xPos = 720;
	final int init_yPos = 235;

	final int dimX = 120;
	final int dimY = 20;

	
	public Car(int p_rate){
		
		//ID in the comunication scenario
		//at the first time, hasn't any ID
		ID = 0;
		p_rate = 0;
		speed_meter = 0;
		display = "";
		myGraphic = new CarGraphic(ID);
	}

	
	
	public void setID(int ID){
		this.ID = ID;
	}
	
	public CarGraphic getMyCarGraphic(){
		return this.myGraphic;
	}
	
	public int getSpeed_meter() {
		return speed_meter;
	}

	public void setSpeed_meter(int speed_meter) {
		this.speed_meter = speed_meter;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
		this.getMyCarGraphic().setDisplay(display);
	}

	public void setP_rate(int val){
		
		p_rate = val;
	}
	
	
	public void sendMessage(){
		
	}
	
	public void receiveMessage(){
		
	}
	public void update(){
		
	}
	
	@Override
	public void run() {

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
		xPos = myGraphic.getXPos();
		yPos = myGraphic.getYPos();
		
		
		while(xPos >= 630)
		{
			myGraphic.getCar().setBounds(xPos--, yPos, 176, 88);
			myGraphic.getDisplay().setBounds(xPos--, yPos, dimX, dimY);

			try{
				Thread.sleep(sleep+50);
			}catch(Exception e){}

		}
		
		/*
		 * entra nel parcheggio?
		 */
		if(new Random().nextBoolean()){
			
			while(yPos <= (375 + new Random().nextInt(300))){
				myGraphic.getCar().setBounds(xPos, yPos+=2, 176, 88);
				myGraphic.getDisplay().setBounds(xPos, yPos+=2, dimX, dimY);
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
				myGraphic.getCar().setBounds(xPos, yPos-=2, 176, 88);
				myGraphic.getDisplay().setBounds(xPos, yPos-=2, dimX, dimY);
				try{
					Thread.sleep(sleep+sleep_curve);
				}catch(Exception e){}
			}
				xPos = 636;
				yPos = init_yPos;
				myGraphic.getCar().setBounds(xPos, yPos, 176, 88);
				myGraphic.getDisplay().setBounds(xPos, yPos, dimX, dimY);


		}

		
		/*
		 * comincia a girare
		 * 
		 */
		while(restart)
		{

			while(xPos >= 590)
			{
				myGraphic.getCar().setBounds(xPos--, yPos-= 2, 176, 88);
				myGraphic.getDisplay().setBounds(xPos--, yPos-=2, dimX, dimY);

				try{
					Thread.sleep(sleep+sleep_curve);
				}catch(Exception e){}

			}

			while(xPos >= 410){
				if(xPos % 2 == 0)
					yPos--;
				myGraphic.getCar().setBounds(xPos--, yPos, 176, 88);
				myGraphic.getDisplay().setBounds(xPos--, yPos, dimX, dimY);

				try{
					Thread.sleep(sleep);
				}catch(Exception e){}

			}					
			while(xPos >= 150)
			{
				myGraphic.getCar().setBounds(xPos--, yPos, 176, 88);
				myGraphic.getDisplay().setBounds(xPos--, yPos, dimX, dimY);

				try{
					Thread.sleep(sleep);
				}catch(Exception e){}
			}

			while(xPos >= 10){
				if(xPos % 2 == 0)
					yPos++;
				myGraphic.getCar().setBounds(xPos--, yPos, 176, 88);
				myGraphic.getDisplay().setBounds(xPos--, yPos, dimX, dimY);

				try{
					Thread.sleep(sleep);
				}catch(Exception e){}

			}
			while(xPos >= -25)
			{
				myGraphic.getCar().setBounds(xPos--, yPos+= 2, 176, 88);
				myGraphic.getDisplay().setBounds(xPos--, yPos+=2, dimX, dimY);

				try{
					Thread.sleep(sleep+sleep_curve);
				}catch(Exception e){}

			}

			/*
			 * 
			 * ora torna indietro
			 */
			myGraphic.getCar().setIcon(CarGraphic.newCarImage("car2.png"));
			while(xPos <= 30)
			{
				myGraphic.getCar().setBounds(xPos++, yPos+= 2, 176, 88);
				myGraphic.getDisplay().setBounds(xPos++, yPos+=2, dimX, dimY);

				try{
					Thread.sleep(sleep+sleep_curve);
				}catch(Exception e){}

			}


			while(xPos <= 180){
				if(xPos % 2 == 0)
					yPos++;
				myGraphic.getCar().setBounds(xPos++, yPos, 176, 88);
				myGraphic.getDisplay().setBounds(xPos++, yPos, dimX, dimY);

				try{
					Thread.sleep(sleep);
				}catch(Exception e){}


			}

			while(xPos <= 400 )
			{
				myGraphic.getCar().setBounds(xPos++, yPos, 176, 88);
				myGraphic.getDisplay().setBounds(xPos++, yPos, dimX, dimY);

				try{
					Thread.sleep(sleep);
				}catch(Exception e){}

			}


			while(xPos <= 580 )
			{
				if(xPos % 2 == 0)
					yPos--;
				myGraphic.getCar().setBounds(xPos++, yPos, 176, 88);
				myGraphic.getDisplay().setBounds(xPos++, yPos, dimX, dimY);

				try{
					Thread.sleep(sleep);
				}catch(Exception e){}

			}

			while(xPos <= 600)
			{
				myGraphic.getCar().setBounds(xPos++, yPos-= 2, 176, 88);
				myGraphic.getDisplay().setBounds(xPos++, yPos-=2, dimX, dimY);
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
				myGraphic.getCar().setBounds(xPos, yPos, 176, 88);
				myGraphic.getDisplay().setBounds(xPos, yPos, dimX, dimY);

				while(xPos < 720)
				{
					myGraphic.getCar().setBounds(xPos++, yPos, 176, 88);
					myGraphic.getDisplay().setBounds(xPos++, yPos, dimX, dimY);
					try{
						Thread.sleep(sleep);
					}catch(Exception e){}
					myGraphic.getCar().repaint();
				}
				restart = false;
				//				this.car.setIcon(null);
				//				car.setVisible(false);
				//				display.setVisible(false);
				myGraphic.getDisplay().setBounds(0,0,0,0);
				myGraphic.getCar().setBounds(0,0,0,0);
				//				myGraphic.getDisplay().setBounds(1000, 1000, 0, 0);
				//				car.repaint();

			}

			else
			{

				while(xPos <= 610)
				{
					myGraphic.getCar().setBounds(xPos++, yPos-= 2, 176, 88);
					myGraphic.getDisplay().setBounds(xPos++, yPos-=2, dimX, dimY);
					try{
						Thread.sleep(sleep+sleep_curve);
					}catch(Exception e){}

				}


				xPos = 636;
				yPos = init_yPos;
				myGraphic.getCar().setBounds(xPos, yPos, 176, 88);
				myGraphic.getDisplay().setBounds(xPos, yPos, dimX, dimY);

				myGraphic.getCar().setIcon(CarGraphic.newCarImage("car.png"));

				myGraphic.getCar().repaint();
			}
		}

	}

	
}
