package comunication;

public class Message {

	private int from;
	private String data;
	
	public Message( int from, String data )
	{
		this.from = from;
		this.data = data;
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
}
