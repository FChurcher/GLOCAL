import controller.JobHolder;
import io.FileCollector;
import io.TimesWriter;

public class main_LMATFU {
	
	public static void main(String[] args) {
		TimesWriter.init();
		FileCollector.start();
		JobHolder.start();
	}
}
