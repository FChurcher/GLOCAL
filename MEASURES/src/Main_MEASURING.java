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
		for (String alignmentName : FileCollector.getAlignmentNames(new File(args[1]))) {
			Writer.write(alignmentName + "\t");
			alignmentFilePath = path + File.separator + alignmentName + ".aln.glocal";
			if (new File(alignmentFilePath).exists()) {
				test = Reader.readGLOCAL(alignmentFilePath);
			}
			
			alignmentFilePath = path + File.separator + alignmentName + ".aln.ox";
			if (new File(alignmentFilePath).exists()) {
				ref = Reader.readFasta(alignmentFilePath);
			} else {
				alignmentFilePath = path + File.separator + alignmentName + ".aln.bb";
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
			alignmentFilePath = path + File.separator + alignmentName + ".aln.tcoffee";
			if (new File(alignmentFilePath).exists()) {
				ref = Reader.readFasta(alignmentFilePath);
			}
			if (test != null && ref != null) {
				Writer.write(Measurer.acW(ref, test) + " " + Measurer.pse(ref, test) + "\t");
			} else {
				Writer.write("no file\t");
			}
			
			ref = null;
			alignmentFilePath = path + File.separator + alignmentName + ".aln.mafft";
			if (new File(alignmentFilePath).exists()) {
				ref = Reader.readFasta(alignmentFilePath);
			}
			if (test != null && ref != null) {
				Writer.write(Measurer.acW(ref, test) + " " + Measurer.pse(ref, test) + "\t");
			} else {
				Writer.write("no file\t");
			}
			
			ref = null;
			alignmentFilePath = path + File.separator + alignmentName + ".aln.clustalw";
			if (new File(alignmentFilePath).exists()) {
				ref = Reader.readFasta(alignmentFilePath);
			}
			if (test != null && ref != null) {
				Writer.write(Measurer.acW(ref, test) + " " + Measurer.pse(ref, test) + "\t");
			} else {
				Writer.write("no file\t");
			}
		}
		Writer.closeWriter();
	}
}
