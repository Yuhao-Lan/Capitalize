
package edu.gatech.seclass.capitalize;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class MyMainTest
{

    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    // Some utilities

    private File createTmpFile() throws IOException {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    private File createInputFile0() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("");

        fileWriter.close();
        return file1;
    }

    private File createInputFile1() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("hello Jack, I love you!!!!,\n" +
                "do you like or love me???????.\n" +
                "do you want to have a dinner with me? I get\n" +
                " $10000 scholarship to fund my study. So I am so happy!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile2() throws Exception {
        File file2 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file2);

        fileWriter.write("veevvvveev,ooooo,\n\nvEevve,abd");

        fileWriter.close();
        return file2;
    }
    private File createInputFile3() throws Exception {
        File file3 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file3);

        fileWriter.write("9xy89xY8!!!,\n\nfadsfas9Xy8,abd");

        fileWriter.close();
        return file3;
    }
    private File createInputFile4() throws Exception {
        File file3 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file3);

        fileWriter.write("$$$$$lAn1991$lAn1991!!!,\n\n$dfdfsdfd$laN1991,abd");

        fileWriter.close();
        return file3;
    }
    private File createInputFile5() throws Exception {
        File file3 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file3);

        fileWriter.write("I have a dream to be a \nSoftware engineer");
        fileWriter.close();
        return file3;
    }
    private File createInputFile6() throws Exception {
        File file3 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file3);

        fileWriter.write("9999i am the king## nothing\n \t King is here \n \t...");
        fileWriter.close();
        return file3;
    }

    private File createInputFile7() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Billy,\n" +
                "I am going to take cs6300 and cs6400 next semester.\n" +
                "Did you take cs 6300 last semester? I want to\n" +
                "take 2 courses so that I will graduate Asap!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile8() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Bill is," + System.getProperty("line.separator") +
                "in my opinion," + System.getProperty("line.separator") +
                "an easier name to spell than William." + System.getProperty("line.separator") +
                "Bill is shorter," + System.getProperty("line.separator") +
                "and Bill is" + System.getProperty("line.separator") +
                "first alphabetically.");

        fileWriter.close();
        return file1;
    }

    private File createInputFile9() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        fileWriter.write(" ");
        fileWriter.close();
        return file1;
    }


    private File createInputFile10() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("123|456|789|0ab|cde|fgh|ijk|lmn|opq|rst|uvw|xyz");

        fileWriter.close();
        return file1;
    }

    private File createInputFile11() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill, have you learned your abc and 123?\r\n" +
                "I know My Abc's.\r" +
                "It is important to know your abc's and 123's,\n" +
                "so repeat with me: abc! 123! Abc and 123!");

        fileWriter.close();
        return file1;
    }
    private File createInputFile12() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("aBcdefgae");

        fileWriter.close();
        return file1;
    }
    private File createInputFile13() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("\r\n\r!#@");

        fileWriter.close();
        return file1;
    }

    private File createInputFile14() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("\t\n\f\r.");

        fileWriter.close();
        return file1;
    }










    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    // CapitalizeTest1
    // Purpose: test the input file size that is 0
    // Frame #: 1
    @Test
    public void capitalizeTest1() throws Exception {
        File inputFile1 = createInputFile0();
        String args[] = {inputFile1.getPath()};
        Main.main(args);
        String expected1 = "";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    // CapitalizeTest2
    // Purpose: test no input arguments and options. Like : capitalize file1.txt
    // Frame #: 4
    @Test
    public void capitalizeTest2() throws Exception {
        File inputFile1 = createInputFile1();
        String args[] = {inputFile1.getPath()};
        Main.main(args);
        String expected1 = "Hello Jack, I love you!!!!,\n" +
                "Do you like or love me???????.\n" +
                "Do you want to have a dinner with me? I get\n" +
                " $10000 scholarship to fund my study. So I am so happy!";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    // CapitalizeTest3
    // Purpose: test single argument and option with "-w" but without delimiters. Like : capitalize -w file1.txt
    // Frame #: 5
    @Test
    public void capitalizeTest3() throws Exception {
        File inputFile1 = createInputFile1();
        String args[] = {"-w", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "Hello Jack, I Love You!!!!,\n" +
                "Do You Like Or Love Me???????.\n" +
                "Do You Want To Have A Dinner With Me? I Get\n" +
                " $10000 Scholarship To Fund My Study. So I Am So Happy!";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    // CapitalizeTest4
    // Purpose: test single argument and option with "-w" but single character delimiters. Like : capitalize -w 'a' file1.txt
    // Frame #: 6
    //Type c: -w "a" will always capitalize the FIRST CHARACTER/LETTER of the sentence
    @Test
    public void capitalizeTest4() throws Exception {
        File inputFile1 = createInputFile12();
        String args[] = {"-w","a", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "aBcdefgaE";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    // CapitalizeTest5
    // Purpose: test single argument and option with "-w" but many character delimiters. Like : capitalize -w 'aecd' file1.txt
    // Frame #: 7
    @Test
    public void capitalizeTest5() throws Exception {
        File inputFile1 = createInputFile1();
        String args[] = {"-w","aecd", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "HeLlo JaCk, I love you!!!!,\n" +
                "dO you like or love me???????.\n" +
                "dO you waNt to haVe a dInneR with me? I geT\n" +
                " $10000 scHolaRship to fund my studY. So I aM so haPpy!";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    // CapitalizeTest6
    // Purpose: test single argument and option with "-m" but without string . Like : capitalize -m file1.txt
    // Frame #: 8
    //Type c: -m "non-character" will generate runtime error
    @Test
    public void capitalizeTest6() throws Exception {
        File inputFile1 = createInputFile1();
        String args[] = {"-m","9999999", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "hello Jack, I love you!!!!,\n" +
                "do you like or love me???????.\n" +
                "do you want to have a dinner with me? I get\n" +
                " $10000 scholarship to fund my study. So I am so happy!";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    // CapitalizeTest7
    // Purpose: test single argument and option with "-m" but with string contains only letters . Like : capitalize -m "VeEv" file1.txt
    // Frame #: 9
    @Test
    public void capitalizeTest7() throws Exception {
        File inputFile1 = createInputFile2();
        String args[] = {"-m","VeEv", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "VeEvvvVeEv,ooooo,\n\nVeEvve,abd";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    // CapitalizeTest8
    // Purpose: test -m -m
    // Frame #: 17
    @Test
    public void capitalizeTest8() throws Exception {
        File inputFile1 = createInputFile3();
        String args[] = {"-m","-m", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9xy89xY8!!!,\n\nfadsfas9Xy8,abd";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    // CapitalizeTest9
    // Purpose: test single argument and option with "-m" but with string  contains other characters . Like : capitalize -m "$LAN1991" file1.txt
    // Frame #: 12
    //Type b
    @Test
    public void capitalizeTest9() throws Exception {
        File inputFile1 = createInputFile4();
        String args[] = {"-m","LAN1991", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "$$$$$LAN1991$LAN1991!!!,\n\n$dfdfsdfd$LAN1991,abd";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    // CapitalizeTest10
    // Purpose: test single argument and option with "-f" . Like : capitalize -f file1.txt
    // Frame #: 13
    @Test
    public void capitalizeTest10() throws Exception {
        File inputFile1 = createInputFile4();
        String args[] = {"-f", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "$$$$$LaN1991$LaN1991!!!,\n\n$DFDFSDFD$LAn1991,ABD";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    // CapitalizeTest11
    // Purpose: test three arguments and options with "-w","-m" and "-f" , -w and -m with no content
    // Frame #: 37
    @Test
    public void capitalizeTest11() throws Exception {
        File inputFile1 = createInputFile5();
        String args[] = {"-m","-f","-w", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "i hAVE a dREAM tO bE a \nsOFTWARE eNGINEER";

        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    // CapitalizeTest12
    // Purpose: test two arguments and options with "-w" and "-f" , -w <delimiter is many characters>. Like : capitalize -w "abn" -f file1.txt
    //also test "   -f   " equals "-f", because we use arg = arg.trim()
    // Frame #: 31

    //Type a
    @Test
    public void capitalizeTest12() throws Exception {
        File inputFile1 = createInputFile5();
        String args[] = {"-f","-w","abn", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "i HAvE A DREAm TO Be A \nsOFTWArE ENgINeER";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        //"I have a dream to be a \nSoftware engineer"
    }
    // CapitalizeTest13
    // Purpose: test two arguments and options with "-w" and "-m" , -w <delimiter is many characters> -m <string contains any other characters>. Like : capitalize -w "abn" -m "HAVE" -f file1.txt
    // Frame #: 28
    //Type a
    @Test
    public void capitalizeTest13() throws Exception {
        File inputFile1 = createInputFile5();
        String args[] = {"-w","abn","-m","HAVE", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "I HAVE a dreaM to bE a \nSoftwaRe enGinEer";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    // CapitalizeTest14
    // Purpose: test three arguments and options with "-m" and "-w" and "-f" , -m <string contains only letters> -w <delimiter is many characters> . Like : capitalize -m "AVE" -w "ab" -f file1.txt
    // Frame #: 48
    // Type a
    @Test
    public void capitalizeTest14() throws Exception {
        File inputFile1 = createInputFile5();
        String args[] = {"-m","AVE","-w","ab","-f", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "i Have A DREAm TO Be A \nsOFTWArE ENGINEER";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    // CapitalizeTest15
    // Purpose: test three arguments and options with "-m" and "-w", -m <string contains only letters> -w <delimiter is many characters> . Like : capitalize -m "AVE" -w "ab" file1.txt
    // Frame #: 25
    @Test
    public void capitalizeTest15() throws Exception {
        File inputFile1 = createInputFile5();
        String args[] = {"-m","AVE","-w","ab", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "I hAVE a dreaM to bE a \nSoftwaRe engineer";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);

    }
    // CapitalizeTest16
    // Purpose: input filename is empty Like : capitalize
    // Frame #: 2

    @Test
    public void capitalizeTest16(){
        String args[] = null; //invalid argument
        Main.main(args);
        assertEquals("Usage: Capitalize  [-w [string]] [-m string] [-f] [-i|-I] [-o] <filename>", errStream.toString().trim());
    }
    // CapitalizeTest17
    // Purpose: test Arguments and options format WHEN IT IS improper format
    // Frame #: 3
    @Test
    public void capitalizeTest17() throws Exception {
        File inputFile1 = createInputFile3();
        String args[] = {"-W-X--FA", inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: Capitalize  [-w [string]] [-m string] [-f] [-i|-I] [-o] <filename>", errStream.toString().trim());
    }
    //test 18
    // Purpose: test -f is always applied last
    // Frame #: 30
    @Test
    public void capitalizeTest18() throws Exception {
        File inputFile1 = createInputFile5();
        String args[] = {"-f","-w", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "i hAVE a dREAM tO bE a \nsOFTWARE eNGINEER";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    //test 19
    //Purpose: test -m, -w without any content before this two opts
    //Frame #: 19
    @Test
    public void capitalizeTest19() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-m","-w", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999i Am The King## Nothing\n \t King Is Here \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    //test 20
    //Purpose: test -m, -w. -m with "   xxxx    " content
    //Frame #: 19
    @Test
    public void capitalizeTest20() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-m"," xxxx   ","-w", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999i Am The King## Nothing\n \t King Is Here \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    //test 21
    //Purpose: test -m, -w. -m with "aM" content
    //Frame #: 20
    //Type c: it seems like -w is executed before -m
    //-w,-m will generate different result compared with -m,-w.
    @Test
    public void capitalizeTest21() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-m","aM","-w", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999i AM The King## Nothing\n \t King Is Here \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    //test 22
    //Purpose: test -m, -w. -m with "aM" content -w with "ioe" content
    //Frame #: 23
    @Test
    public void capitalizeTest22() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-m","aM","-w","ioe9", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999I aM the kiNg## noThiNg\n \t KiNg iS heRe \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    //test 23
    //Purpose: test -m,-w,-f. All opts are with contents
    //Frame #: 40
    @Test
    public void capitalizeTest23() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-m","aM","-f","-w","9012ioe", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999i Am THE KInG## NOtHInG\n \t kInG Is HErE \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    //test 24
    //Purpose: test -m,-w,-f. All opts are with contents with multiple "-f"
    //Frame #: 41
    //Type a: only use one -f
    @Test
    public void capitalizeTest24() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-m","aM","-w","###!!!ioe", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999i aM the kiNg## noThiNg\n \t KiNg iS heRe \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    //test 25
    //Purpose: test -o -o
    //Frame #: 51
    @Test
    public void capitalizeTest25() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-o","-o","-o",inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999i am the king## nothing\n \t King is here \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals(expected1,outStream.toString().trim());



    }

    //test 26
    //Purpose: test -w,-m. Test -m with "AM " which contanins space
    //Frame #: 28
    @Test
    public void capitalizeTest26() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-w","-m","AM ", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999i AM The King## Nothing\n \t King Is Here \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    //test 27
    //Purpose: test -w,-w.
    //Frame #: 7
    //type a: I am not sure this is type a or c. But I already test "-w" has something wrong in TEST4
    //there is no need to test the same bug again
    @Test
    public void capitalizeTest27() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-w","-w","i", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999i Am The KiNg## NothiNg\n \t KiNg Is Here \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);

        //"9999i am the king## nothing\n \t King is here \n \t..."
    }
    //test 28
    //Purpose: test -w,-m. Test mutiple -m
    //Frame #: 28
    @Test
    public void capitalizeTest28() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-w","-m","I","-m","aM", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999I aM The KIng## NothIng\n \t KIng Is Here \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    //test 29
    //Purpose: test -w,-m. Test mutiple -m,-w
    //Frame #: 28
    //Type a
    @Test
    public void capitalizeTest29() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-w","-m","I","-m","aM", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "9999I aM The KIng## NothIng\n \t KIng Is Here \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        //9999i am the king## nothing
        // 	 King is here
        // 	...
    }
    //test 30
    //Purpose: test -w,-w.
    //Frame #: 7
    @Test
    public void capitalizeTest30() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-w","-w","-w",inputFile1.getPath()};
        Main.main(args);

        String expected1 = "9999i Am The King## Nothing\n \t King Is Here \n \t...";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    //test 31
    //Purpose: test invalid opts
    //Frame #: 3
    @Test
    public void capitalizeTest31() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"    ","             ",inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: Capitalize  [-w [string]] [-m string] [-f] [-i|-I] [-o] <filename>", errStream.toString().trim());

    }
    //test 32
    //Purpose: test invalid opts
    //Frame #: 3
    //Type a
    @Test
    public void capitalizeTest32() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"    ","-f","-m","-f",inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: Capitalize  [-w [string]] [-m string] [-f] [-i|-I] [-o] <filename>", errStream.toString().trim());

    }
    //test 33
    //Purpose: test invalid opts
    //Frame #: 3
    @Test
    public void capitalizeTest33() throws Exception {
        File inputFile1 = createInputFile6();
        String args[] = {"-i","-w","-o","     ", inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: Capitalize  [-w [string]] [-m string] [-f] [-i|-I] [-o] <filename>", errStream.toString().trim());

    }
    //test 34
    //test -i -I
    //Type c: when the argument contains "-i" and "-I", it only accepts the first "-i"
    @Test
    public void capitalizeTest34() throws Exception {
        File inputFile1 = createInputFile2();
        String args[] = {"-i","-I", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "VEEVVVVEEV,OOOOO,\n\nVEEVVE,ABD";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    //test 35
    //test -i -I and -o
    //Type a
    @Test
    public void capitalizeTest35() throws Exception {
        File inputFile1 = createInputFile2();
        String args[] = {"-m","VeEv","-I","-o", inputFile1.getPath()};
        String actual_before = getFileContent(inputFile1.getPath());
        Main.main(args);

        String expected1 = "VeEvVVVeEv,OOOOO,\n\nVeEvVE,ABD";
        String actual_after = getFileContent(inputFile1.getPath());
        assertEquals("The file was changed!", actual_before, actual_after);
        assertEquals(expected1,outStream.toString().trim());

        //VEEVVVVEEV,OOOOO,
        //
        //VEEVVE,ABD
    }
    //no file
    @Test
    public void capitalizeTest36() throws Exception {
        String args[] = {"-x"}; //invalid argument
        Main.main(args);
        assertEquals("Usage: Capitalize  [-w [string]] [-m string] [-f] [-i|-I] [-o] <filename>", errStream.toString().trim());

    }

    @Test
    public void capitalizeTest37() {
        String args[] = {"-m"}; //invalid argument
        Main.main(args);
        assertEquals("Usage: Capitalize  [-w [string]] [-m string] [-f] [-i|-I] [-o] <filename>", errStream.toString().trim());
    }

    @Test
    public void capitalizeTest38() throws Exception {
        String args[] = {"nofile.txt"};
        Main.main(args);

        assertEquals("File Not Found", errStream.toString().trim());
    }

    @Test
    public void capitalizeTest39() throws Exception {
        File inputFile1 = createInputFile7();

        String args[] = {"-f", "-x", inputFile1.getPath()};
        Main.main(args);

        String expected = "Howdy Billy,\n" +
                "I am going to take cs6300 and cs6400 next semester.\n" +
                "Did you take cs 6300 last semester? I want to\n" +
                "take 2 courses so that I will graduate Asap!";

        String actual = getFileContent(inputFile1.getPath());

        assertEquals("The file was changed!", expected, actual);
        assertEquals("Usage: Capitalize  [-w [string]] [-m string] [-f] [-i|-I] [-o] <filename>", errStream.toString().trim());
    }

    @Test
    public void capitalizeTest40() throws Exception {
        File inputFile1 = createInputFile7();

        String args[] = {"-i", inputFile1.getPath()};
        Main.main(args);

        String expected = "howdy billy,\n" +
                "i am going to take cs6300 and cs6400 next semester.\n" +
                "did you take cs 6300 last semester? i want to\n" +
                "take 2 courses so that i will graduate asap!";

        String actual = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    @Test
    //Type d: -I and change the content of file, but there is something wrong with the whitespace characters!
    public void capitalizeTest41() throws Exception {
        File inputFile1 = createInputFile7();

        String args[] = {"-I", inputFile1.getPath()};
        Main.main(args);

        String expected = "HOWDY BILLY,\n" +
                "I AM GOING TO TAKE CS6300 AND CS6400 NEXT SEMESTER.\r\n" +
                "DID YOU TAKE CS 6300 LAST SEMESTER? I WANT TO\r" +
                "TAKE 2 COURSES SO THAT I WILL GRADUATE ASAP!";

        String actual = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    @Test
    public void capitalizeTest42() throws Exception {
        File inputFile5 = createInputFile8();

        String args[] = {"-w", "z", inputFile5.getPath()};
        Main.main(args);

        String expected = "Bill is," + System.getProperty("line.separator") +
                "in my opinion," + System.getProperty("line.separator") +
                "an easier name to spell than William." + System.getProperty("line.separator") +
                "Bill is shorter," + System.getProperty("line.separator") +
                "and Bill is" + System.getProperty("line.separator") +
                "first alphabetically.";

        String actual = getFileContent(inputFile5.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    @Test
    public void capitalizeTest43() throws Exception {
        File inputFile9 = createInputFile9();

        String args[] = {"-f", inputFile9.getPath()};
        Main.main(args);

        String expected = " ";

        String actual = getFileContent(inputFile9.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: Additional D2 Test
    @Test
    public void capitalizeTest44() throws Exception {
        File inputFile8 = createInputFile10();

        String args[] = {"-w", "|", "-f", "-m", "XYZ", inputFile8.getPath()};
        Main.main(args);

        String expected = "123|456|789|0AB|cDE|fGH|iJK|lMN|oPQ|rST|uVW|xyz";

        String actual = getFileContent(inputFile8.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    @Test
    public void capitalizeTest45() throws Exception {
        File inputFile6 = createInputFile11();

        String args[] = {"-m", "ABC'S", "-w", inputFile6.getPath()};
        Main.main(args);

        String expected = "Howdy Bill, Have You Learned Your Abc And 123?\r\n" +
                "I Know My ABC'S.\r" +
                "It Is Important To Know Your ABC'S And 123's,\n" +
                "So Repeat With Me: Abc! 123! Abc And 123!";

        String actual = getFileContent(inputFile6.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    @Test
    public void capitalizeTest46() throws Exception {
        File inputFile6 = createInputFile13();

        String args[] = {"-w","\r","-m","\n","-i","-I","-f","-f","-o","-o","-w","-m","i",inputFile6.getPath()};
        Main.main(args);

        String expected = "\r\n\r!#@";

        String actual = getFileContent(inputFile6.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    @Test
    public void capitalizeTest47() throws Exception {
        File inputFile6 = createInputFile14();

        String args[] = {"-w","\t\r","-m","99999","\f","-i","-I","-f","-f","-o","-o","-w",".","-m","\t\f","i",inputFile6.getPath()};
        Main.main(args);
        String expected = "\t\n\f\r.";
        String actual = getFileContent(inputFile6.getPath());
        assertEquals("The files differ!", expected, actual);
    }















}
