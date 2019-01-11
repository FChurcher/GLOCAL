import controller.JobHolder;
import io.FileCollector;

public class main_LMATFU {
	
	public static void main(String[] args) {
		System.out.println("version 1.1.4");
		FileCollector.start();
		JobHolder.start();
	}
}
