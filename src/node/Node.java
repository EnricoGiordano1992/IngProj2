package node;

import comunication.Comunication;

public abstract class Node implements Runnable{

	private Comunication com;
	private int pps;
	private int id;
	
	
	public abstract void update();
	public abstract void run();
	public abstract void receive();
	public abstract void send();
}
