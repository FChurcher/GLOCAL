package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.AlignmentJobGroup;

public class TimesWriter {
	public static final String path = "LMATFU" + File.separator + "aligned" + File.separator + "times";
	
	private static BufferedWriter writer;
	
	
	public static void init() {
		try {
			writer = new BufferedWriter(new FileWriter(path, true));
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public static void writeTimes(AlignmentJobGroup alignmentJobGroup) {
		write(alignmentJobGroup.getGlocal().getDuration() + "\t");
		write(alignmentJobGroup.getT_coffee().getDuration() + "\t");
		write(alignmentJobGroup.getMafft().getDuration() + "\t");
		write(alignmentJobGroup.getClustalw().getDuration() + "\n");
	}
	
	private static void write(String text) {
		try {
			writer.write(text);
		} catch (IOException e) { e.printStackTrace(); }
	}

}
