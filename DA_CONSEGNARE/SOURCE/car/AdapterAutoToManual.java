package car;

import graphics.CarGraphic;
import graphics.ScenarioGraphic;
import comunication.Net;

public class AdapterAutoToManual extends AutoCar implements InterfaceAutoCar,InterfaceManCar{

	ManCar mancar = null;
	
	public AdapterAutoToManual(int p_rate, Net net, int tempID,
			ScenarioGraphic g) {
		super(p_rate, net, tempID, g);
	}
	
	public AdapterAutoToManual( ManCar car )
	{
		this.mancar = car;
	}
	
	@Override
	public void doBreak()
	{
		mancar.doBreak();
	}
	
	@Override
	public CarGraphic getMyCarGraphic()
	{
		return mancar.getMyCarGraphic();
	}
	
	@Override
	public String getDisplay()
	{
		return mancar.getDisplay();
	}
	
	@Override
	public void update()
	{
		mancar.update();
	}
	
	@Override
	public int getID()
	{
		return mancar.getID();
	}
	
	@Override
	public int getP_rate()
	{
		return mancar.getP_rate();
	}
	
	@Override
	public void move()
	{
		mancar.move();
	}
	
	@Override
	public void leaveComunication(){
		mancar.leaveComunication();
	}
}
