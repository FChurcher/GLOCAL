package model;

public class Job {
	private Process process;
	private Thread waiter;
	private boolean done;
	
	private long startTime;
	private long endTime;
	
	public Job(Process p) {
		this.startTime = System.currentTimeMillis();
		this.process = p;
		this.done = false;
		this.waiter = new Thread(new Runnable() {
			public void run() {
				try { process.waitFor(); } catch (InterruptedException e) { e.printStackTrace(); }
				endTime = System.currentTimeMillis();
				done = true;
			}
		});
		this.waiter.start();
	}
	
	public boolean isDone() {
		return done;
	}
	
	public long getDuration() {
		if (isDone()) {
			return this.endTime - this.startTime;
		}
		return -1;
	}

}
