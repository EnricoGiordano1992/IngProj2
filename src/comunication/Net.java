package comunication;

import graphics.ScenarioGraphic;

import java.util.ArrayList;
import java.util.Random;

import station.Station;

public class Net {

	private int capacity;
	/**
	 * 
	 */
	private ArrayList<Comunication> devices = new ArrayList<Comunication>();
	private ArrayList<Integer> bandwidth;
	private final Random ran;
	private int ids = 0;
	private final Object lock = new Object();
	private Station station;
	private int maxChannels = 1;
	private boolean joined = false;
	private int i=0;
	private ScenarioGraphic g;
	
	public Net( int capacity, int maxChannels, ScenarioGraphic g )
	{
		this.g = g;
		this.capacity = capacity;
		bandwidth  = new ArrayList<Integer>(maxChannels);
		bandwidth.add(new Integer(0));
		this.maxChannels = maxChannels;
		ran = new Random();
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
			station.sendBroadcast( c );
	}
	/**
	 * Funzione per connettersi alla rete, dopo aver ricevuto il pacchetto
	 * JOIN da parte della stazione
	 * @return Id univo per l'identificazione sulla rete, se ritorna -1 allora la rete � piena
	 */
	public int join( Comunication c )
	{
		int ret = -1;
		g.print("JOIN in NET");
		synchronized(lock){
			joined = false;
			for( i=0 ; i < bandwidth.size() ; i++ )
			{
				if ( bandwidth.get(i) + c.getPps() <= capacity && !joined )
				{
					g.print("Aggiunta macchina con pps " + c.getPps());
					bandwidth.set(i, bandwidth.get(i) + c.getPps());
					devices.add(c);
					c.setChannel(bandwidth.indexOf(bandwidth.get(i)));
					ret = ids++;
					joined = true;
					g.print("banda : " + bandwidth.get(i) + " sul canale " + i);
				}
			}
			if( ! joined )
			{
				g.print("Nessuno spazio libero con i " + i);
				if ( i < maxChannels )
				{
					bandwidth.add(new Integer(0));
					bandwidth.set(i, bandwidth.get(i) + c.getPps());
					devices.add(c);
					c.setChannel(bandwidth.indexOf(bandwidth.get(i)));
					ret = ids++;
					g.print("banda : " + bandwidth.get(i) + " sul canale " + i);
				}
			}
		}
		return ret;
	}
	/**
	 * Metodo per verificare se è disponibile un posto dato un pps
	 */
	public boolean canIJoin( int pps )
	{
		boolean res = false;
		synchronized(lock){
			if( bandwidth.size() < maxChannels && capacity <= pps )
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
		}
	}
}