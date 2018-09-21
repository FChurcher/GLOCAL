package model;

import java.util.ArrayList;

public class Alignment {
	private ArrayList<Sequence> sequences;
	private ArrayList<Sequence> alignedSequences;
	
	
	public Alignment(ArrayList<Sequence> sequences, ArrayList<Sequence> alignedSequences) {
		this.sequences = sequences;
		this.alignedSequences = alignedSequences;
	}


	public ArrayList<Sequence> getSequences() {
		return sequences;
	}


	public void setSequences(ArrayList<Sequence> sequences) {
		this.sequences = sequences;
	}


	public ArrayList<Sequence> getAlignedSequences() {
		return alignedSequences;
	}


	public void setAlignedSequences(ArrayList<Sequence> alignedSequences) {
		this.alignedSequences = alignedSequences;
	}
	
	
	

}
