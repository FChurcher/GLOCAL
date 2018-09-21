import model.Job;

import java.io.File;

import controller.JobBuilder;
import io.FileExplorer;

public class main_LMATFU {
	
	public static void main(String[] args) {
		System.out.println("asd");
		FileExplorer.start();
		Job j = JobBuilder.buildJob("touch " + FileExplorer.toAlignDir + File.separator + "add.fasta");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
