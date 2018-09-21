package model;

public class Job {
	private Process process;
	private Thread waiter;
	private boolean done;
	
	public Job(Process p) {
		this.process = p;
		this.done = false;
		
		waiter = new Thread(new Runnable() {
			public void run() {
				try { process.waitFor(); } catch (InterruptedException e) { e.printStackTrace(); }
				done = true;
			}
		});
		waiter.start();
	}
	
	public boolean isDone() {
		return !process.isAlive();
	}

}
