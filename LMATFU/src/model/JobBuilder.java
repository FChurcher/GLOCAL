package model;

import java.io.File;
import java.io.IOException;

public class JobBuilder {
	public static Runtime rt = Runtime.getRuntime();
	
	public static Job buildJob(String dir, String command) {
		rt = Runtime.getRuntime();
		try {
			Process process = rt.exec("mkdir a");
			return new Job(process);
		} catch (IOException e) { e.printStackTrace(); }
		return null;
	}
}
