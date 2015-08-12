/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grover;

/**
 *
 * @author seaadmin
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
 
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompound;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompoundSet;
import org.biojava.nbio.core.sequence.compound.DNACompoundSet;
 
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.loader.GenbankProxySequenceReader;

public class PileupGeneMapper {
    
    
    
    public PileupGeneMapper(){
        
    }
    
    public void initScript(){
        
    }
    
    public void executeScript() throws IOException, InterruptedException, CompoundNotFoundException{
        GenbankProxySequenceReader<NucleotideCompound> genbankDNAReader 
	= new GenbankProxySequenceReader<NucleotideCompound>("/tmp", "NM_001126", DNACompoundSet.getDNACompoundSet());
	DNASequence dnaSequence = new DNASequence(genbankDNAReader);
	genbankDNAReader.getHeaderParser().parseHeader(genbankDNAReader.getHeader(), dnaSequence);
	System.out.println("Sequence" + "(" + dnaSequence.getAccession() + "," + dnaSequence.getLength() + ")=" +
dnaSequence.getSequenceAsString().substring(0, 10) + "...");
    }
}
