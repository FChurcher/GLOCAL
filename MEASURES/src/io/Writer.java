package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
	public static final String path = "LMATFU" + File.separator + "to_compare" + File.separator + "measures.txt";
	
	private static BufferedWriter writer;
	
	
	public static void openWriter() {
		try {
			writer = new BufferedWriter(new FileWriter(path, true));
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public static void closeWriter() {
		try {
			writer.close();
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public static void write(String text) {
		try {
			writer.write(text);
		} catch (IOException e) { e.printStackTrace(); }
	}

}
