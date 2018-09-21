package model;

import java.io.File;
import java.io.IOException;

public class JobBuilder {
	public static ProcessBuilder builder = new ProcessBuilder();
	
	public static Job buildJob(String dir, String command) {
		Runtime rt = Runtime.getRuntime();
		try {
			Process process = rt.exec("mkdir a");
		} catch (IOException e) { e.printStackTrace(); }
		return null;
	}
}
