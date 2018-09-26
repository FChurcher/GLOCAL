package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
	public static final String acPath = "LMATFU" + File.separator + "to_compare" + File.separator + "ac.txt";
	public static final String psePath = "LMATFU" + File.separator + "to_compare" + File.separator + "pse.txt";
	
	private static BufferedWriter acWriter;
	private static BufferedWriter pseWriter;
	
	
	public static void openWriters() {
		try {
			acWriter = new BufferedWriter(new FileWriter(acPath, true));
			pseWriter = new BufferedWriter(new FileWriter(psePath, true));
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public static void closeWriters() {
		try {
			acWriter.close();
			pseWriter.close();
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public static void writeAc(String text) {
		try {
			acWriter.write(text);
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public static void writePse(String text) {
		try {
			pseWriter.write(text);
		} catch (IOException e) { e.printStackTrace(); }
	}

}
