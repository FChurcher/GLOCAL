package model;

public class Sequence{
	private String sequence;
	private String name;
	
	
	public Sequence(String sequence, String name) {
		this.sequence = sequence;
		this.name = name;
	}
	
	
	public String getSequence() {
		return sequence;
	}
	
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
