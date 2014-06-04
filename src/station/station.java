package station;

import java.util.Vector;

import comunication.Comunication;
import comunication.Message;
import comunication.Net;
import comunication.Packet;
import comunication.ComunicationBroadcast;


public class station {

	private int pps;
	private int id = 0;
	private static Comunication com;
	public static ComunicationBroadcast comB;

	public static void main (String[] args){
	
		String ideal = "IDEAL";
		String decrease = "DECREASE THE SPEED";
		
		Net net = new Net(100,5);
		net.join(com);
		
		Vector<Message> Messages = com.readAllMessages();
		//threshold: 50 km/h
		
		private int from=0;
		private String mex;
		private Packet packet;
		
		packet = new Packet(0);
		while(true){
			for(Message me : Messages){
				from=me.getFrom();
				mex=me.getData();
				
				String[] split = mex.split(" ");
				
				//i = position of speed 
				if(Integer.parseInt(split[i]) > 50)
					packet.addMessage(from, decrease);
				else
					packet.addMessage(from, ideal);
				
			}
			
			//sends a broadcasting packet to all cars on the road asking them to join it
			comB.write(packet);
			
			Thread.sleep(100);
		}
		
		public void sendMessage(){
			
		}
		
		public void receiveMessage(){
			
		}
		
		public void update(){
			
		}

	}
}