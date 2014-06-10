package station;

import graphics.ScenarioGraphic;

import java.util.Vector;

import node.Node;
import comunication.ComStation;
import comunication.Comunication;
import comunication.Message;
import comunication.Net;
import comunication.Packet;

public class Station extends Node{

	static final String ideal = "IDEAL";
	static final String decrease = "DECREASE THE SPEED";
	static final String busy = "BUSY";
	
	private int pps;
	private int id;
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
		com = new ComStation(pps, this, net);
		if( com.join() )
			id = com.getId();
		else
			g.print("Fatal error in network creation!");
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
							if( s.length > 1 && net.canIJoin(from, Integer.parseInt(s[1])))
							{
								g.print("Car [" + from + "] can join ");
								packet.addMessage(from, "OK-JOIN");
							}
							else
								packet.addMessage(from, busy);
						}
						else if( s[0].compareTo("SPEED") == 0 )
						{
							if( s.length > 1 && Integer.parseInt(s[1]) > 50 )
							{
								packet.addMessage(from, decrease);
							}
						}
					}
				}
				if(newMex)
				{
					//sends a broadcasting packet to all cars on the road asking them to join it
					com.sendBroadcast(packet);
					newMex = false;
				}
			}
			try {
				Thread.sleep(1000/pps);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void sendBroadcast( Comunication c){
		com.sendBroadcast(new Packet(0, 0, "JOIN"));
	}
	@Override
	public void update(){
		
	}
	@Override
	public void receive() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void send() {
		// TODO Auto-generated method stub
		
	}
}