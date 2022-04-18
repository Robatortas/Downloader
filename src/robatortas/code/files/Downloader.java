package robatortas.code.files;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Downloader {
	
	public static final String AUTHOR = "Robatortas";
	
	private Scanner readInput = new Scanner(System.in);
	private String input;
	private String inputLink;
	private String inputFileName;
	
	public Downloader() {
		console();
	}
	
	public void console() {
		notice();
		downloadOption();
	}
	
	public void notice() {
		System.out.println("Welcome to \"File Downloader\" client."
				+ "\n" + "\n"
				+ "This program was made by " + AUTHOR + "."
				+ "\n" + "\n"
				+ "USE IT AT YOUR OWN WILL"
				+ "\n"
				+ "************************************");
	}
	
	public void downloadOption() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.err.println("EXCEPTION: Thread Issue!");
		}
		System.out.println("What kind of download do you want to compute?" + "\n");
		
		System.out.println("-Option A: Directory" + "\n");
		System.out.println("-Option B: File" + "\n");
		
		System.out.println("Please type out your answer..." + "\n");
		
		downloadOptionAns();
	}
	
	// Options input answer
	public void downloadOptionAns() {
		this.input = readInput.nextLine().toLowerCase();
		
		// I could use switch case, but I am lazy as fuck.
		if(input.contains("a".toLowerCase())) {
			System.out.println("\n" + "You'll be downloading files through a DIRECTORY it seems.");
			computeDirDownload();
		}
		if(input.contains("b".toLowerCase())) {
			System.out.println("\n" + "You'll be downloading a FILE you say." + "\n");
			System.out.println("Input the download link:" + "\n");
			
			this.inputLink = readInput.nextLine().toLowerCase();
			
			System.out.println("\n" + "Input the file name" + "\n");
			this.inputFileName = readInput.nextLine().toLowerCase();
			// TODO: CONVERT INPUTLINK TO A URL!!!
//			computeFileDownload(inputLink, inputFileName);
		}
	}
	
	// Computes the DIRECTORY download
	public void computeDirDownload() {
		
	}
	
	// Computes the FILE download
	public void computeFileDownload(URL url, String fileName) {
		try (InputStream inP = url.openStream()){
			Files.copy(inP, Paths.get(fileName));
		} catch (IOException e) {
			System.err.println("EXCEPTION: inP != url.openStream()");
		}
	}
}