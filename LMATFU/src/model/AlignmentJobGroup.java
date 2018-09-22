package model;

import controller.AlignmentStarter;

public class AlignmentJobGroup {
	String name;
	private Job glocal, t_coffee, mafft, clustalw;
	
	public AlignmentJobGroup(String name) {
		this.name = name;
		this.glocal = null;
		this.t_coffee = null;
		this.mafft = null;
		this.clustalw = null;
	}
	
	public void startOne(){
		if (this.glocal == null) {
			this.glocal = AlignmentStarter.startGlocal(name);
		} else if (this.t_coffee == null) {
			this.t_coffee = AlignmentStarter.startT_coffee(name);
		} else if (this.mafft == null) {
			this.mafft = AlignmentStarter.startMafft(name);
		} else if (this.clustalw == null) {
			this.clustalw = AlignmentStarter.startClustalw(name);
		}
	}
	
	public boolean isFullyRunning() {
		if (glocal == null || this.t_coffee == null || this.mafft == null || this.clustalw == null) {
			return false;
		}
		return true;
	}
	
	public boolean isDone() {
		if (glocal == null || this.t_coffee == null || this.mafft == null || this.clustalw == null) {
			return false;
		}
		return glocal.isDone() && t_coffee.isDone() && mafft.isDone() && clustalw.isDone();
	}
	
	public String getName() {
		return name;
	}

	
}
