
import controller.Aligner;
import controller.Settings;
import io.Writer;
import model.Alignment;
import model.Sequence;
import ui.ArgsParser;
import ui.TimeStampMaganer;

public class Main_GLOCAL {

	public static void main(String[] args) {
		TimeStampMaganer.getInstance().printGuide();
		TimeStampMaganer.getInstance().printTimeStamp("reading input files...");
		Sequence[] sequences = Settings.init("t4long");
		//Sequence[] sequences = ArgsParser.getInstance().initWithArgs(args);
		for (Sequence sequence : sequences) {
			System.out.println(sequence);
		}
		
		TimeStampMaganer.getInstance().printTimeStamp("aligning...");
		Alignment a = Aligner.getInstance().align(sequences);
		
		TimeStampMaganer.getInstance().printTimeStamp("writing files...");
		String writername = Settings.name;
		Writer.registerWriter(Writer.DIR_NAME_OUTPUT, writername);
		Writer.write(writername, "took " + TimeStampMaganer.getInstance().getActualRunTime() + " seconds\n\n");
		Writer.write(writername, Settings.printToString() + "\n\n");
		Writer.write(writername, a.toString() + "\n");
		//Writer.write(writername, a.getHasseGraph().toLongString());
		Writer.closeAll();
		TimeStampMaganer.getInstance().printTimeStamp("done");
	}
}
