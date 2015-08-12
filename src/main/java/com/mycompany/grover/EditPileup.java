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
public class EditPileup {
    
    private File pileupFile;
    private int nameColumn; 
    private int nucleotideColumn; 
    private int countColumn;
    private int hitsColumn;
    private int i = 1;
    
    public EditPileup(File pileupFile, int nameColumn, int nucleotideColumn, int countColumn, int hitsColumn){
        this.pileupFile = pileupFile;
        this.nameColumn = nameColumn;
        this.nucleotideColumn = nucleotideColumn;
        this.countColumn = countColumn;
        this.hitsColumn = hitsColumn;
    }
    
    public void edit() throws FileNotFoundException, IOException{
        BufferedReader br = openPileupFile(this.pileupFile);
        editFile(br);
        br.close();
        JOptionPane.showMessageDialog(null, "Pileup Editing Complete");
    }
    
    private BufferedReader openPileupFile(File file) throws FileNotFoundException{
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        return br;
    }
    
    private BufferedWriter outFileBW(String editFileName) throws IOException{
        File outFile = new File(editFileName);
        BufferedWriter outFileBW = new BufferedWriter(new FileWriter(outFile));
         return outFileBW;
    }
    
    private void editFile(BufferedReader br) throws IOException{
        String line;
        String[] columns;
        
        BufferedWriter editBW = outFileBW(this.pileupFile.getName()+".Revised");
        
        while((line = br.readLine()) != null){
            
            //split line
            columns = line.split("\t");
            
            String name = columns[this.nameColumn-1];
            String nucleotide = columns[this.nucleotideColumn-1];
            int count = new Integer(columns[this.countColumn-1]);
            int hits = new Integer(columns[this.hitsColumn-1]);
            
            if(i < count){
                while(i < count){
                    editBW.write(name + "\t"+i+"\tN\t0");
                    editBW.newLine();
                    //System.out.println(name + "\t"+i+"\tN\t0");
                    i++;
                }
                editBW.write(name + "\t"+i+"\tN\t0");
                editBW.newLine();
                //System.out.println(name + "\t"+count+"\t"+nucleotide+"\t" + hits);
                i++;
            }else{
                //write the line
                editBW.write(name + "\t"+i+"\tN\t0");
                editBW.newLine();
                //System.out.println(name + "\t"+count+"\t"+nucleotide+"\t" + hits);
                i++;
            }
            
            
        }
        editBW.close();
    
    }
    
    
}
