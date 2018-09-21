import model.Job;

import java.io.File;

import controller.AlignmentStarter;
import controller.JobBuilder;
import io.FileCollector;

public class main_LMATFU {
	
	public static void main(String[] args) {
		FileCollector.start();
		AlignmentStarter.startGlocal(FileCollector.fileNamesToAlign.get(0));
	}
}
