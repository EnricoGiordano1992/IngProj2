package comunication;

import java.util.Vector;

//TODO
public abstract class Comunication implements Runnable{

	protected int pps;
	
	protected Vector<Comunication> observers;
	protected Net net;
	protected boolean connected = false;

	/**
	 * Vettore contenente i messaggi in arrivo
	 */
	protected Vector<Message> dataReceived;
	/**
	 * Pacchetto contenente i messsaggi da inviare
	 */
	protected Packet toSend;
	protected int id = 0;
	protected int channel;
	protected final Object lock = new Object();
	protected final Object lock_observers = new Object();
	
	public Comunication(){}
	public abstract void sendBroadcast( Packet p );
	public abstract void receive( Packet p);
	
	public boolean join()
	{
		if ( !connected && net.join(this) )
		{
			toSend = new Packet(id);
			connected = true;
			return true;
		}
		return connected;
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
			synchronized (lock_observers) {
				for( Comunication com : observers )
						com.receive(toSend);
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
		synchronized (lock_observers) {
			observers.add(c);
		}
		
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

	public void setId(int id) {
		this.id = id;
	}
}
