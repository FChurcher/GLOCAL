package controller;

import java.util.Iterator;
import java.util.LinkedList;

import io.TimesWriter;
import model.AlignmentJobGroup;
import model.Job;

public class JobHolder {
	private static LinkedList<Job> runningjobs = new LinkedList<>();
	private static LinkedList<AlignmentJobGroup> waitingAlignments = new LinkedList<>();
	private static int maxJobs = 7;
	
	private static boolean run;
	
	
	public static void start() {
		run = true;
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (run) {
					clean();
					startJob();
					System.out.println("[running jobs " + runningjobs.size() + "]");
					try { Thread.sleep(500); } catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		});
		t.start();
	}
	
	public static void add(AlignmentJobGroup alignmentJobGroup) {
		waitingAlignments.add(alignmentJobGroup);
		System.out.println("added: " + alignmentJobGroup.getName());
	}
	
	public static void startJob() {
		if (runningjobs.size() < maxJobs) {
			for (AlignmentJobGroup waitingAlignment : waitingAlignments) {
				if (!waitingAlignment.isFullyRunning()) {
					runningjobs.add(waitingAlignment.startOne());
					break;
				}
			}
		}
	}
	
	public static void clean() {
		for (Iterator<Job> it = runningjobs.iterator(); it.hasNext();) {
			Job job = it.next();
			if (job.isDone()) {
				it.remove();
			}
		}
		for (Iterator<AlignmentJobGroup> it = waitingAlignments.iterator(); it.hasNext();) {
			AlignmentJobGroup alignmentJobGroup = it.next();
			if (alignmentJobGroup.isDone()) {
				TimesWriter.writeTimes(alignmentJobGroup);
				it.remove();
			}
		}
	}
}
