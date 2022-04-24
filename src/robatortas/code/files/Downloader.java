package robatortas.code.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Downloader {
	
	public static final String AUTHOR = "Robatortas";
	
	private Scanner readInput = new Scanner(System.in);
	private String input;
	private String dir;
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
		
		System.out.println("-Option A: Directory (STILL IN DEVELOPMENT!)" + "\n");
		System.out.println("-Option B: File" + "\n");
		
		System.out.println("Please type out your answer..." + "\n");
		
		downloadOptionAns();
	}
	
	// Options input answer
	public void downloadOptionAns() {
		this.input = readInput.nextLine().toLowerCase();
		
		// I could use switch case, but I am lazy as fuck.
		// And the code could be much more optimized, but I am lazyyyY!
		if(input.contains("a".toLowerCase())) {
			System.out.println("\n" + "You'll be downloading files through a DIRECTORY it seems.");

			System.out.println("\n" + "Input the download link:" + "\n");
			this.inputLink = readInput.nextLine().toLowerCase();
			
			System.out.println("\n" + "Input the saving location on your machine" + "\n");
			
			this.dir = readInput.nextLine().toLowerCase();
			
			System.out.println("Now input the file name (with its extention (Ex: myFile.jpg))");
			
			this.inputFileName = readInput.nextLine().toLowerCase();
			
			String savingLocation = dir + "\\" + inputFileName;
			
			try {
				computeDirDownload(new URL(inputLink), inputFileName);
				System.out.println(checkFileSize(new URL(inputLink)));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
		if(input.contains("b".toLowerCase())) {
			System.out.println("Input the download link:" + "\n");
		
			this.inputLink = readInput.nextLine().toLowerCase();
			
			System.out.println("\n" + "Input the saving location on your machine" + "\n");
			
			this.dir = readInput.nextLine().toLowerCase();
			
			System.out.println("Now input the file name (with its extention (Ex: myFile.jpg))");
			
			this.inputFileName = readInput.nextLine().toLowerCase();
			
			String savingLocation = dir + "\\" + inputFileName;
		
			try {
				computeFileDownload(new URL(inputLink), savingLocation);
				System.out.println(checkFileSize(new URL(inputLink)) + "bytes");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Computes the DIRECTORY download
	public void computeDirDownload(URL url, String fileName) {
		 byte[] buffer = new byte[1024];
		 InputStream inP = null;
		try { inP = url.openStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		    int bytesRead = -1;
		    StringBuilder page = new StringBuilder(1024);
		    try {
				while ((bytesRead = inP.read(buffer)) != -1) {
				    page.append(new String(buffer, 0, bytesRead));
				    Files.copy(inP, Paths.get(dir + "\\" + fileName));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public int checkFileSize(URL url) {
		try {
			URLConnection connection = url.openConnection();
			connection.connect();
			int fileSize = connection.getContentLength();
			return fileSize;
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Exception: URLConnection error");
		}
		return 0;
	}
	
	// Computes the FILE download
	public void computeFileDownload(URL url, String fileName) {
		try (InputStream inP = url.openStream()){
			// Copies bytes from the inputStream to a file
			Files.copy(inP, Paths.get(fileName));
		} catch (IOException e) {
			System.err.println("EXCEPTION: inP != url.openStream()");
		}
	}
}