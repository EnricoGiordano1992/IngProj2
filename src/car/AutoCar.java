package car;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import javax.swing.SwingUtilities;

import graphics.CarGraphic;
import graphics.ScenarioGraphic;
import comunication.ComAutoCar;
import comunication.Comunication;
import comunication.Message;
import comunication.Net;

public class AutoCar implements InterfaceAutoCar,Runnable {

	private String display;
	private int p_rate;
	private Comunication com;
	protected int ID;
	private CarGraphic myGraphic;
	private boolean connected = false;
	private boolean roadFree;
	protected ScenarioGraphic g;

	int xPos;
	int yPos;
	final int init_xPos = 720;
	final int init_yPos = 235;

	Color myColor;

	final int dimX = 120;
	final int dimY = 20;

	final int sleep = 20;
	final int sleep_curve = 10;


	Random rand = new Random();
	int velocity;

	final int conversion = 50;

	public AutoCar(int p_rate, Net net, int tempID, ScenarioGraphic g){

		this.g = g;
		com = new ComAutoCar(p_rate, this, net);
		com.setId(tempID);
		ID = tempID;
		this.p_rate = p_rate;
		display = "";
		roadFree = false;
		myColor = new Color(new Random().nextFloat(),new Random().nextFloat(),new Random().nextFloat());
		myGraphic = new CarGraphic(ID, myColor,"A");
		net.joinBroadcast(com);
	}
	public AutoCar(){}
	
	public CarGraphic getMyCarGraphic(){
		return this.myGraphic;
	}
	public String getDisplay() {
		return display;
	}
	private void updateDisplay( String display )
	{
		this.display = display;
//		this.myGraphic.setDisplay("[ " + ID + " - A ] " + display);
		g.print("[ " + ID + " - A ] " + display , myColor);
	}
	private void sendMessage(){
		com.send();
	}
	public void doBreak() {
		velocity-=5;
	}
	public void update(){
		Message mex = com.readMessages();

		if( mex == null )
			return;

		if ( mex.getData().compareTo("JOIN") == 0 && !connected )
		{
			com.write(mex.getFrom(), "OK;" + p_rate);
			com.send();
		}			
		else if ( mex.getData().compareTo("BUSY") == 0 && !connected )
		{
			roadFree = false;
			updateDisplay("JOIN NOT EXECUTED");
		}
		else if( mex.getData().compareTo("OK-JOIN") == 0 && !connected )
		{
			connected = com.join();
			roadFree = true;
			updateDisplay("JOIN SUCCESSFULLY EXECUTED");
		}
		else if ( mex.getData().compareTo("DECREASE THE SPEED") == 0 )
			doBreak();
		else if( mex.getData().compareTo("IDEAL") == 0)
			updateDisplay("IDEAL");
		
		if( connected )
			roadFree = true;
	}
	@Override
	public void run() {
		move();
		leaveComunication();
	}
	
	@Override
	public void leaveComunication() {
		com.leave();		
	}

	

