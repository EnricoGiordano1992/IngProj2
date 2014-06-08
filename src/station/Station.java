package station;

import java.util.Vector;

import comunication.Comunication;
import comunication.Message;
import comunication.Net;
import comunication.Packet;

public class Station implements Runnable{

	static final String ideal = "IDEAL";
	static final String decrease = "DECREASE THE SPEED";
	static final String busy = "BUSY";
	
	private int pps;
	private int id;
	private Comunication com;
	private int from=0;
	private String mex;
	private Packet packet;
	private Net net;
	Vector<Message> messages;
	
	public Station( int pps, Net net )
	{
		this.pps = pps;
		this.net = net;
		this.com = new Comunication(pps, this, net);
		if( com.join() )
			id = com.getId();
		else
			System.out.println("Errore fatale nella creazione della rete!");
	}
	@Override
	public void run()
	{		
		while(true)
		{
			this.sendBroadcast(com);
			// leggo tutti i messaggi che ci sono in coda
			messages = com.readAllMessages();
			if( messages != null )
			{
				packet = new Packet(id);
				
				for(Message me : messages){
					from=me.getFrom();
					mex=me.getData();
					// salto i pacchetti provenienti dalla stazione
					if( from != 0 )
					{
						System.out.println("Data stazione: " + mex);	
						System.out.println("Da           : " + from);
						String[] s = mex.split(";");
						if ( s[0].compareTo("OK") == 0)
						{
							// in s[1] è presente il pps
							if( s.length > 1 && net.canIJoin(Integer.parseInt(s[1])))
							{
								packet.addMessage(from, "OK-JOIN");
							}
							else
								packet.addMessage(from, busy);
						}
					}
				}
				//sends a broadcasting packet to all cars on the road asking them to join it
				com.sendBroadcast(packet);
			}
			try {
				Thread.sleep(1000/pps);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public int join( Comunication c )
	{
		int ret =0;
		
		return ret;
	}
	public void sendBroadcast( Comunication c){
		/**
		 * Registro la stazione tra gli observers della macchina
		 */
		c.register(com);
		/**
		 * Registro la macchina tra gli observers della stazione
		 */
		com.register(c);
		c.receive(new Packet(0, c.getId(), "JOIN"));
	}
	public void sendMessage(){

	}

	public void receiveMessage(){

	}

	public void update(){

	}
}