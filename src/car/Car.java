package car;

import java.util.Random;

import comunication.Comunication;
import comunication.Message;
import comunication.Net;
import graphics.CarGraphic;

public class Car implements Runnable{

	private int speed_meter;
	private String display;
	private int p_rate;
	private Comunication com;
	private int ID;
	private CarGraphic myGraphic;
	private int netId;
	private boolean roadFree;
	
	int xPos;
	int yPos;
	final int init_xPos = 720;
	final int init_yPos = 235;

	final int dimX = 120;
	final int dimY = 20;

	public Car(int p_rate){
		
		this.p_rate = p_rate;
		speed_meter = 0;
		display = "";
		roadFree = false;
		myGraphic = new CarGraphic(ID);
	}
	public Car(int p_rate, Net net, int tempID){
		
		com = new Comunication(p_rate, this, net);
		com.setId(tempID);
		ID = tempID;
		net.joinBroadcast(com);
		this.p_rate = p_rate;
		speed_meter = 0;
		display = "";
		roadFree = false;
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
		Message mex = com.readMessages();
	
		if( mex == null )
		{
			System.out.println("NON ci sono messaggi");
			return;
		}
		System.out.println("Data: " + mex.getData());
		if ( mex.getData().compareTo("JOIN") == 0)
		{
			com.write(mex.getFrom(), "OK;" + p_rate);
			com.send();
		}			
		else if ( mex.getData().compareTo("BUSY") == 0)
		{
			roadFree = false;
			System.out.println("JOIN NON ESEGUITO");
		}
		else if( mex.getData().compareTo("OK-JOIN") == 0 )
		{
			com.join();
			this.setID(com.getId());
			roadFree = true;
			System.out.println("[ " + this.ID + " ] JOIN ESEGUITO");
		}
		else
		{
			String[] s = mex.getData().split(";");
		}
	}
	@Override
	public void run() {
		move();
		com.leave();
	}
	
	public void move(){

		final int sleep = 10;
		final int sleep_curve = 10;
		boolean restart = true;

		/*
		 * entra nella pista
		 */
		xPos = myGraphic.getXPos();
		yPos = myGraphic.getYPos();
		
		update();
		while(xPos >= 630)
		{
			myGraphic.getCar().setBounds(xPos--, yPos, 176, 88);
			myGraphic.getDisplay().setBounds(xPos--, yPos, dimX, dimY);
			System.out.println("Chiamo update");
			
			try{
				Thread.sleep(sleep+50);
			}catch(Exception e){}

		}
		/**
		 * Setto l'ID della macchina da visualizzare sul display
		 */
		setDisplay("" + com.getId());
		/*
		 * entra nel parcheggio?
		 */
		System.out.println("["+ ID +"]" + "Controllo rete: " + roadFree);
		if( ! roadFree ){
			System.out.println("["+ ID +"]" + "Entro nel parcheggio..");
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

			/**
			 * Aspetta di ricevere un messaggio di JOIN da parte della stazione 
			 * per poter entrare in strada( quando � in questo stato pu� ricevere il messaggio?
			 * Si pu� chiamare il metodo update() su un oggetto in questo stato o fallisce? )
			 */
			while( !roadFree )
			{
				try{
					Thread.sleep(5000);
				}catch(Exception e){}
				System.out.println("Controllo se posso entrare");
				update();
			}

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
