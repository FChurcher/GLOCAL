import java.io.File;

import controller.Measurer;
import io.FileCollector;
import io.Reader;
import io.Writer;
import model.Alignment;

public class Main_MEASURING {
	public static void main(String[] args) {
		
//		Alignment a, b;
//		a = Reader.readFasta("test1.fasta");
//		b = Reader.readFasta("test2.fasta");
//		System.out.println(Measurer.acW(a, b));
//		System.out.println(Measurer.pse(a, b));
		
		
		Writer.openWriters();
		String path = args[0];
		Alignment test = null, ref = null;
		String alignmentFilePath = "";
		for (String alignmentName : FileCollector.getAlignmentNames(new File(path))) {
			test = null;
			ref = null;
			Writer.writeAc(alignmentName + "\t");
			Writer.writePse(alignmentName + "\t");
			
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
			if (ref == null) {
				Writer.writeAc("no ref file\t");
				Writer.writePse("no ref file\t");
				//continue;
			}
			
			test = null;
			alignmentFilePath = path + alignmentName + ".glocal";
			if (new File(alignmentFilePath).exists()) {
				System.out.println(alignmentFilePath);
				test = Reader.readGLOCAL(alignmentFilePath);
			}
			if (test != null && ref != null) {
				Writer.writeAc(Measurer.acW(ref, test) + "\t");
				Writer.writePse(Measurer.pse(ref, test) + "\t");
			} else {
				Writer.writeAc("nf\t");
				Writer.writePse("nf\t");
			}
				
			test = null;
			alignmentFilePath = path + alignmentName + ".tcoffe";
			System.out.println(alignmentFilePath);
			if (new File(alignmentFilePath).exists()) {
				System.out.println(alignmentFilePath);
				test = Reader.readFasta(alignmentFilePath);
			}
			if (test != null && ref != null) {
				Writer.writeAc(Measurer.acW(ref, test) + "\t");
				Writer.writePse(Measurer.pse(ref, test) + "\t");
			} else {
				Writer.writeAc("nf\t");
				Writer.writePse("nf\t");
			}
			
			test = null;
			alignmentFilePath = path + alignmentName + ".mafft";
			if (new File(alignmentFilePath).exists()) {
				System.out.println(alignmentFilePath);
				test = Reader.readFasta(alignmentFilePath);
			}
			if (test != null && ref != null) {
				Writer.writeAc(Measurer.acW(ref, test) + "\t");
				Writer.writePse(Measurer.pse(ref, test) + "\t");
			} else {
				Writer.writeAc("nf\t");
				Writer.writePse("nf\t");
			}
			
			test = null;
			alignmentFilePath = path + alignmentName + ".clustalw";
			if (new File(alignmentFilePath).exists()) {
				System.out.println(alignmentFilePath);
				test = Reader.readFasta(alignmentFilePath);
			}
			if (test != null && ref != null) {
				Writer.writeAc(Measurer.acW(ref, test) + "\t");
				Writer.writePse(Measurer.pse(ref, test) + "\t");
			} else {
				Writer.writeAc("nf\t");
				Writer.writePse("nf\t");
			}
			Writer.writeAc("\n");
			Writer.writePse("\n");
		}
		Writer.closeWriters();
	}
}
