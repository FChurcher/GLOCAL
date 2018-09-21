package io;

import java.io.File;
import java.util.ArrayList;

public class FileExplorer {
	public static final File toAlignDir = new File(File.separator + "LMATFU" + File.separator + "to_align");
	
	public static ArrayList<String> fileNamesToAlign = new ArrayList<>();
	
	public static void refresh() {
		System.out.println(toAlignDir);
		for (File file : toAlignDir.listFiles()) {
			String name = file.getName().substring(0, file.getName().lastIndexOf('.'));
			if (!fileNamesToAlign.contains(name)) {
				fileNamesToAlign.add(name);
				System.out.println(name);
			}
		}
		System.out.println("done");
	}

}
