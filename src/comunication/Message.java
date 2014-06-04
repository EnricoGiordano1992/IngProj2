package comunication;

public class Message {

	private int to;
	private int from;
	private String data;
	
	public Message( int from, int to, String data )
	{
		this.from = from;
		this.data = data;
		this.to = to;
	}
	public Message( int from, String data )
	{
		this.from = from;
		this.data = data;
		this.to = to;
	}
	public Message(){}
	
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
}
