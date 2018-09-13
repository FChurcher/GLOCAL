
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

public class Main_Alignments {

	public static void main(String[] args) {
//		try {
//			System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("log.log"))));
//		} catch (FileNotFoundException e) { e.printStackTrace(); }
		
		
		Sequence[] sequences = Settings.init("data\\test.fasta", "data\\locality.txt", "data\\AGCT.txt", "data\\codes.txt");
		for (Sequence sequence : sequences) {
			//System.out.println(sequence);
		}
		Alignment a = Aligner.getInstance().align(sequences);

		
		//System.out.println(a);
		
		
		String writername = "alignment";
		Writer.registerWriter(Writer.DIR_NAME_OUTPUT, writername);
		Writer.write(writername, Settings.printToString() + "\n\n");
		Writer.write(writername, a.toString() + "\n");
		Writer.write(writername, a.getHasseGraph().toLongString());
		Writer.closeAll();
		
		//System.out.println(Settings.printToString());
		
		//sequences = Reader.readLocality("data\\locality.txt", Reader.readSequences("data\\sequences.fasta"));
	}
}
