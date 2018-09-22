package io;

import java.io.File;
import java.util.ArrayList;

import controller.JobBuilder;
import controller.JobHolder;
import model.AlignmentJobGroup;

public class FileCollector {
	public static final File toAlignDir = new File("LMATFU" + File.separator + "to_align");
	
	public static ArrayList<String> regocnicedAlignmentNames = new ArrayList<>();
	
	public static boolean run;
	
	
	public static void start() {
		run = true;
		refresh();
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (run) {
					refresh();
					try { Thread.sleep(2500); } catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		});
		t.start();
	}
	
	public static void stop() {
		run = false;
	}
	
	public static void refresh() {
		System.out.println("[refreshing files]");
		for (File file : toAlignDir.listFiles()) {
			String name = file.getName().substring(0, file.getName().lastIndexOf('.'));
			if (!regocnicedAlignmentNames.contains(name)) {
				regocnicedAlignmentNames.add(name);
				System.out.println("new aligment name found: " + name);
				JobHolder.add(new AlignmentJobGroup(name));
			}
		}
	}

}
