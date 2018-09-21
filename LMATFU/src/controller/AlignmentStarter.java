package controller;

import java.io.File;

import model.AlignmentJobGroup;
import model.Job;

public class AlignmentStarter {
	
	public static AlignmentJobGroup startAll(String fileName) {
		return new AlignmentJobGroup(startGlocal(fileName), startT_coffee(fileName), startMafft(fileName), startClustalw(fileName));
	}
	
	public static Job startGlocal(String fileName) {
		System.out.println("starting GLOCAL");
		return JobBuilder.buildJob("java -Xms50G -Xmx200G -jar GLOCAL.jar " + "LMATFU" + File.separator + "to_align" + File.separator + fileName, null);
	}
	
	public static Job startT_coffee(String fileName) {
		System.out.println("starting t_coffee");
		return JobBuilder.buildJob("bash -c t_coffee " + "LMATFU" + File.separator + "to_align" + File.separator + fileName + ".fasta –inorder=input -output=fasta_aln\\ &&\\ mv " + fileName + ".fasta_aln " + "LMATFU" + File.separator + "aligned" + File.separator +  fileName + ".aln.t_coffee", null);
	}
	
	public static Job startMafft(String fileName) {
		System.out.println("starting mafft");
		return JobBuilder.buildJob("mafft --maxiterate 1000 --globalpair " + "LMATFU" + File.separator + "to_align" + File.separator + fileName+".fasta", "LMATFU" + File.separator + "aligned" + File.separator +  fileName + ".aln.mafft");
	}
	
	public static Job startClustalw(String fileName) {
		System.out.println("starting clustalw");
		return JobBuilder.buildJob("clustalw -infile=" + "LMATFU" + File.separator + "to_align" + File.separator + fileName + ".fasta" + " -matrix=Blosum -OUTORDER=INPUT -OUTFILE=" + "LMATFU" + File.separator + "aligned" + File.separator +  fileName + ".aln.clustalw", null);
	}

}
 