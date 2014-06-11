package comunication;

import java.util.Vector;
import car.ManCar;

public class ComManCar extends Comunication {

	private ManCar car;
	
	public ComManCar( int pps, ManCar autoCar, Net net )
	{
		this.pps = pps;
		this.car = autoCar;
		this.net = net;
		dataReceived = new Vector<Message>();
		observers = new Vector<Comunication>();
	}
	/**
	 * Controlla se nel pacchetto � presente un messaggio per questa macchina
	 * @param p
	 */
	public void receive( Packet p )
	{
		// ricevo i messaggi col mio id e quelli inviati a tutti
		if ( p.isForMe(id) )
		{	
			synchronized(lock){
				if ( p.getData(0) == null)
					dataReceived.add(new Message ( p.getFrom(), p.getData(id)));
				else
					dataReceived.add(new Message ( p.getFrom(), p.getData(0) ));
			}
			car.update();
		}
	}
	@Override
	public void sendBroadcast(Packet p) {
		// TODO Auto-generated method stub
		
	}
}
