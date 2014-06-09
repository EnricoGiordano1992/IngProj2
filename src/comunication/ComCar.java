package comunication;

import java.util.Vector;

import car.Car;

public class ComCar extends Comunication {

	private Car car;
	
	public ComCar( int pps, Car car, Net net )
	{
		this.pps = pps;
		this.car = car;
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
