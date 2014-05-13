package view;

import java.io.*;
import java.util.List;
import model.*;

public class OutputFileWriter extends OutputWriter {
	private File file;
	
	/**
	 * Konstruktor, der die Datei cleared.
	 * 
	 * @param path Der Pfad zur Datei
	 * @throws FileNotFoundException Falls die Datei nicht gefunden werden konnte
	 */
	public OutputFileWriter(String path) throws FileNotFoundException {
		setFile(new File(path));
	}
	
	/**
	 * Konstruktor, der die Datei cleared.
	 * 
	 * @param file Die Datei
	 * @throws FileNotFoundException Falls die Datei nicht gefunden werden konnte
	 */
	public OutputFileWriter(File file) throws FileNotFoundException {
		setFile(file);
	}
	
	/**
	 * Setzt die Datei und cleared den Inhalt.
	 * 
	 * @param file Die Datei
	 * @throws FileNotFoundException Falls die Datei nicht gefunden werden konnte
	 */
	private void setFile(File file) throws FileNotFoundException {
		if(file.length() > 0) {
			// clear file
			new FileOutputStream(file, false);			
		}
		
		this.file = file;
	}
	
	@Override
	public void write(Area startArea, List<Strategy> strategies) {
		// create string
		StringBuilder sb = new StringBuilder();
		
		sb.append("Startpunkt (" + startArea.getStartPoint().toString() + ")\n");
		sb.append(startArea.toString() + "\n\n");

		for(Strategy strategy : strategies) {
			sb.append(strategy.getName() + "\n");
			sb.append(strategy.getArea().toString() + "\n");
		}
		
		
		Strategy firstStrategy = strategies.get(0);
		int allCells = firstStrategy.getArea().numberOfCells();
		int blockedCells = firstStrategy.getArea().numberOfBlockedCells();
		
		sb.append("\nzu versiegelnde Parzellen: " + (allCells - blockedCells) + "\n");
		sb.append("Hindernisparzellen: " + blockedCells + "\n");
		sb.append("versiegelte Parzellen: " + firstStrategy.getArea().numberOfWorkedCells() + "\n");
		sb.append("nicht versiegelte Parzellen: " + firstStrategy.getArea().numberOfNonWorkedCells());
		
		
		
		// write to file
		Writer writer = null;
		
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"));
		    writer.write(sb.toString());
		} catch (IOException ex) {
			// TODO: besser weiter werfen?
			System.err.println("IOException: " + ex.getLocalizedMessage());
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
	}
	
	@Override
	public void append(String s) {
		// write to file
		Writer writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"));
		    writer.append(s);
		} catch (IOException ex) {
			// TODO: besser weiter werfen?
			System.err.println("IOException: " + ex.getLocalizedMessage());
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
	}	
}