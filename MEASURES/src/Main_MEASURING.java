import java.io.File;

import controller.Measurer;
import io.FileCollector;
import io.Reader;
import io.Writer;
import model.Alignment;

public class Main_MEASURING {
	public static void main(String[] args) {
		Writer.openWriter();
		String path = "LMATFU" + File.separator + "to_compare";
		Alignment test = null, ref = null;
		String alignmentFilePath = "";
		for (String alignmentName : FileCollector.getAlignmentNames(new File(args[0]))) {
			Writer.write(alignmentName + "\t");
			alignmentFilePath = path + File.separator + alignmentName + ".glocal";
			System.out.println(alignmentFilePath);
			if (new File(alignmentFilePath).exists()) {
				test = Reader.readGLOCAL(alignmentFilePath);
			}
			
			alignmentFilePath = path + File.separator + alignmentName + ".ox";
			if (new File(alignmentFilePath).exists()) {
				ref = Reader.readFasta(alignmentFilePath);
			} else {
				alignmentFilePath = path + File.separator + alignmentName + ".bb";
				if (new File(alignmentFilePath).exists()) {
					ref = Reader.readFasta(alignmentFilePath);
				}
			}
			if (test != null && ref != null) {
				Writer.write(Measurer.acW(ref, test) + " " + Measurer.pse(ref, test) + "\t");
			} else {
				Writer.write("no file\t");
			}
			
			ref = null;
			alignmentFilePath = path + File.separator + alignmentName + ".tcoffee";
			if (new File(alignmentFilePath).exists()) {
				ref = Reader.readFasta(alignmentFilePath);
			}
			if (test != null && ref != null) {
				Writer.write(Measurer.acW(ref, test) + " " + Measurer.pse(ref, test) + "\t");
			} else {
				Writer.write("no file\t");
			}
			
			ref = null;
			alignmentFilePath = path + File.separator + alignmentName + ".mafft";
			if (new File(alignmentFilePath).exists()) {
				ref = Reader.readFasta(alignmentFilePath);
			}
			if (test != null && ref != null) {
				Writer.write(Measurer.acW(ref, test) + " " + Measurer.pse(ref, test) + "\t");
			} else {
				Writer.write("no file\t");
			}
			
			ref = null;
			alignmentFilePath = path + File.separator + alignmentName + ".clustalw";
			if (new File(alignmentFilePath).exists()) {
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
