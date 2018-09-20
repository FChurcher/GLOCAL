package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Alignment;
import model.Sequence;


/**
 * a "static" Class to read input files
 * @author Falco
 */
public class Reader {
	// static values an methods
		/** the reader */
		private static BufferedReader reader;
		
		/**
		 * reads the Sequences froma n imput file
		 * @param path - the path of the file to read
		 * @return - the Sequences
		 */
		public static Alignment readFasta(String path){
			try {
				Reader.reader = new BufferedReader(new FileReader(path));
			} catch (FileNotFoundException e) { e.printStackTrace(); }
			
			ArrayList<Sequence> sequences = new  ArrayList<>();
			ArrayList<Sequence> alignedSequences = new  ArrayList<>();
			String sequenceName;
			String sequence;
			
			String line = readLine(";");
			while (line != null) {
				sequenceName = "";
				sequence = "";
				if (line.startsWith(">")) {
					sequenceName = line.substring(1);
					while ((line = readLine(";")) != null && !line.startsWith(">")) {
						sequence += line;
					}
				} else {
					System.out.println("invalid format");
					return null;
				}
				sequences.add(new Sequence(sequence.replace("-", ""), sequenceName));
				alignedSequences.add(new Sequence(sequence, sequenceName));
			}
			return new Alignment(sequences, alignedSequences);
		}
		
		/**
		 * reads the Sequences froma n imput file
		 * @param path - the path of the file to read
		 * @return - the Sequences
		 */
		public static Alignment readGLOCAL(String path){
			try {
				Reader.reader = new BufferedReader(new FileReader(path));
			} catch (FileNotFoundException e) { e.printStackTrace(); }
			
			ArrayList<Sequence> alignedSequences = new  ArrayList<>();
			ArrayList<Sequence> sequences = new  ArrayList<>();
			ArrayList<String> names = new ArrayList<>();
			ArrayList<String> seqs = new ArrayList<>();
			ArrayList<String> alignedSeqs = new ArrayList<>();
			
			String line;
			while ((line = readLine("#")) != null) {
				if (line.equals("Sequences:")) {
					line = readLine("#");
					if (line.equals("----------------------------------------------------------")) {
						while ((line = readLine("#")) != null && !line.startsWith("-")) {
							names.add(line.substring(line.indexOf(") ") + 2, line.indexOf(":\t")));
							seqs.add(line.substring(line.replace("[", "(").indexOf(":\t(") + 3, line.replace("]", ")").lastIndexOf(")")));
						}
					}
				}
				if (line.equals("ALIGNMENT:")) {
					line = readLine("#");
					if (line.equals("----------------------------------------------------------")) {
						while ((line = readLine("#")) != null && !line.startsWith("-")) {
							alignedSeqs.add(line.substring(line.replace("[", "(").indexOf("(") + 2, line.replace("]", ")").indexOf(")")-1));
						}
					}
				}
			}
			for (int i = 0; i < alignedSeqs.size(); i++) {
				sequences.add(new Sequence(seqs.get(i), names.get(i)));
				alignedSequences.add(new Sequence(alignedSeqs.get(i), names.get(i)));
			}
			return new Alignment(sequences, alignedSequences);
		}
		
		/**
		 * reads a single line from the file
		 * @param commentsPrefix - the prefix of coments (to ignore them) 
		 * @return - a line of the file (empty lines and comments are skipped)
		 */
		private static String readLine(String commentsPrefix) {
			try {
				String line;
				while ((line = reader.readLine()) != null) {
					if (line.contains(commentsPrefix)) {
						line = line.substring(0, line.indexOf(commentsPrefix)); 		// remove comments
					}
					if (line.equals("")) { continue; }									// skip empty lines
					break;
				}
				return line;
			} catch (IOException e) { e.printStackTrace(); }
			return null;
		}

}
