package car;

import comunication.Comunication;

public class Car {

	private int speed_meter;
	private String display;
	private int p_rate;
	private Comunication com;
	
	public Car(int p_rate){
		p_rate = 0;
		speed_meter = 0;
		display = "";
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
