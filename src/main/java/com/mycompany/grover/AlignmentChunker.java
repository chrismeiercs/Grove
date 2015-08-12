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


public class AlignmentChunker {
    
    private File fileToBeChunked;
    private int chunkSize;
    
    public AlignmentChunker(File needsChunked, int chunkSize){
        this.fileToBeChunked = needsChunked;
        this.chunkSize = chunkSize;
    }
    
    public void chunk() throws FileNotFoundException, IOException{
        BufferedReader br = openChunkFile(this.fileToBeChunked);
        chunkFile(br);
        br.close();  
    }
    
    private BufferedReader openChunkFile(File file) throws FileNotFoundException{
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        return br;
    }
    
    private BufferedWriter outFileBW(String outFileName) throws IOException{
        File outFile = new File(outFileName);
         BufferedWriter outFileBW = new BufferedWriter(new FileWriter(outFile));
         return outFileBW;
    }
    
    private void chunkFile(BufferedReader br) throws IOException{
        String line;
        String[] columns;
        int fileNumber = 1;
        String outname = "Chunk"+fileNumber+"."+fileToBeChunked.getName();
        
        System.out.println(outname);
       BufferedWriter outWriter = outFileBW(outname);
        
        int lineCounter = 1;
        while((line = br.readLine()) != null){
            
            if((lineCounter % chunkSize) == 0){
                //chunk size reached
                fileNumber++;
                outWriter.close();
                outname = "Chunk"+fileNumber+"."+fileToBeChunked.getName();
                outWriter = outFileBW(outname);
                
                System.out.println(outname);
                
            }
                //print to file
                outWriter.write(line);
                outWriter.newLine();
              
                
            lineCounter++;
            
        }
        br.close();
        outWriter.close();
        JOptionPane.showMessageDialog(null, "Chunking Complete");
    }

}
