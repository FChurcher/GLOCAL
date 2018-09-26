package controller;

import java.util.ArrayList;

import model.Alignment;
import model.Sequence;

public class Measurer {
	
	public static float acW(Alignment refAlignment, Alignment testAlignment) {
//		System.out.println("\nacW");
		int[] refIndices = new int[refAlignment.getSequences().size()];
		int[] testIndices = new int[testAlignment.getSequences().size()];
		int falsly = 0;
		ArrayList<int[]> refindicesList = new ArrayList<>();
		ArrayList<int[]> testindicesList = new ArrayList<>();
		
		for (int i = 0; i < refIndices.length; i++) {
			// startpositionen finden
			refIndices[i] = refAlignment.getSequences().get(i).getSequence().lastIndexOf(refAlignment.getAlignedSequences().get(i).getSequence().replaceAll("-", "").replaceAll(" ", ""));
			testIndices[i] = testAlignment.getSequences().get(i).getSequence().lastIndexOf(testAlignment.getAlignedSequences().get(i).getSequence().replaceAll("-", "").replaceAll(" ", ""));
		}
		
		// safe aligned posisions for all positions of all sequences
		for (int j = 0; j < Math.min(refAlignment.getAlignedSequences().get(0).getSequence().length(), testAlignment.getAlignedSequences().get(0).getSequence().length()); j++) {
			// one incremetation step
			for (int i = 0; i < refIndices.length; i++) {
				if (refAlignment.getAlignedSequences().get(i).getSequence().charAt(j) != '-' && refAlignment.getAlignedSequences().get(i).getSequence().charAt(j) != ' ') {
					refIndices[i] += 1;
				}
				if (testAlignment.getAlignedSequences().get(i).getSequence().charAt(j) != '-' && testAlignment.getAlignedSequences().get(i).getSequence().charAt(j) != ' ') {
					testIndices[i] += 1; 
				}
			}
			refindicesList.add(refIndices.clone());
			testindicesList.add(testIndices.clone());
		}
		
		// count correctly aligned sequences
		for (int i = 0; i < refindicesList.size(); i++) {										// for each position of alignment
			int[] ref = refindicesList.get(i);													// reference row at alignment position 1
			for (int r = 0; r < ref.length-1; r++) {
				if ((i == 0 || ref[r] != refindicesList.get(i-1)[r]) && ref[r] != 0) {
					int[] test = null;
					int tes = -1;
					for (int j = 0; j < testindicesList.size(); j++) {							// find ref position in test alignment
						if (testindicesList.get(j)[r] == ref[r]) {
							test = testindicesList.get(j);
							tes = j;
							break;
						}
					}
					if (test != null) {
						for (int t = r+1; t < test.length; t++) {					// for each pair of sequences
//							System.out.println("::" + r + " " + t);
							if (ref[t] != test[t]) {
//								char refChar = refAlignment.getAlignedSequences().get(r).getSequence().charAt(i);
//								char testChar = testAlignment.getAlignedSequences().get(t).getSequence().charAt(tes);
//								char reftestChar = refAlignment.getAlignedSequences().get(t).getSequence().charAt(i);
//								char testrefChar = testAlignment.getAlignedSequences().get(r).getSequence().charAt(tes);
//								if (refChar == ' ' || refChar == '-' || testChar == ' ' || testChar == '-' || reftestChar == ' ' || reftestChar == '-' || testrefChar == ' ' || testrefChar == '-') {
//									continue;
//								}
//								System.out.println(i + ":" + r + " " + tes + ":" + t);
//								System.out.println(ref[t] + " " + test[t]);
//								System.out.println(refChar + " " + testChar);
								falsly++;
							}
						}
					}
				}
			}
		}
		int positions = ((((refAlignment.getSequences().size()-1) * refAlignment.getSequences().size())/2) * refAlignment.getAlignedSequences().get(0).getSequence().length());
		System.out.println("falsly: " + falsly + " of " + positions);
		float acW = 100 * (float)(positions-falsly) / (float)positions;
//		System.out.println("ACw: " + acW + " %");
		return acW;
		
	}
	
