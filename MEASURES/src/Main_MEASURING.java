import controller.Measurer;
import io.Reader;
import model.Alignment;
import model.Sequence;

public class Main_MEASURING {
	public static void main(String[] args) {
		Alignment a1, a2;
		
		a1 = Reader.readGLOCAL(args[0]);
		for (Sequence sequence : a1.getSequences()) {
			System.out.println(sequence.getName() + "\t|" + sequence.getSequence() + "|");
		}
		for (Sequence sequence : a1.getAlignedSequences()) {
			System.out.println(sequence.getName() + "\t|" + sequence.getSequence() + "|");
		}
		
		System.out.println();
		
		a2 = Reader.readFasta(args[1]);
		for (Sequence sequence : a2.getSequences()) {
			System.out.println(sequence.getName() + "\t|" + sequence.getSequence() + "|");
		}
		for (Sequence sequence : a2.getAlignedSequences()) {
			System.out.println(sequence.getName() + "\t|" + sequence.getSequence() + "|");
		}
		
		Measurer.pse(a1, a2);
		Measurer.acW(a1, a2);
	}
}
