package io;

import java.io.File;
import java.util.ArrayList;


public class FileCollector {
	public static final File toAlignDir = new File("LMATFU" + File.separator + "to_compare");
	
	public static ArrayList<String> getAlignmentNames() {
		// get all alignment names
		ArrayList<String> alignmentNames = new ArrayList<>();
		for (File file : toAlignDir.listFiles()) {
			String name = file.getName().substring(0, file.getName().lastIndexOf('.'));
			if (!alignmentNames.contains(name)) {
				alignmentNames.add(name);
			}
		}
		return alignmentNames;
	}

}
