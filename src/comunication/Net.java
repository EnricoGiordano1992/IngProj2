package comunication;

import java.util.ArrayList;
import java.util.Random;

public class Net {

	private int capacity;
	private ArrayList<Comunication> devices = new ArrayList<Comunication>();
	private ArrayList<Integer> bandwidth;
	private static Random ran;
	private final Object lock = new Object();
	
	public Net( int capacity, int maxChannels )
	{
		this.capacity = capacity;
		bandwidth  = new ArrayList<Integer>(maxChannels);
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
	public void leave( Comunication c )
	{
		synchronized(lock)
		{
			bandwidth.set(c.getChannel(), bandwidth.get(c.getChannel()) - c.getPps());
			devices.remove(c);
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