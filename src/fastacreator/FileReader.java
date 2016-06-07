/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastacreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author Rutger
 */
public class FileReader {
    /**
     * a buffered reader object.
     */
    private BufferedReader reader;
    /**
     * the current line of the file.
     */
    private String line;
    /**
     * creates a file reader object with a user entered file.
     * @param filePath path to the file.
     * @throws IOException 
     */
    public FileReader(Path filePath) throws IOException {
        Charset charset = Charset.forName("US-ASCII");
        reader = Files.newBufferedReader(filePath, charset);
    }
     /**
     * sets the next line and returns if it is empty or not.
     * @return boolean based on if line is empty.
     * @throws IOException an exception.
     */
    public Boolean nextLine() throws IOException {
        if ((line = reader.readLine()) != null) {
            return true;
        } else {
            reader.close();
            return false;

        }

    }
    /**
     * returns the current line of the file.
     * @return string version of the line.
     */
    public String getLine() {
        return line;
    }
}
