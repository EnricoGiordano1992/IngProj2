package comunication;

import java.util.Vector;

import station.Station;
import car.Car;

public class Comunication {

	private int pps;
	private Car car;
	private Station station;
	Vector<Comunication> observers;
	private Net net;

	/**
	 * Vettore contenente i messaggi in arrivo
	 */
	private Vector<Message> dataReceived;
	/**
	 * Pacchetto contenente i messsaggi da inviare
	 */
	private Packet toSend;
	private int id = 0;
	private int channel;
	private final Object lock = new Object();
	
	public Comunication( int pps, Car car, Net net )
	{
		this.pps = pps;
		this.car = car;
		this.station = null;
		this.net = net;
		dataReceived = new Vector<Message>();
		observers = new Vector<Comunication>();
	}
	public Comunication( int pps, Station s, Net net )
	{
		this.pps = pps;
		this.station = s;
		this.net = net;
		this.car = null;
		dataReceived = new Vector<Message>();
		observers = new Vector<Comunication>();
	}
	public boolean join()
	{
		int temp = net.join(this);
		if ( temp >= 0 )
		{
			id = temp;
			toSend = new Packet(id);
			return true;
		}
		return false;
	}
	public void leave()
	{
		net.leave(this);
	}
	public void setPps(int pps) {
		this.pps = pps;
	}
	/**
	 * Invio il pacchetto creato, se il pacchetto � null non spedisco nulla
	 */
	public void send()
	{
		if ( toSend != null )
			for( Comunication com : observers )
					com.receive(toSend);
	}
	/**
	 * Controlla se nel pacchetto � presente un messaggio per questa macchina
	 * @param p
	 */
	public void receive( Packet p )
	{
		if ( p.isForMe(id) )
		{	
			synchronized(lock){
				dataReceived.add(new Message ( p.getFrom(), p.getData(id)));
			}
			if ( car != null )
				car.update();
			else
				station.update();
		}
	}
	/**
	 * Leggo il primo messaggio che � stato ricevuto
	 * @return
	 */
	public Message readMessages()
	{

		if ( dataReceived.size() > 0)
			return dataReceived.remove(0);
		else
			return null;
	}
	/**
	 * Leggo tutti i messaggi nella coda 
	 * @return
	 */
	public Vector<Message> readAllMessages()
	{
		if ( dataReceived.size() > 0)
			{
			Vector<Message> temp = new Vector<Message>(dataReceived);
			dataReceived.clear();
			return temp;
			}
		else
			return null;
	}
	public void register ( Comunication c )
	{
		observers.add(c);
	}
	/**
	 * Inserisco nel pacchetto i messaggi che voglio inviare
	 * @param to Destinatario del messaggio
	 * @param data Il messaggio da inviare
	 */
	public void write( int to, String data )
	{
		if ( toSend == null )
			toSend = new Packet(id);
		toSend.addMessage(to,data);
	}
	public int getId()
	{
		return id;
	}
	public int getPps() {
		return pps;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public void sendBroadcast( Packet p ){
		toSend = p;
		send();
	}
	public void setId(int id) {
		this.id = id;
	}
}
