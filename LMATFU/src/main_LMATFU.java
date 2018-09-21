import model.Job;

import java.io.File;

import controller.JobBuilder;
import io.FileExplorer;

public class main_LMATFU {
	
	public static void main(String[] args) {
		FileExplorer.start();
		Job j = JobBuilder.buildJob("touch " + FileExplorer.toAlignDir + File.separator + "add.fasta");
	}
}
