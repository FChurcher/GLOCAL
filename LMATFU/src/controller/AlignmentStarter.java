package controller;

import java.io.File;

import model.Job;

public class AlignmentStarter {
	
	public static void startGlocal(String fileName) {
		System.out.println("starting GLOCAL");
		Job j = JobBuilder.buildJob("java -Xmx50G -Xms200G -jar GLOCAL.jar " + "LMATFU" + File.separator + "to_align" + File.separator + fileName);
	}

}
