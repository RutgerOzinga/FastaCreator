/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastacreator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

/**
 *
 * @author rutgero
 */
public final class ParseCLI {

    /**
     * stores the path to a snp directory.
     */
    private Path protPath;

    /**
     * stores the path to the new file.
     */
    private String newFilePath;
    /**
     * index of the sequence.
     */
    private int seqIndex;
    private Pattern re = Pattern.compile("(?<=\\\\|/)\\w+\\.fasta");

    /**
     * Rutger Ozinga. ParseCLI Parses the commandline input and is able to
     * return the wanted option and value.
     *
     * @param args are commandline arguments.
     * @throws org.apache.commons.cli.ParseException an exception
     */
    public ParseCLI(final String[] args)
            throws org.apache.commons.cli.ParseException {
        HelpFormatter helpForm = new HelpFormatter();
        Options cliOpt = new Options();
        cliOpt.addOption("h", "help", false, "Displays help");
        cliOpt.addOption("p", true, "Expects a path to a protein fasta file.");
        cliOpt.addOption("n", true, "Expects a path to place the new tab separated transcript file at.");
        cliOpt.addOption("i", true, "The index of the sequence");
        if (args.length == 0) {
            helpForm.printHelp("Please enter all the "
                    + "options below. ",
                    cliOpt);
            System.exit(0);
        } else {
            BasicParser parser = new BasicParser();
            CommandLine cliParser = parser.parse(cliOpt, args);
            if (cliParser.getOptions().length < 2) {
                System.out.println("Error : "
                        + "Please enter all options in"
                        + " order for this program to work"
                        + "!\n");
                helpForm.printHelp("Please enter all of the  "
                        + "option ", cliOpt);
                System.exit(0);
            } else {
                if (cliParser.hasOption("h") && cliParser.hasOption(
                        "help")) {
                    helpForm.printHelp("Command Line Help:\n", cliOpt);
                    System.exit(0);
                } else {
                    String snpFileString = cliParser.getOptionValue("p");
                    Path snpPath = Paths.get(snpFileString);
                    if (Files.exists(snpPath)) {
                        setCDSPath(snpPath);
                    } else {
                        System.out.println("The entered Path does"
                                + " not exits");
                        helpForm.printHelp("Please enter -p followed by a valid"
                                + " path ", cliOpt);
                        System.exit(0);
                    }
                    String newFileString = cliParser.getOptionValue("n");
                    Matcher match2 = re.matcher(newFileString);
                    String editedTranscriptFileString = match2.replaceAll("");
                    Path newFilePath = Paths.get(editedTranscriptFileString);
                    if (Files.exists(newFilePath)) {
                        setNewFilePath(newFileString);
                    } else {
                        System.out.println("The entered Path does"
                                + " not exits");
                        helpForm.printHelp("Please enter -nt followed by a valid"
                                + " path ", cliOpt);
                        System.exit(0);
                    }
                    String indexString = cliParser.getOptionValue("i");
                    int index = Integer.parseInt(indexString);
                    setIndex(index);
                }
            }
        }
    }

    /**
     * gets the protein file path.
     *
     * @return the protein file path
     */
    public Path getCDSPath() {
        return protPath;
    }

    /**
     * sets the protein file path.
     *
     * @param newProtPath the file path
     */
    public void setCDSPath(final Path newProtPath) {
        this.protPath = newProtPath;
    }

    /**
     * sets the path to the new file.
     *
     * @param filePath the path to the new file.
     */
    public void setNewFilePath(final String filePath) {
        this.newFilePath = filePath;
    }
    public String getNewFilePath(){
        return newFilePath;
    }
    public int getIndex(){
        return seqIndex;
    }
    public void setIndex(int index){
        this.seqIndex = index;
    }
}
