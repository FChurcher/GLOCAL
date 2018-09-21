package io;

import java.io.File;
import java.util.ArrayList;

import controller.JobBuilder;

public class FileCollector {
	public static final File toAlignDir = new File("LMATFU" + File.separator + "to_align");
	
	public static ArrayList<String> fileNamesToAlign = new ArrayList<>();
	public static Process refreshingProcess;
	public static boolean run;
	
	public static void start() {
		run = true;
		refresh();
		System.out.println("step");
		System.out.println(run);
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (true) {
					refresh();
					try {
						Thread.sleep(2500);
					} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		});
		t.start();
	}
	
	public static void stop() {
		run = false;
	}
	
	public static void refresh() {
		System.out.println("refreshing...");
		for (File file : toAlignDir.listFiles()) {
			String name = file.getName().substring(0, file.getName().lastIndexOf('.'));
			if (!fileNamesToAlign.contains(name)) {
				fileNamesToAlign.add(name);
				System.out.println("new file found:" + name);
			}
		}
	}

}
