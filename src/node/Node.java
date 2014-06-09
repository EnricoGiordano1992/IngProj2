package node;

import comunication.Comunication;

public abstract class Node implements Runnable{

	protected Comunication com;
	protected int pps;
	protected int id;
	
	
	public abstract void update();
	public abstract void run();
	public abstract void receive();
	public abstract void send();
	
	public void register( Comunication com )
	{
		this.com.register(com);
		com.register(this.com);
	}
}
