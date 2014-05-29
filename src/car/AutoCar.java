package car;

public class AutoCar extends Car {

	private boolean break_signal;
	
	public AutoCar(){

		super(10);
		break_signal = false;
	}
	
	public void setBreak_signal(boolean value){
		
		break_signal = value;
	}
	
}
