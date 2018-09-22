package controller;

import java.util.Iterator;
import java.util.LinkedList;

import model.AlignmentJobGroup;
import model.Job;

public class JobHolder {
	private static LinkedList<Job> runningjobs = new LinkedList<>();
	private static LinkedList<AlignmentJobGroup> waitingAlignments = new LinkedList<>();
	private static int maxJobs = 7;
	
	public static void add(AlignmentJobGroup alignmentJobGroup) {
		waitingAlignments.add(alignmentJobGroup);
		System.out.println("added: " + alignmentJobGroup.getName());
		fill();
	}
	
	public static void fill() {
		clean();
		while (runningjobs.size() < maxJobs) {
			waitingAlignments.getFirst().startOne();
			clean();
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
				it.remove();
			}
		}
	}
}
