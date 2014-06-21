package comunication;

import java.util.Vector;

import station.Station;
//TODO
public class ComStation extends Comunication{
	private Station station;

	public ComStation( int pps, Station s, Net net )
	{
		this.pps = pps;
		this.station = s;
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
		if ( p.getFrom() != id )
		{	
			synchronized(lock){
				dataReceived.add(new Message ( p.getFrom(), p.getData(id)));
			}
		}
	}
	public void sendBroadcast( Packet p ){
		net.sendBroadcast(p);
	}
	@Override
	public void run() {
	}
}
