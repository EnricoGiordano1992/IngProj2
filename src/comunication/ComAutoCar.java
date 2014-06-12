package comunication;

import java.util.Vector;

import car.AutoCar;

//TODO
public class ComAutoCar extends Comunication implements Runnable{

	private AutoCar car;

	public void run(){
		while(true){
			try{
				Thread.sleep(1000/car.getP_rate());
			}catch(Exception e){}

			write(0, "SPEED;"+car.getVelocity());
			send();
		}
	}

	public ComAutoCar( int pps, AutoCar autoCar, Net net )
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
