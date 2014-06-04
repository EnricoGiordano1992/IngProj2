package comunication;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import station.Station;

public class Net {

	private int capacity;
	private ArrayList<Comunication> devices = new ArrayList<Comunication>();
	private Vector<Comunication> broadcastDevices = new Vector<Comunication>();
	private ArrayList<Integer> bandwidth;
	private static Random ran;
	private final Object lock = new Object();
	private Station station;
	
	public Net( int capacity, int maxChannels, Station station )
	{
		this.capacity = capacity;
		bandwidth  = new ArrayList<Integer>(maxChannels);
		this.station = station;
	}
	/**
	 * Funzione per connettersi alla rete
	 * @return Id univo per l'identificazione sulla rete
	 */
	public int join( Comunication c )
	{
		int ret = -1;
		synchronized(lock){
			for ( Integer cap : bandwidth )
			{
				if ( cap + c.getPps() <= capacity )
				{
					cap+=c.getPps();
					devices.add(c);
					c.setChannel(bandwidth.indexOf(cap));
					ret = ran.nextInt();
					break;
				}
			}
		}
		return ret;
	}
	public void joinBroadcast( Comunication c )
	{
		synchronized(lock){
			broadcastDevices.add(c);
			station.sendBroadcast( c );
		}
	}
	public void sendBroadcast( Packet p )
	{
		synchronized(lock){
			for ( Comunication c : broadcastDevices )
				c.receive(p);
		}
	}
	public void leave( Comunication c )
	{
		synchronized(lock)
		{
			bandwidth.set(c.getChannel(), bandwidth.get(c.getChannel()) - c.getPps());
			devices.remove(c);
			broadcastDevices.remove(c);
		}
	}
	public void sendToAll( Packet p )
	{
		for( Comunication com : devices )
		{
			com.receive(p);
		}
	}
}