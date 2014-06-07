package station;

import java.util.Vector;

import comunication.Comunication;
import comunication.Message;
import comunication.Net;
import comunication.Packet;

public class Station implements Runnable{

	static final String ideal = "IDEAL";
	static final String decrease = "DECREASE THE SPEED";
	
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
			// leggo tutti i messaggi che ci sono in coda
			messages = com.readAllMessages();
			if( messages != null )
			{
				packet = new Packet(id);
				
				for(Message me : messages){
					from=me.getFrom();
					mex=me.getData();
										
//					System.out.println("Pacchetto ricevuto " + mex + " da " + from);
//					String[] split = mex.split(" ");
					packet.addMessage(from, ideal);
					
					//i = position of speed 
	//				if(Integer.parseInt(split[i]) > 50)
	//					packet.addMessage(from, decrease);
	//				else
	//					packet.addMessage(from, ideal);
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
		c.register(com);
		c.receive(new Packet(0, c.getId(), "JOIN"));
	}
	public void sendMessage(){

	}

	public void receiveMessage(){

	}

	public void update(){

	}
}