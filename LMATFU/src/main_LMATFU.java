import model.Job;
import controller.JobBuilder;
import io.FileExplorer;

public class main_LMATFU {
	
	public static void main(String[] args) {
		System.out.println("asd");
		FileExplorer.refresh();
		Job j = JobBuilder.buildJob("touch" + FileExplorer.toAlignDir + "add.fasta");
		FileExplorer.refresh();
		Job j = JobBuilder.buildJob("sleep 10");
	}
}
