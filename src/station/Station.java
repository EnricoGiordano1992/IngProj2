package station;

import graphics.ScenarioGraphic;

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
	private boolean newMex = false;
	private ScenarioGraphic g;
	
	public Station( int pps, Net net, ScenarioGraphic g )
	{
		this.g = g;
		this.pps = pps;
		this.net = net;
		net.setStation(this);
		this.com = new Comunication(pps, this, net);
		if( com.join() )
			id = com.getId();
		else
			g.print("Errore fatale nella creazione della rete!");
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
						newMex = true;
						String[] s = mex.split(";");
						if ( s[0].compareTo("OK") == 0)
						{
							// in s[1] è presente il pps
							if( s.length > 1 && net.canIJoin(Integer.parseInt(s[1])))
							{
								g.print("Macchina " + from + " si può connettere ");
								packet.addMessage(from, "OK-JOIN");
							}
							else
								packet.addMessage(from, busy);
						}
					}
				}
				if(newMex)
				{
					//sends a broadcasting packet to all cars on the road asking them to join it
					g.print("Invio i pacchetti");
					com.sendBroadcast(packet);
					newMex = false;
				}
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