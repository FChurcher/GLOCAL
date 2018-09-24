package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.AlignmentJobGroup;

public class TimesWriter {
	public static final String path = "LMATFU" + File.separator + "aligned" + File.separator + "times.txt";
	
	private static BufferedWriter writer;
	
	
	public static void writeTimes(AlignmentJobGroup alignmentJobGroup) {
		openWriter();
		write(alignmentJobGroup.getName() + "\t");
		if (alignmentJobGroup.getGlocal().getExitValue() == 0) {
			write(alignmentJobGroup.getGlocal().getDuration() + "\t");
		} else {
			write("err" + "\t");
		}
		
		if (alignmentJobGroup.getT_coffee().getExitValue() == 0) {
			write(alignmentJobGroup.getT_coffee().getDuration() + "\t");
		} else {
			write("err" + "\t");
		}
		
		if (alignmentJobGroup.getMafft().getExitValue() == 0) {
			write(alignmentJobGroup.getMafft().getDuration() + "\t");
		} else {
			write("err" + "\t");
		}
		
		if (alignmentJobGroup.getClustalw().getExitValue() == 0) {
			write(alignmentJobGroup.getClustalw().getDuration() + "\n");
		} else {
			write("err" + "\n");
		}
		closeWriter();
	}
	
	private static void openWriter() {
		try {
			writer = new BufferedWriter(new FileWriter(path, true));
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	private static void closeWriter() {
		try {
			writer.close();
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	private static void write(String text) {
		try {
			writer.write(text);
		} catch (IOException e) { e.printStackTrace(); }
	}

}
