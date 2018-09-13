package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Writer {
	public static final String DIR_NAME_OUTPUT = "out/";
	public static final String DIR_NAME_DEBUG = "out/debug/";
	
	private static HashMap<String, BufferedWriter> writers = new HashMap<String, BufferedWriter>();
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	
	public static void registerWriter(String dir, String name) {
		if (writers.containsKey(name)) { return; }
		Date date = new Date();
		String filename = dir + "/" + name + "_" + Writer.dateFormat.format(date).toString() + ".txt";
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
			writers.put(name, writer);
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public static void write(String name, String text) {
		try {
			writers.get(name).write(text);
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public static void closeAll() {
		for (BufferedWriter writer : writers.values()) {
			try {
				writer.close();
			} catch (IOException e) { e.printStackTrace(); }
		}
	}
	
	
}
