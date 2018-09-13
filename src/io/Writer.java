package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * a static Class to write output files
 * @author Falco
 */
public class Writer {
  // static values an methods
	public static final String DIR_NAME_OUTPUT = "out/";
	public static final String DIR_NAME_DEBUG = "out/debug/";
	
	/** stores all the writers */
	private static HashMap<String, BufferedWriter> writers = new HashMap<String, BufferedWriter>();
	/** holds the date format */
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	
	/**
	 * adds a new writer to the writers list
	 * @param dir - the dir where the writer may writes
	 * @param name - the name of the writer = name of the file
	 */
	public static void registerWriter(String dir, String name) {
		if (writers.containsKey(name)) { return; }
		Date date = new Date();
		String filename = dir + "/" + name + "_" + Writer.dateFormat.format(date).toString() + ".txt";
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
			writers.put(name, writer);
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	/**
	 * writes the text to the file
	 * @param name name of the writer = name of the file
	 * @param text the text to write
	 */
	public static void write(String name, String text) {
		try {
			writers.get(name).write(text);
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	/** closes all the writes in the writers list */
	public static void closeAll() {
		for (BufferedWriter writer : writers.values()) {
			try {
				writer.close();
			} catch (IOException e) { e.printStackTrace(); }
		}
	}
	
	
}
