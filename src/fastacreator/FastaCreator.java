/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastacreator;

import java.io.IOException;
import java.nio.file.Path;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author Rutger
 */
public class FastaCreator {
    private String newFilePath;
    private Path CDSPath;
    private int index;
    /**
     * @param args the command line arguments
     * @throws org.apache.commons.cli.ParseException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws ParseException, IOException {
        FastaCreator fastaCreate =  new FastaCreator();
        fastaCreate.run(args);
    }
    /**
     * runs all of the other classes.
     * @param args the commandline arguments entered by the user.
     * @throws ParseException an exception
     * @throws IOException an exception
     */
    public void run(String[] args) throws ParseException, IOException{
        ParseCLI parse =  new ParseCLI(args);
        CDSPath = parse.getCDSPath();
        newFilePath = parse.getNewFilePath();
        //gets the user entered index for the sequence.
        index = parse.getIndex()-1;
        createFasta();
    }
    /**
     * Creates a fastafile with the user entered file and the user entered index.
     * @throws IOException 
     */
    private void createFasta() throws IOException{
        FileReader read = new FileReader(CDSPath);
        FileWriter write = new FileWriter();
        write.OpenFile(newFilePath);
        //While there are still lines in the file.
        while(read.nextLine()){
            String[] splittedLine = read.getLine().split("\t");
            //add the id to the file allong with a new line followed by the sequence.
            String newLine = splittedLine[0] + "\n" + splittedLine[index];
            write.writeLine(newLine);
        }
        write.CloseFile();
    }
     
}
