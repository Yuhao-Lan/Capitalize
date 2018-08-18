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

import static org.junit.Assert.*;

/*
DO NOT ALTER THIS CLASS.  Use it as an example for MyMainTest.java
 */

public class MainTest {

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


    private File createInputFile1() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Billy,\n" +
                "I am going to take cs6300 and cs6400 next semester.\n" +
                "Did you take cs 6300 last semester? I want to\n" +
                "take 2 courses so that I will graduate Asap!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile2() throws Exception {
        File file2 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file2);

        fileWriter.write("aaaaaa");

        fileWriter.close();
        return file2;
    }

    private File createInputFile3() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Billy,\n" +
                "I am going to take cs6300 and cs6400 next semester.\r\n" +
                "Did you take cs 6300 last semester? I want to\r" +
                "take 2 courses so that I will graduate Asap!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile4() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Billy,\n" +
                "This is a test file for the capitalize utility.\n" +
                "let's make sure it has at least a few lines,\n" +
                "so that we can create some \n"
                + "interesting test cases...And let's say \"howdy\" to Bill again!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile5() throws Exception {
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

    private File createInputFile6() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill, have you learned your abc and 123?\r\n" +
                "I know My Abc's.\r" +
                "It is important to know your abc's and 123's,\n" +
                "so repeat with me: abc! 123! Abc and 123!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile7() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("123\\456?789\\0ab?cde\\fgh?ijk\\lmn?opq\\rst?uvw\\xyz");

        fileWriter.close();
        return file1;
    }

    private File createInputFile8() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("123|456|789|0ab|cde|fgh|ijk|lmn|opq|rst|uvw|xyz");

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



    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    // test cases

    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 1 from assignment directions
    @Test
    public void mainTest1() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Howdy Billy,\n" +
                "I am going to take cs6300 and cs6400 next semester.\n" +
                "Did you take cs 6300 last semester? I want to\n" +
                "Take 2 courses so that I will graduate Asap!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 2 from assignment directions
    @Test
    public void mainTest2() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-w", inputFile1.getPath()};
        Main.main(args);

        String expected2 = "Howdy Billy,\n" +
                "I Am Going To Take Cs6300 And Cs6400 Next Semester.\n" +
                "Did You Take Cs 6300 Last Semester? I Want To\n" +
                "Take 2 Courses So That I Will Graduate Asap!";

        String actual2 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected2, actual2);
    }

    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 3 from assignment directions
    @Test
    public void mainTest3() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-m", "CS6300", inputFile1.getPath()};
        Main.main(args);

        String expected3 = "Howdy Billy,\n" +
                "I am going to take CS6300 and cs6400 next semester.\n" +
                "Did you take cs 6300 last semester? I want to\n" +
                "take 2 courses so that I will graduate Asap!";

        String actual3 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected3, actual3);
    }

    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 4 from assignment directions
    @Test
    public void mainTest4() throws Exception {
        File inputFile2 = createInputFile2();

        String args[] = {"-m", "AAAA", inputFile2.getPath()};
        Main.main(args);

        String expected4 = "AAAAaa";

        String actual4 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected4, actual4);
    }

    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 5 from assignment directions
    @Test
    public void mainTest5() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-m", "ASAP", "-f", inputFile1.getPath()};
        Main.main(args);

        String expected5 = "hOWDY bILLY,\n" +
                "i AM GOING TO TAKE CS6300 AND CS6400 NEXT SEMESTER.\n" +
                "dID YOU TAKE CS 6300 LAST SEMESTER? i WANT TO\n" +
                "TAKE 2 COURSES SO THAT i WILL GRADUATE asap!";

        String actual5 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected5, actual5);
    }

    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 6 from assignment directions
    @Test
    public void mainTest6() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-w", "abc", inputFile1.getPath()};
        Main.main(args);

        String expected6 = "Howdy Billy,\n" +
        "I aM going to taKe cS6300 aNd cS6400 next semester.\n" +
                "Did you taKe cS 6300 laSt semester? I waNt to\n" +
                "taKe 2 cOurses so thaT I will graDuaTe AsaP!";

        String actual6 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected6, actual6);
    }


    // Purpose: To provide an example of a test case format
    // Frame #: Instructor error example
    @Test
    public void mainTest7() {
        String args[] = null; //invalid argument
        Main.main(args);
        assertEquals("Usage: Capitalize  [-w [string]] [-m string] [-f] [-i|-I] [-o] <filename>", errStream.toString().trim());
    }

    // Purpose: Additional D2 Test
    @Test
    public void mainTest8() {
        String args[] = {"-m"}; //invalid argument
        Main.main(args);
        assertEquals("Usage: Capitalize  [-w [string]] [-m string] [-f] [-i|-I] [-o] <filename>", errStream.toString().trim());
    }

    // Purpose: Additional D2 Test
    @Test
    public void mainTest9() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-i", inputFile1.getPath()};
        Main.main(args);

        String expected = "howdy billy,\n" +
                "i am going to take cs6300 and cs6400 next semester.\n" +
                "did you take cs 6300 last semester? i want to\n" +
                "take 2 courses so that i will graduate asap!";

        String actual = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: Additional D2 Test
    @Test
    public void mainTest10() throws Exception {
        File inputFile3 = createInputFile3();

        String args[] = {"-I", inputFile3.getPath()};
        Main.main(args);

        String expected = "HOWDY BILLY,\n" +
                "I AM GOING TO TAKE CS6300 AND CS6400 NEXT SEMESTER.\r\n" +
                "DID YOU TAKE CS 6300 LAST SEMESTER? I WANT TO\r" +
                "TAKE 2 COURSES SO THAT I WILL GRADUATE ASAP!";

        String actual = getFileContent(inputFile3.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: Additional D2 Test
    @Test
    public void mainTest11() throws Exception {
        File inputFile4 = createInputFile4();

        String args[] = {"-o", "-f", inputFile4.getPath()};
        Main.main(args);

        String expected = "Howdy Billy,\n" +
                "This is a test file for the capitalize utility.\n" +
                "let's make sure it has at least a few lines,\n" +
                "so that we can create some \n"
                + "interesting test cases...And let's say \"howdy\" to Bill again!";

        String actual = getFileContent(inputFile4.getPath());

        assertEquals("The file was changed!", expected, actual);

        assertEquals("hOWDY bILLY,\n" +
                "tHIS IS A TEST FILE FOR THE CAPITALIZE UTILITY.\n" +
                "LET'S MAKE SURE IT HAS AT LEAST A FEW LINES,\n" +
                "SO THAT WE CAN CREATE SOME \n"
                + "INTERESTING TEST CASES...aND LET'S SAY \"HOWDY\" TO bILL AGAIN!", outStream.toString().trim());
    }

    // Purpose: Additional D2 Test
    @Test
    public void mainTest12() throws Exception {
        File inputFile5= createInputFile5();

        String args[] = {inputFile5.getPath()};
        Main.main(args);

        String expected = "Bill is," + System.getProperty("line.separator") +
                "In my opinion," + System.getProperty("line.separator") +
                "An easier name to spell than William." + System.getProperty("line.separator") +
                "Bill is shorter," + System.getProperty("line.separator") +
                "And Bill is" + System.getProperty("line.separator") +
                "First alphabetically.";

        String actual = getFileContent(inputFile5.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: Additional D2 Test
    @Test
    public void mainTest13() throws Exception {
        File inputFile7 = createInputFile7();

        String args[] = {"-w", "\\?", inputFile7.getPath()};
        Main.main(args);

        String expected = "123\\456?789\\0ab?Cde\\Fgh?Ijk\\Lmn?Opq\\Rst?Uvw\\Xyz";

        String actual = getFileContent(inputFile7.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: Additional D2 Test
    @Test
    public void mainTest14() throws Exception {
        File inputFile8 = createInputFile8();

        String args[] = {"-w", "|", "-f", "-m", "XYZ", inputFile8.getPath()};
        Main.main(args);

        String expected = "123|456|789|0AB|cDE|fGH|iJK|lMN|oPQ|rST|uVW|xyz";

        String actual = getFileContent(inputFile8.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: Additional D2 Test
    @Test
    public void mainTest15() throws Exception {
        File inputFile6 = createInputFile6();

        String args[] = {"-m", "ABC'S", "-w", inputFile6.getPath()};
        Main.main(args);

        String expected = "Howdy Bill, Have You Learned Your Abc And 123?\r\n" +
                "I Know My ABC'S.\r" +
                "It Is Important To Know Your ABC'S And 123's,\n" +
                "So Repeat With Me: Abc! 123! Abc And 123!";

        String actual = getFileContent(inputFile6.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: Additional D2 Test
    @Test
    public void mainTest16() throws Exception {
        String args[] = {"nofile.txt"};
        Main.main(args);

        assertEquals("File Not Found", errStream.toString().trim());
    }

    // Purpose: Additional D2 Test
    @Test
    public void mainTest17() throws Exception {
        File inputFile1 = createInputFile1();

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

    // Purpose: Additional D2 Test
    @Test
    public void mainTest18() throws Exception {
        File inputFile6 = createInputFile6();

        String args[] = {"-f", "-I", inputFile6.getPath()};
        Main.main(args);

        String expected = "howdy bill, have you learned your abc and 123?\r\n" +
                "i know my abc's.\r" +
                "it is important to know your abc's and 123's,\n" +
                "so repeat with me: abc! 123! abc and 123!";

        String actual = getFileContent(inputFile6.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: Additional D2 Test
    @Test
    public void mainTest19() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-w", "abc", inputFile1.getPath()};
        Main.main(args);

        String expected = "Howdy Billy,\n" +
                "I aM going to taKe cS6300 aNd cS6400 next semester.\n" +
                "Did you taKe cS 6300 laSt semester? I waNt to\n" +
                "taKe 2 cOurses so thaT I will graDuaTe AsaP!";

        String actual = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: Additional D2 Test
    @Test
    public void mainTest20() throws Exception {
        File inputFile3 = createInputFile3();

        String args[] = {"-f", "-f", "-f", inputFile3.getPath()};
        Main.main(args);

        String expected = "hOWDY bILLY,\n" +
                "i AM GOING TO TAKE CS6300 AND CS6400 NEXT SEMESTER.\r\n" +
                "dID YOU TAKE CS 6300 LAST SEMESTER? i WANT TO\r" +
                "TAKE 2 COURSES SO THAT i WILL GRADUATE aSAP!";

        String actual = getFileContent(inputFile3.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: Additional D2 Test
    @Test
    public void mainTest21() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-w", "a", "-w", "bc",  inputFile1.getPath()};
        Main.main(args);

        String expected = "Howdy Billy,\n" +
                "I aM going to taKe cS6300 aNd cS6400 next semester.\n" +
                "Did you taKe cS 6300 laSt semester? I waNt to\n" +
                "taKe 2 cOurses so thaT I will graDuaTe AsaP!";

        String actual = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: Additional D2 Test
    @Test
    public void mainTest22() throws Exception {
        File inputFile5 = createInputFile5();

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

    // Purpose: Additional D2 Test
    @Test
    public void mainTest23() throws Exception {
        File inputFile9 = createInputFile9();

        String args[] = {"-f", inputFile9.getPath()};
        Main.main(args);

        String expected = " ";

        String actual = getFileContent(inputFile9.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: Additional D2 Test
    @Test
    public void mainTest24() throws Exception {
        File inputFile7 = createInputFile7();

        String args[] = {"-w", inputFile7.getPath()};
        Main.main(args);

        String expected = "123\\456?789\\0ab?cde\\fgh?ijk\\lmn?opq\\rst?uvw\\xyz";

        String actual = getFileContent(inputFile7.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: Additional D2 Test
    @Test
    public void mainTest25() throws Exception {
        File inputFile2 = createInputFile2();

        String args[] = {"-w", "-f", inputFile2.getPath()};
        Main.main(args);

        String expected = "aAAAAA";

        String actual = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected, actual);
    }



}