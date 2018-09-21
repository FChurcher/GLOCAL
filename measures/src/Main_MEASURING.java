import controller.Measurer;
import io.Reader;
import model.Alignment;
import model.Sequence;

public class Main_MEASURING {
	public static void main(String[] args) {
		Alignment a1, a2;
		
		a1 = Reader.readGLOCAL("..\\_share\\results\\4way\\4t2_2018_09_19_15_22_23.txt");
		for (Sequence sequence : a1.getSequences()) {
			System.out.println(sequence.getName() + "\t|" + sequence.getSequence() + "|");
		}
		for (Sequence sequence : a1.getAlignedSequences()) {
			System.out.println(sequence.getName() + "\t|" + sequence.getSequence() + "|");
		}
		
		System.out.println();
		
		a2 = Reader.readFasta("..\\_share\\results_oxbench\\4t2");
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
