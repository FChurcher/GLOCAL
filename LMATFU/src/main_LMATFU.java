import model.Job;
import model.JobBuilder;

public class main_LMATFU {
	
	public static void main(String[] args) {
		System.out.println("asd");
		Job j = JobBuilder.buildJob("wait 10");
		
		System.out.println(j.isDone());
		System.out.println(j.isDone());
		System.out.println(j.isDone());
		System.out.println(j.isDone());
		System.out.println(j.isDone());
	}
}
