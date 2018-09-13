
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import controller.Aligner;
import controller.Settings;
import io.Reader;
import io.Writer;
import model.Alignment;
import model.Sequence;
import ui.TimeStampMaganer;

public class Main_Alignments {

	public static void main(String[] args) {
//		try {
//			System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("log.log"))));
//		} catch (FileNotFoundException e) { e.printStackTrace(); }
		
		TimeStampMaganer.getInstance().printGuide();
		TimeStampMaganer.getInstance().printTimeStamp("reading input files...");
		Sequence[] sequences = Settings.init("data\\sequences.fasta", "data\\locality.loc", "data\\AGCT.sco", "data\\codes.cod");
		for (Sequence sequence : sequences) {
			System.out.println(sequence);
		}
		
		TimeStampMaganer.getInstance().printTimeStamp("aligning...");
		Alignment a = Aligner.getInstance().align(sequences);
		System.out.println(a);
		
		System.out.print("writing files... ");
		String writername = "alignment";
		Writer.registerWriter(Writer.DIR_NAME_OUTPUT, writername);
		Writer.write(writername, Settings.printToString() + "\n\n");
		Writer.write(writername, a.toString() + "\n");
		//Writer.write(writername, a.getHasseGraph().toLongString());
		Writer.closeAll();
		
		System.out.println("done.");
	}
}
