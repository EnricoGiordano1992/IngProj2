package comunication;

import java.util.HashMap;

public class Packet {

	HashMap<Integer, String> message;
	private int from;
	
	/**
	 * Un pacchetto deve avere sempre il mittente
	 * @param from Id del mittente
	 */
	public Packet ( int from )
	{
		message = new HashMap<Integer, String>();
		this.from = from;
	}
	/**
	 * Crea un pacchetto con mittente, destinatario e un dato
	 * @param from Id del mittente
	 * @param to Id del destinatario
	 * @param data Stringa con le informazioni
	 */
	public Packet ( int from, int to, String data )
	{
		message = new HashMap<Integer, String>();
		message.put(to, data);
		this.from = from;
	}
	/**
	 * Aggiunge un messaggio al pacchetto
	 * @param to Id del destinatario
	 * @param data Stringa contenente le informazioni
	 */
	public void addMessage( int to, String data )
	{
		message.put(to, data);
	}
	/**
	 * Ricava il dato del pacchetto rivolto al destinatario con id uguale a to
	 * @param to Id del destinatario
	 * @return Il dato del messaggio
	 */
	public String getData( int to )
	{
		return message.get(to);
	}
	/**
	 * Controlla se ci sono messaggi del il destinatario specificato in id
	 * @param id Id del destinatario
	 * @return True se è presente un messaggio, false altrimenti.
	 */
	public boolean isForMe( int id )
	{
		return message.containsKey(id);
	}
	/**
	 * Ritorna il mittente del pacchetto
	 * @return
	 */
	public int getFrom() {
		return from;
	}
}
