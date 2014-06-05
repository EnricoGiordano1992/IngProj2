package car;

import comunication.Comunication;
import graphics.CarGraphic;

public class Car {

	private int speed_meter;
	private String display;
	private int p_rate;
	private Comunication com;
	private int ID;
	private CarGraphic myGraphic;
	
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
	
}
