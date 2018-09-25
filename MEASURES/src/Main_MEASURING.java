import java.io.File;

import controller.Measurer;
import io.FileCollector;
import io.Reader;
import io.Writer;
import model.Alignment;

public class Main_MEASURING {
	public static void main(String[] args) {
		Writer.openWriter();
		String path = args[0];
		Alignment test = null, ref = null;
		String alignmentFilePath = "";
		for (String alignmentName : FileCollector.getAlignmentNames(new File(path))) {
			
			Writer.write(alignmentName + "\t");
			alignmentFilePath = path + alignmentName + ".glocal";
			if (new File(alignmentFilePath).exists()) {
				System.out.println(alignmentFilePath);
				test = Reader.readGLOCAL(alignmentFilePath);
			}
			
			alignmentFilePath = path + alignmentName + ".ox";
			if (new File(alignmentFilePath).exists()) {
				System.out.println(alignmentFilePath);
				ref = Reader.readFasta(alignmentFilePath);
			} else {
				alignmentFilePath = path + alignmentName + ".bb";
				if (new File(alignmentFilePath).exists()) {
					System.out.println(alignmentFilePath);
					ref = Reader.readFasta(alignmentFilePath);
				}
			}
			if (test != null && ref != null) {
				Writer.write(Measurer.acW(ref, test) + " " + Measurer.pse(ref, test) + "\t");
			} else {
				Writer.write("no file\t");
			}
			
			ref = null;
			alignmentFilePath = path + alignmentName + ".tcoffee";
			if (new File(alignmentFilePath).exists()) {
				System.out.println(alignmentFilePath);
				ref = Reader.readFasta(alignmentFilePath);
			}
			if (test != null && ref != null) {
				Writer.write(Measurer.acW(ref, test) + " " + Measurer.pse(ref, test) + "\t");
			} else {
				Writer.write("no file\t");
			}
			
			ref = null;
			alignmentFilePath = path + alignmentName + ".mafft";
			if (new File(alignmentFilePath).exists()) {
				System.out.println(alignmentFilePath);
				ref = Reader.readFasta(alignmentFilePath);
			}
			if (test != null && ref != null) {
				Writer.write(Measurer.acW(ref, test) + " " + Measurer.pse(ref, test) + "\t");
			} else {
				Writer.write("no file\t");
			}
			
			ref = null;
			alignmentFilePath = path + alignmentName + ".clustalw";
			if (new File(alignmentFilePath).exists()) {
				System.out.println(alignmentFilePath);
				ref = Reader.readFasta(alignmentFilePath);
			}
			if (test != null && ref != null) {
				Writer.write(Measurer.acW(ref, test) + " " + Measurer.pse(ref, test) + "\t");
			} else {
				Writer.write("no file\t");
			}
			Writer.write("\n");
		}
		Writer.closeWriter();
	}
}
