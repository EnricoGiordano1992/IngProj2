package car;
import car.*;

public class AdapterAutoToManual extends ManCar{

	AutoCar a_car = null;
	
	public AdapterAutoToManual(AutoCar a_car){
		
		this.a_car = a_car;
		this.setP_rate(5);
		
	}
	
	//poi override di tutti i metodi come se non ci fosse un domani!
	//tipo return a_car.suometodo()
	
	public void setBreakAdapter(boolean val){
		
		a_car.setBreak_signal(val);
	}
	
}
