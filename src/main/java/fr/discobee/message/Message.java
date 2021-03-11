package fr.discobee.message;

public class Message {

	private final String id;
	private final String content;
	protected Message(String idIn, String contentIn) {
		this.id = idIn;
		this.content = contentIn;
	}
	
	public String getId() {
		return this.id;
	}

	public void delete() {
		
	}
	
	public String getContent() {
		return this.content;
	}
	
}