	public static float pse(Alignment refAlignment, Alignment testAlignment) {
//		System.out.println("\npse");
		float pseP, pse = 0;
		for (int i = 0; i < refAlignment.getSequences().size()-1; i++) {
			for (int j = i+1; j < refAlignment.getSequences().size(); j++) {
				pseP = 0;
				Sequence refseq1 = refAlignment.getAlignedSequences().get(i);
				Sequence refseq2 = refAlignment.getAlignedSequences().get(j);
				Sequence testseq1 = testAlignment.getAlignedSequences().get(i);
				Sequence testseq2 = testAlignment.getAlignedSequences().get(j);
				
				ArrayList<Float> refseq1Counts = new ArrayList<>();
				ArrayList<Float> refseq2Counts = new ArrayList<>();
				ArrayList<Float> testseq1Counts = new ArrayList<>();
				ArrayList<Float> testseq2Counts = new ArrayList<>();
				
				float c = 0;
				for (int k = 0; k < refseq1.getSequence().length(); k++) {
					if (refseq1.getSequence().charAt(k) == '-' || refseq1.getSequence().charAt(k) == ' ') {
						c = (float) ((int)c + 0.5);
					} else {
						c = (float) ((int)c + 1);
					}
					refseq1Counts.add(c);
				}
				
				c = 0;
				for (int k = 0; k < refseq2.getSequence().length(); k++) {
					if (refseq2.getSequence().charAt(k) == '-' || refseq2.getSequence().charAt(k) == ' ') {
						c = (float) ((int)c + 0.5);
					} else {
						c = (float) ((int)c + 1);
					}
					refseq2Counts.add(c);
				}
				
				c = 0;
				for (int k = 0; k < testseq1.getSequence().length(); k++) {
					if (testseq1.getSequence().charAt(k) == '-' || testseq1.getSequence().charAt(k) == ' ') {
						c = (float) ((int)c + 0.5);
					} else {
						c = (float) ((int)c + 1);
					}
					testseq1Counts.add(c);
				}
				
				c = 0;
				for (int k = 0; k < testseq2.getSequence().length(); k++) {
					if (testseq2.getSequence().charAt(k) == '-' || testseq2.getSequence().charAt(k) == ' ') {
						c = (float) ((int)c + 0.5);
					} else {
						c = (float) ((int)c + 1);
					}
					testseq2Counts.add(c);
				}
				
				float errorSum = 0;
				for (int k = 0; k < refseq1Counts.size(); k++) {
					if (refseq1.getSequence().charAt(k) != '-' && refseq1.getSequence().charAt(k) != ' ' && testseq1Counts.contains(refseq1Counts.get(k))) {
						errorSum += (Math.abs(refseq2Counts.get(k) - testseq2Counts.get(testseq1Counts.indexOf(refseq1Counts.get(k)))));
//						System.out.println(Math.abs(refseq2Counts.get(k) - testseq2Counts.get(testseq1Counts.indexOf(refseq1Counts.get(k)))) + "=" + refseq2Counts.get(k) + "-" + testseq2Counts.get(testseq1Counts.indexOf(refseq1Counts.get(k))));
					}
				}
				pseP = errorSum/refAlignment.getAlignedSequences().get(0).getSequence().length();
				pse += pseP;
//				System.out.println("SUM: " + errorSum);
//				System.out.println(pseP);
//				
//				System.out.println();
//				for (float f : refseq1Counts) {
//					System.out.print(f + "\t");
//				}
//				System.out.println();
//				for (char charr : refseq1.getSequence().toCharArray()) {
//					System.out.print(charr + "\t");
//				}
//				System.out.println();
//				for (char charr : refseq2.getSequence().toCharArray()) {
//					System.out.print(charr + "\t");
//				}
//				System.out.println();
//				for (float f : refseq2Counts) {
//					System.out.print(f + "\t");
//				}
//				System.out.println();
//				System.out.println();
//				for (float f : testseq1Counts) {
//					System.out.print(f + "\t");
//				}
//				System.out.println();
//				for (char charr : testseq1.getSequence().toCharArray()) {
//					System.out.print(charr + "\t");
//				}
//				System.out.println();
//				for (char charr : testseq2.getSequence().toCharArray()) {
//					System.out.print(charr + "\t");
//				}
//				System.out.println();
//				for (float f : testseq2Counts) {
//					System.out.print(f + "\t");
//				}
//				System.out.println();
			}
		}
		int n = refAlignment.getSequences().size();
//		pse = 100 * pse / (float)(((float)n*(float)(n-1f)/2f));
//		System.out.println("PSE: " + pse + "%");
		return pse;
	}

}
