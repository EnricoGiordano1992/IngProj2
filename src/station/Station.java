package station;

import java.util.Vector;

import comunication.Comunication;
import comunication.Message;
import comunication.Net;
import comunication.Packet;

public class Station {

	private int pps;
	private int id = 0;
	private Comunication com;
	private int from=0;
	private String mex;
	private Packet packet;
	private Net net;

	public Station( int pps, Net net )
	{
		this.pps = pps;
		this.net = net;
		this.com = new Comunication(pps, null, net);
	}
	public void run()
	{
	
		String ideal = "IDEAL";
		String decrease = "DECREASE THE SPEED";
		
		Net net = new Net(100,5, this);
		net.join(com);
		
		Vector<Message> Messages = com.readAllMessages();
		//threshold: 50 km/h

		packet = new Packet(0);
		while(true){
			for(Message me : Messages){
				from=me.getFrom();
				mex=me.getData();
				
				String[] split = mex.split(" ");
				
				//i = position of speed 
//				if(Integer.parseInt(split[i]) > 50)
//					packet.addMessage(from, decrease);
//				else
//					packet.addMessage(from, ideal);
			}
			
			//sends a broadcasting packet to all cars on the road asking them to join it
			com.sendBroadcast(packet);
			
			try {
				Thread.sleep(1000/pps);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void sendBroadcast( Comunication c){
		c.receive(new Packet(0, c.getId(), "JOIN"));
	}
	public void sendMessage(){
		
	}
	
	public void receiveMessage(){
		
	}
	
	public void update(){
		
	}
}