	public void move(){

		boolean restart = true;

		/*
		 * enter into the circuit
		 */
		xPos = myGraphic.getXPos();
		yPos = myGraphic.getYPos() + new Random().nextInt(5);

		while(xPos >= 630)
		{

			myGraphic.getCar().setBounds(xPos--, yPos, 176, 88);
			myGraphic.getDisplay().setBounds(xPos--, yPos, dimX, dimY);			
			try{
				Thread.sleep(sleep+30);
			}catch(Exception e){}

		}
		/*
		 * do I enter into the park? station is busy?
		 */
		if( ! roadFree ){
			updateDisplay("Enter into the park...");
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
			 * waiting for join message 
			 */
			while( !roadFree )
			{
				try{
					Thread.sleep(100);
				}catch(Exception e){}
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
		 * let's go!
		 * 
		 */
		while(restart)
		{

			setNewVelocity();

			while(xPos >= 590)
			{

				myGraphic.getCar().setBounds(xPos--, yPos-= 2, 176, 88);
				myGraphic.getDisplay().setBounds(xPos--, yPos-=2, dimX, dimY);

				setMoving(true);


			}

			setNewVelocity();

			while(xPos >= 410){
				if(xPos % 2 == 0)
					yPos--;
				myGraphic.getCar().setBounds(xPos--, yPos, 176, 88);
				myGraphic.getDisplay().setBounds(xPos--, yPos, dimX, dimY);

				setMoving(false);

			}

			setNewVelocity();

			while(xPos >= 150)
			{
				myGraphic.getCar().setBounds(xPos--, yPos, 176, 88);
				myGraphic.getDisplay().setBounds(xPos--, yPos, dimX, dimY);

				setMoving(false);
			}

			setNewVelocity();

			while(xPos >= 10){
				if(xPos % 2 == 0)
					yPos++;
				myGraphic.getCar().setBounds(xPos--, yPos, 176, 88);
				myGraphic.getDisplay().setBounds(xPos--, yPos, dimX, dimY);

				setMoving(false);

			}

			setNewVelocity();

			while(xPos >= -25)
			{
				myGraphic.getCar().setBounds(xPos--, yPos+= 2, 176, 88);
				myGraphic.getDisplay().setBounds(xPos--, yPos+=2, dimX, dimY);

				setMoving(true);

			}

			/*
			 * go back!
			 */
			myGraphic.getCar().setIcon(CarGraphic.newCarImage("car2.png"));

			setNewVelocity();

			while(xPos <= 30)
			{
				myGraphic.getCar().setBounds(xPos++, yPos+= 2, 176, 88);
				myGraphic.getDisplay().setBounds(xPos++, yPos+=2, dimX, dimY);

				setMoving(true);

			}

			setNewVelocity();

			while(xPos <= 180){
				if(xPos % 2 == 0)
					yPos++;
				myGraphic.getCar().setBounds(xPos++, yPos, 176, 88);
				myGraphic.getDisplay().setBounds(xPos++, yPos, dimX, dimY);

				setMoving(false);

			}

			setNewVelocity();

			while(xPos <= 400 )
			{
				myGraphic.getCar().setBounds(xPos++, yPos, 176, 88);
				myGraphic.getDisplay().setBounds(xPos++, yPos, dimX, dimY);

				setMoving(false);

			}

			setNewVelocity();

			while(xPos <= 580 )
			{
				if(xPos % 2 == 0)
					yPos--;
				myGraphic.getCar().setBounds(xPos++, yPos, 176, 88);
				myGraphic.getDisplay().setBounds(xPos++, yPos, dimX, dimY);

				setMoving(false);

			}

			setNewVelocity();

			while(xPos <= 600)
			{
				myGraphic.getCar().setBounds(xPos++, yPos-= 2, 176, 88);
				myGraphic.getDisplay().setBounds(xPos++, yPos-=2, dimX, dimY);

				setMoving(true);

			}


			/*
			 * random decision: I will exit?
			 */

			if(new Random().nextBoolean())
			{
				/*
				 * yes, now I'm exiting from the circuit
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
				myGraphic.getDisplay().setBounds(0,0,0,0);
				myGraphic.getCar().setBounds(0,0,0,0);

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

	private void setNewVelocity(){

		velocity = rand.nextInt(20);
		updateDisplay( "Vel : " + (velocity+conversion));
	}

	private void setMoving(boolean isCurve){

		if(isCurve)
		{
			try{

				Thread.sleep(sleep-velocity+sleep_curve);
				/*
				 * la macchina invia questo:
				 */
				com.write(0, "SPEED;"+velocity+conversion);

			}catch(Exception e){}
		}

		else{

			try{

				Thread.sleep(sleep-velocity);
				/*
				 * la macchina invia questo:
				 */
				com.write(0, "SPEED;"+velocity+conversion);
			}catch(Exception e){}
		}
//   	 synchronized (this) {
//		  try {
//			SwingUtilities.invokeAndWait(doNextGen);
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
		sendMessage();
	}
//	
//	final Runnable doNextGen = new Runnable() {
//	     public void run() {
//	    	  myGraphic.getCar().repaint();
//	     }
//	 };
	 
	public int getID() {
		return ID;
	}

	public int getP_rate() {
		return p_rate;
	}
}
