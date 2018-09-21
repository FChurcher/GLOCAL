package controller;

import java.io.File;
import java.io.IOException;

import model.Job;

public class JobBuilder {
	public static ProcessBuilder pb = new ProcessBuilder();
	
	public static Job buildJob(String command) {
		System.out.println("executing:" + command);
		pb.command(command.split(" "));
		try {
			Process process = pb.start();
			return new Job(process);
		} catch (IOException e) { e.printStackTrace(); }
		return null;
	}
}
