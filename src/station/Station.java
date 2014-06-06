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

	
//	@Override
//	public void run()
//	{
//
//		String ideal = "IDEAL";
//		String decrease = "DECREASE THE SPEED";
//
//		Net net = new Net(100,5, this);
//		net.join(com);
//
//		Vector<Message> Messages = com.readAllMessages();
//		//threshold: 50 km/h
//
//		packet = new Packet(0);
//		while(true){
//			for(Message me : Messages){
//				from=me.getFrom();
//				mex=me.getData();
//
//				String[] split = mex.split(" ");
//
//				//i = position of speed in the message
//				if(Integer.parseInt(split[1]) > 50)
//					packet.addMessage(from, decrease);
//				else
//					packet.addMessage(from, ideal);
//
//			}
//
//			//sends a broadcasting packet to all cars on the road asking them to join it
//			comB.write(packet);
//
//			try{
//				Thread.sleep(100);
//			}catch(Exception e){}
	Vector<Message> messages;
	
	public Station( int pps, Net net )
	{
		this.pps = pps;
		this.net = net;
		this.com = new Comunication(pps, null, net);
		id = net.join(this.com);
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
				packet = new Packet(0);
				
				for(Message me : messages){
					from=me.getFrom();
					mex=me.getData();
										
					System.out.println("Pacchetto ricevuto " + mex + " da " + from);
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
		c.register(c);
		c.receive(new Packet(0, c.getId(), "JOIN"));
	}
	public void sendMessage(){

	}

	public void receiveMessage(){

	}

	public void update(){

	}
}