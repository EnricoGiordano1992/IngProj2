package comunication;

import graphics.ScenarioGraphic;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import station.Station;

public class Net {

	private int capacity;
	private CopyOnWriteArrayList<Comunication> devices = new CopyOnWriteArrayList<Comunication>();
	private ArrayList<Integer> bandwidth;
	private final Object lock;
	private Station station;
	private int maxChannels = 1;
	private boolean joined = false;
	private int i=0;
	private ScenarioGraphic g;
	
	private Cqueue park;
	private Cqueue connected;
	
	public Net( int capacity, int maxChannels, ScenarioGraphic g )
	{
		this.g = g;
		this.capacity = capacity;
		bandwidth  = new ArrayList<Integer>(maxChannels);
		bandwidth.add(new Integer(0));
		this.maxChannels = maxChannels;
		park = new Cqueue();
		connected = new Cqueue();
		lock = new Object();
	}
	public void setStation( Station s ){
		this.station = s;
	}
	/**
	 * Metodo chiamato dalle macchine quando entrano nella rete
	 * @param c L'oggetto Comunication
	 */
	public void joinBroadcast( Comunication c )
	{
		synchronized(lock){
			devices.add(c);
		}
		park.insert(c);
		station.register(c);
		station.sendBroadcast( c );
	}
	public boolean join ( Comunication c )
	{
		synchronized(lock){
			joined = false;
			g.print("[ " + c.getId() + " ] trying join...");
			for( i=0 ; i < bandwidth.size() ; i++ )
			{
				if ( bandwidth.get(i) + c.getPps() <= capacity && !joined )
				{
					bandwidth.set(i, bandwidth.get(i) + c.getPps());
					park.remove(c.getId());
					connected.insert(c);
					c.setChannel(bandwidth.indexOf(bandwidth.get(i)));
					joined = true;
					g.print("[ " + c.getId() + " ] Capacity : " + bandwidth.get(i) + " on channel " + i);
				}
			}
			if( ! joined )
			{
				if ( i < maxChannels )
				{
					bandwidth.add(new Integer(0));
					bandwidth.set(i, bandwidth.get(i) + c.getPps());
					park.remove(c.getId());
					c.setChannel(bandwidth.indexOf(bandwidth.get(i)));
					g.print("[ " + c.getId() + " ] Capacity : " + bandwidth.get(i) + " on channel " + i);
				}
			}
		}
		return joined;
	}
	/**
	 * Metodo per verificare se è disponibile un posto dato un pps
	 */
	public boolean canIJoin( int id, int pps )
	{
		boolean res = false;

		synchronized(lock){
			if ( connected.contains(id) )
				res = false;
			else if( bandwidth.size() < maxChannels && pps <= capacity )
				res = true;
			else if( bandwidth.size() <= maxChannels )
				{
					for ( int i = 0 ; i < bandwidth.size() ;i++)
						if ( bandwidth.get(i) + pps <= capacity )
							res = true;
				}
		}
		return res; 
	}
	/**
	 * Invia un pacchetto a tutta la rete
	 * @param p Pacchetto da inviare in broadcast
	 */
	public void sendBroadcast( Packet p )
	{
		synchronized(lock){
			for ( Comunication c : devices )
				c.receive(p);
		}
	}
	public void leave( Comunication c )
	{
		synchronized(lock)
		{

			bandwidth.set(c.getChannel(), bandwidth.get(c.getChannel()) - c.getPps());
			g.print("Capacity: " + bandwidth.get(c.getChannel()) + " on channel " + c.getChannel());
			devices.remove(c);
			connected.remove(c.getId());
			if ( park.size() > 0 )
			{
				Comunication temp = park.getFirstWithoutRemove();
				if ( canIJoin(temp.getId(), temp.getPps() ))
				{
					temp.receive(new Packet(0, park.getFirstWithoutRemove().getId(), "OK-JOIN"));
				}
			}
		}
	}
}