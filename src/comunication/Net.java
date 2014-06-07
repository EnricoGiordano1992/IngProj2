package comunication;

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
	
	public Net( int capacity, int maxChannels )
	{
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
		System.out.println("JOIN in NET");
		synchronized(lock){
			joined = false;
			for( i=0 ; i < bandwidth.size() ; i++ )
			{
				if ( bandwidth.get(i) + c.getPps() <= capacity && !joined )
				{
					System.out.println("Aggiunta macchina con pps " + c.getPps());
					bandwidth.set(i, bandwidth.get(i) + c.getPps());
					devices.add(c);
					c.setChannel(bandwidth.indexOf(bandwidth.get(i)));
					ret = ids++;
					joined = true;
					System.out.println("banda : " + bandwidth.get(i) + " sul canale " + i);
				}
			}
			if( ! joined )
			{
				System.out.println("Nessuno spazio libero con i " + i);
				if ( i < maxChannels )
				{
					bandwidth.add(new Integer(0));
					bandwidth.set(i, bandwidth.get(i) + c.getPps());
					devices.add(c);
					c.setChannel(bandwidth.indexOf(bandwidth.get(i)));
					ret = ids++;
					System.out.println("banda : " + bandwidth.get(i) + " sul canale " + i);
				}
			}
		}
		
		return ret;
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
			System.out.println("Banda: " + bandwidth.get(c.getChannel()) + " sul canale " + c.getChannel());
			devices.remove(c);
		}
	}
}