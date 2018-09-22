import controller.JobHolder;
import io.FileCollector;

public class main_LMATFU {
	
	public static void main(String[] args) {
		FileCollector.start();
		JobHolder.start();
	}
}
