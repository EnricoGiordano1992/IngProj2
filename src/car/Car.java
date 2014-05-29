package car;
import node.*;

public class Car extends Node{

	private int speed_meter;
	private String display;
	private boolean break_signal;
	
	public Car(int p_rate){
		super(p_rate);
		speed_meter = 0;
		display = "";
		break_signal = false;
	}
}
