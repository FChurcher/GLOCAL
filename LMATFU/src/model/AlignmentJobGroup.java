package model;

public class AlignmentJobGroup {
	private Job glocal, t_coffee, mafft, clustalw;
	
	public AlignmentJobGroup(Job glocal, Job t_coffee, Job mafft, Job clustalw) {
		this.glocal = glocal;
		this.t_coffee = t_coffee;
		this.mafft = mafft;
		this.clustalw = clustalw;
	}
	
	public boolean isDone() {
		return glocal.isDone() && t_coffee.isDone() && mafft.isDone() && clustalw.isDone();
	}

	
}
