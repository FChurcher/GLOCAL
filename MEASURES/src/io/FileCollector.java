package io;

import java.io.File;
import java.util.ArrayList;


public class FileCollector {
	
	public static ArrayList<String> getAlignmentNames(File toAlignDir) {
		// get all alignment names
		ArrayList<String> alignmentNames = new ArrayList<>();
		for (File file : toAlignDir.listFiles()) {
			if (file.isDirectory()) {continue;}
			String name = file.getName().substring(0, file.getName().lastIndexOf('.'));
			if (name.contains(".aln") && !alignmentNames.contains(name)) {
				alignmentNames.add(name);
			}
		}
		return alignmentNames;
	}

}
