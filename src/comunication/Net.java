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
		for( i=0 ; i < bandwidth.size() ; i++ )
		{
			if ( bandwidth.get(i) + c.getPps() <= capacity && !joined )
			{
				bandwidth.set(i, bandwidth.get(i) + c.getPps());
				park.remove(c.getId());
				connected.insert(c);
				c.setChannel(bandwidth.indexOf(bandwidth.get(i)));
				joined = true;
				g.print("Banda : " + bandwidth.get(i) + " sul canale " + i);
			}
		}
		if( ! joined )
		{
			g.print("Nessuno spazio libero con i " + i);
			if ( i < maxChannels )
			{
				bandwidth.add(new Integer(0));
				bandwidth.set(i, bandwidth.get(i) + c.getPps());
				park.remove(c.getId());
				c.setChannel(bandwidth.indexOf(bandwidth.get(i)));
				g.print("banda : " + bandwidth.get(i) + " sul canale " + i);
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
			else if( bandwidth.size() < maxChannels && capacity <= pps )
				res = true;
			else if( bandwidth.size() <= maxChannels && bandwidth.get(bandwidth.size() - 1) + pps <= capacity )
				res = true;
			else
				res = false;
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
			g.print("Banda: " + bandwidth.get(c.getChannel()) + " sul canale " + c.getChannel());
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