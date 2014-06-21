package comunication;

import java.util.LinkedList;
import java.util.Queue;

public class Cqueue {

	private Queue<Item> q;
	private final Object lock;
	
	public Cqueue()
	{
		q = new LinkedList<Cqueue.Item>();
		lock = new Object();
	}
	
	public void insert(int id, Comunication com )
	{
		synchronized (lock) {
			q.add(new Item(com, id));
		}
	}
	public void insert( Comunication com )
	{
		synchronized (lock) {
			q.add(new Item(com, com.getId()));
		}
	}
	public void remove( int id )
	{
		synchronized (lock) {
			q.remove(new Item(null, id));
		}
	}
	public Comunication getFirst()
	{
		Comunication c = null;
		synchronized (lock) {
			Item i = q.remove();
			c = i.getCom();
		}
		return c;
	}
	public Comunication getFirstWithoutRemove()
	{
		Comunication c = null;
		synchronized (lock) {
			Item i = q.peek();
			c = i.getCom();
		}
		return c;
	}
	public int size()
	{
		int res = 0;
		synchronized (lock) {
			res = q.size();
		}
		return res;
	}
	public boolean contains( int id )
	{
		return q.contains(new Item(null, id ));
	}

	private class Item{
		private Comunication com;
		private int id;
		
		public Item(Comunication com, int id) {
			super();
			this.com = com;
			this.id = id;
		}
		public Comunication getCom() {
			return com;
		}
		public int getId() {
			return id;
		}
		@Override
		public int hashCode() {
			return id;
		}
		@Override
		public boolean equals(Object obj) {
			if( !(obj instanceof Item) )
				return false;
			return ((Item) obj).getId()==this.id ? true : false ;
		}
	}
}
