package controller;

import java.util.ArrayList;

import model.Alignment;
import model.Sequence;

public class Measurer {
	
	public static void acW(Alignment refAlignment, Alignment testAlignment) {
		System.out.println("\nacW");
		int[] refIndices = new int[refAlignment.getSequences().size()];
		int[] testIndices = new int[testAlignment.getSequences().size()];
		int correctlyAlignedPositionCount = 0;
		ArrayList<int[]> refindicesList = new ArrayList<>();
		ArrayList<int[]> testindicesList = new ArrayList<>();
		
		for (int i = 0; i < refIndices.length; i++) {
			refIndices[i] = refAlignment.getSequences().get(i).getSequence().lastIndexOf(refAlignment.getAlignedSequences().get(i).getSequence().replaceAll("-", ""));
			testIndices[i] = testAlignment.getSequences().get(i).getSequence().lastIndexOf(testAlignment.getAlignedSequences().get(i).getSequence().replaceAll("-", ""));
		}
		
		
		for (int j = 0; j < Math.min(refAlignment.getSequences().get(0).getSequence().length(), testAlignment.getSequences().get(0).getSequence().length()); j++) {
			// one incremetation step
			for (int i = 0; i < refIndices.length; i++) {
				if (refAlignment.getAlignedSequences().get(i).getSequence().charAt(j) != '-') {
					refIndices[i] += 1;
				}
				if (testAlignment.getAlignedSequences().get(i).getSequence().charAt(j) != '-') {
					testIndices[i] += 1; 
				}
			}
			refindicesList.add(refIndices.clone());
			testindicesList.add(testIndices.clone());
		}
		
		for (int i = 0; i < refindicesList.size(); i++) {
			int[] ref = refindicesList.get(i);
			if (i == 0 || ref[0] != refindicesList.get(i-1)[0]) {		// did reference sequence make a step?
				if (ref[0] != 0) {										// skip starting gaps
					int[] test = null;
					for (int j = 0; j < testindicesList.size(); j++) {
						if (testindicesList.get(i)[0] == ref[0]) {
							test = testindicesList.get(i);
							break;
						}
					}
					if (test != null) {
						boolean same = true;
						for (int j = 1; j < ref.length; j++) {
							if (ref[j] != test[j]) {
								same = false;
								break;
							}
						}
						if (same) {
							correctlyAlignedPositionCount++;
						}
					}
				}
			}
		}
		System.out.println("correctlyAlignedPositionCount: " + correctlyAlignedPositionCount);
		float acW = 100 * (float)correctlyAlignedPositionCount/refAlignment.getAlignedSequences().get(0).getSequence().length();
		System.out.println("ACw: " + acW);
		
	}
	
	public static void pse(Alignment refAlignment, Alignment testAlignment) {
		System.out.println("\npse");
		for (int i = 0; i < refAlignment.getSequences().size()-1; i++) {
			for (int j = i+1; j < refAlignment.getSequences().size(); j++) {
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
					if (refseq1.getSequence().charAt(k) == '-') {
						c = (float) ((int)c + 0.5);
					} else {
						c = (float) ((int)c + 1);
					}
					refseq1Counts.add(c);
				}
				
				c = 0;
				for (int k = 0; k < refseq2.getSequence().length(); k++) {
					if (refseq2.getSequence().charAt(k) == '-') {
						c = (float) ((int)c + 0.5);
					} else {
						c = (float) ((int)c + 1);
					}
					refseq2Counts.add(c);
				}
				
				c = 0;
				for (int k = 0; k < testseq1.getSequence().length(); k++) {
					if (testseq1.getSequence().charAt(k) == '-') {
						c = (float) ((int)c + 0.5);
					} else {
						c = (float) ((int)c + 1);
					}
					testseq1Counts.add(c);
				}
				
				c = 0;
				for (int k = 0; k < testseq2.getSequence().length(); k++) {
					if (testseq2.getSequence().charAt(k) == '-') {
						c = (float) ((int)c + 0.5);
					} else {
						c = (float) ((int)c + 1);
					}
					testseq2Counts.add(c);
				}
				
				float errorSum = 0;
				for (int k = 0; k < refseq1Counts.size(); k++) {
					if (refseq2.getSequence().charAt(k) != '-') {
						errorSum += (Math.abs(k - testseq1Counts.indexOf(refseq1Counts.get(k))));
						System.out.println(Math.abs(k - testseq1Counts.indexOf(refseq1Counts.get(k))) + "=" + k + "-" + testseq1Counts.indexOf(refseq1Counts.get(k)));
					}
				}
				System.out.println("SUM: " + errorSum);
				
				System.out.println();
				for (float f : refseq1Counts) {
					System.out.print(f + "\t");
				}
				System.out.println();
				for (char charr : refseq1.getSequence().toCharArray()) {
					System.out.print(charr + "\t");
				}
				System.out.println();
				for (char charr : refseq2.getSequence().toCharArray()) {
					System.out.print(charr + "\t");
				}
				System.out.println();
				for (float f : refseq2Counts) {
					System.out.print(f + "\t");
				}
				System.out.println();
				System.out.println();
				for (float f : testseq1Counts) {
					System.out.print(f + "\t");
				}
				System.out.println();
				for (char charr : testseq1.getSequence().toCharArray()) {
					System.out.print(charr + "\t");
				}
				System.out.println();
				for (char charr : testseq2.getSequence().toCharArray()) {
					System.out.print(charr + "\t");
				}
				System.out.println();
				for (float f : testseq2Counts) {
					System.out.print(f + "\t");
				}
				System.out.println();
			}
		}
	}

}
