/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grover;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author seaadmin
 */
public class RNASeqDiff {

    private File cappedFile;
    private File uncappedFile;
    private String outputFileName;

    private int uncappedSampleNameColumn;
    private int uncappedNucleotideColumn;
    private int uncappedNucleotideNumberColumn;
    private int uncappedHitsColumn;

    private int cappedSampleNameColumn;
    private int cappedNucleotideColumn;
    private int cappedNucleotideNumberColumn;
    private int cappedHitsColumn;

    public RNASeqDiff(File capped, File uncapped, String outputName, int uncappedSampleColumn,
            int uncappedNtNumColumn, int uncappedNtColumn, int uncappedHitsColumn,
            int cappedSampleColumn, int cappedNtNumColumn, int cappedNtColumn,
            int cappedHitsColumn) {

        this.cappedFile = capped;
        this.uncappedFile = uncapped;
        this.outputFileName = outputName;

        this.uncappedSampleNameColumn = uncappedSampleColumn;
        this.uncappedNucleotideColumn = uncappedNtColumn;
        this.uncappedNucleotideNumberColumn = uncappedNtNumColumn;
        this.uncappedHitsColumn = uncappedHitsColumn;

        this.cappedSampleNameColumn = cappedSampleColumn;
        this.cappedNucleotideColumn = cappedNtColumn;
        this.cappedNucleotideNumberColumn = cappedNtNumColumn;
        this.cappedHitsColumn = cappedHitsColumn;

    }

    public void calculateDifference() throws FileNotFoundException, IOException {
        BufferedReader cappedBR = openFile(this.cappedFile);
        BufferedReader uncappedBR = openFile(this.uncappedFile);
        BufferedWriter outFileBW = fileToWrite(this.outputFileName);

        String cappedLine, uncappedLine;

        while (((cappedLine = cappedBR.readLine()) != null)
                && (uncappedLine = uncappedBR.readLine()) != null) {

            //get each column from both files
            String[] uncappedColumns;
            String[] cappedColumns;
            
            uncappedColumns = uncappedLine.split("\t");
            cappedColumns = cappedLine.split("\t");
            //calculate difference and return the absolute value
            int cappedReads = new Integer(cappedColumns[this.cappedHitsColumn -1]);
            int uncappedReads = new Integer(uncappedColumns[this.uncappedHitsColumn -1]);
            
            int readDifference = Math.abs((uncappedReads - cappedReads));
            
            String sampleName = uncappedColumns[this.uncappedSampleNameColumn - 1];
            String nucleotide = uncappedColumns[this.uncappedNucleotideColumn - 1];
            String nucleotideNumber = uncappedColumns[this.uncappedNucleotideNumberColumn - 1]; 
            //write to file 
            outFileBW.write(sampleName + "\t" + nucleotideNumber + "\t" + nucleotide + "\t" + readDifference);
            outFileBW.newLine();
        }
        JOptionPane.showMessageDialog(null,"Calculation complete");

    }

    private BufferedReader openFile(File file) throws FileNotFoundException {
        return new BufferedReader(new FileReader(file));
    }

    private BufferedWriter fileToWrite(String fileName) throws IOException {

        File file = new File(fileName);
        return new BufferedWriter(new FileWriter(file));

    }

}
