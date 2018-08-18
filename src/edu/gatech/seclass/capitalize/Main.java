package edu.gatech.seclass.capitalize;
import java.io.*;
import java.nio.file.*;

public class Main
{
    public static void main(String[] args) throws IllegalArgumentException
    {

        String arg = "";
        String result = "";
        String file_content = "";
        String w_content = "";
        String m_content = "";
        Boolean write_back_to_file = true;
        int i = 0;
        boolean valid_opt = true;
        boolean file_exist = true;
        //check the args if no args
        if(args == null || args.length == 0)
        {
            usage();
            //System.out.println("1");
        }
        else
        {

            String filename = args[args.length -1];
            //if the filename is not right format

            if(filename == null || filename.length() == 0)
                file_not_found();

            if(filename.startsWith("-"))
            {
                usage();
                //System.out.println("2");
            }
            else
            {
                //read the file to file_content
                try
                {
                    Path file_path = Paths.get(filename);

                    if(Files.exists(file_path))
                    {
                        file_content = read_file_to_string(filename);
                    }
                    else
                    {
                        file_not_found();
                        file_exist = false;
                    }

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                //case 1: capitalize file1.txt [no opts]
                //capitalizes the first character in each line in the file (if it is a letter).
                //check opt has or not
                if(args.length == 1 && file_exist)
                {
                    result = empty_opt(file_content);
                }
                //check -i/I; apply the first

                while (i < args.length - 1 && file_exist)
                {

                    arg = args[i].trim();

                    if(arg.length() == 0 || arg.isEmpty())
                        valid_opt = false;

                    if(arg.equals("-I"))
                    {
                       result = i_opt(file_content,"-I");
                    }
                    else if(arg.equals("-i"))
                    {
                        result = i_opt(file_content,"-i");
                    }
                    i++;
                }
                i = 0;
                //check the command
                while (i < args.length - 1 && file_exist)
                {


                    arg = args[i].trim();

                    if(arg.equals("-w"))
                    {
                        //i+1 is the filename or next opt then w_content is ""
                        if( (i+1) == (args.length-1) || args[i+1].startsWith("-") )
                        {
                            //result is empty
                            if (result.length() == 0)
                                result = w_opt(file_content,"");
                            else
                                result = w_opt(result,"");
                        }
                        else
                        {
                            w_content = args[i+1];
                            //result is empty
                            if (result.length() == 0)
                                result = w_opt(file_content,w_content);
                            else
                                result = w_opt(result,w_content);
                            i++;
                        }
                    }
                    else if(arg.equals("-m"))
                    {
                        //get m_content
                        if( (i+1) != (args.length-1) && !args[i+1].startsWith("-"))
                        {
                            m_content = args[i+1];

                            //result is empty
                            if (result.length() == 0)
                                result = m_opt(file_content,m_content);
                            else
                                result = m_opt(result,m_content);
                            i++;
                        }
                        else
                        {
                            if (result.length() == 0)
                                result = file_content;
                        }
                    }
                    else if(arg.equals("-o"))
                    {
                        write_back_to_file = false;
                    }
                    else if(arg.equals("-i") || arg.equals("-I") || arg.equals("-f"))
                    {
                        //do nothing here
                    }
                    else
                    {
                        valid_opt = false;
                        break;
                    }
                    ++i;
                }
                //CHECK -F, APPLY TO THE LAST
                i = 0;
                //String pattern = "-[iImwof]";
                while (i < args.length - 1 && file_exist)
                {
                    arg = args[i].trim();
                    if(arg.equals("-f"))
                    {
                        if (result.length() == 0)
                            result = f_opt(file_content);
                        else
                            result = f_opt(result);
                    }
                    i++;
                }
                //there has some invalid opts
                if(valid_opt == false)
                {
                    usage();
                }
                else
                {
                    //write String to file
                    if(file_exist)
                    {
                        if(write_back_to_file)
                            write_string_to_file(result,filename);
                        else
                            System.out.println(result);
                    }


                }





            }
        }
    }


    private static String read_file_to_string(String filename) throws IOException
    {

        String file_content = "";

        try
        {
            Path file_path = Paths.get(filename);
            byte[] encoded = Files.readAllBytes(file_path);
            file_content = new String(encoded, "UTF-8");


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return file_content;

    }
//no opts
    //If none of the OPT flags is specified,
// capitalize capitalizes the first character in each line in the file.
    private static String empty_opt(String file_content)
    {
        char[] chars = file_content.toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++)
        {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            }
            else if ( chars[i] == '\n' || chars[i] == '\r')
            {
                //\n next word first letter should be a letter
                if(i+1 < chars.length && Character.isLetter(chars[i+1]))
                    found = false;
            }
        }
        return String.valueOf(chars);
    }
    //capitalize -w file1.txt
    //capitalizes the first letter in every whitespace delimited word.
    //capitalize -w “abc” file1.txt
    //would capitalize the first character in every word (if it is a letter),
    // where a word is any string of characters terminated by ‘a’, ‘b’, ‘c’, or the end of the file.
    private static String w_opt(String file_content, String w_content)
    {
        //w_content is empty
        if(w_content.length() == 0)
        {
            char[] chars = file_content.toCharArray();
            boolean found = false;
            for (int i = 0; i < chars.length; i++)
            {
                //check this is a word or not
                if(found == false && !Character.isLetter(chars[i]))
                    found = true;

                //front is a whitespace
                if (!found && Character.isLetter(chars[i]))
                {
                    chars[i] = Character.toUpperCase(chars[i]);
                    found = true;
                }
                else if (Character.isWhitespace(chars[i]) )
                { // You can add other chars here
                    found = false;
                }

            }
            return String.valueOf(chars);
        }
        else
        {
            //check w_content,would capitalize the first character in every word (if it is a letter),
            char[] chars = file_content.toCharArray();
            boolean found = true;
            for (int i = 0; i < chars.length; i++)
            {
                if (!found && Character.isLetter(chars[i])) {
                    chars[i] = Character.toUpperCase(chars[i]);
                    found = true;
                }
                else if (w_content.indexOf(chars[i]) != -1)
                {
                    found = false;
                }
                //if the next char is not a letter then we jump
                if(found == false && !Character.isLetter(chars[i+1]) && (i+1) <chars.length)
                    found = true;
            }
            return String.valueOf(chars);
        }
    }

    //-m <string>: if specified, the utility will change the capitalization of all the sequences of characters that match (in a case insensitive way)
    // the provided string so that the capitalization matches that of string (see examples).

    private static String m_opt(String file_content, String w_content)
    {
        String result = "";
        while(file_content.length() != 0)
        {
            int index = file_content.toLowerCase().indexOf(w_content.toLowerCase());

            if(index != -1)
            {
                result = result + file_content.substring(0,index) + w_content;
                file_content = file_content.substring(index + w_content.length());
            }
            else
            {
                result = result + file_content;
                break;
            }
        }
        return result;
    }

    //-f: if specified, the capitalize utility will flip the capitalization of all letters in the file,
    // after applying any other transformation.

    private static String f_opt(String file_content)
    {
        char[] chars = file_content.toCharArray();
        for (int i = 0; i < chars.length; i++)
        {
            char c = chars[i];
            if (Character.isUpperCase(c))
            {
                chars[i] = Character.toLowerCase(c);
            }
            else if (Character.isLowerCase(c))
            {
                chars[i] = Character.toUpperCase(c);
            }
        }
        return String.valueOf(chars);
    }

    //-i / -I:  if specified,
    // the capitalize utility will make all letters in the file lower case (for -i) or upper case (for -I)
    // before applying any other transformation.
    private static String i_opt(String file_content, String type)
    {
        char[] chars = file_content.toCharArray();

        //lower case
        if(type.equals("-i"))
        {
            for (int i = 0; i < chars.length; i++)
            {
                char c = chars[i];
                chars[i] = Character.toLowerCase(c);
            }

        }
        //upper case
        if(type.equals("-I"))
        {
            for (int i = 0; i < chars.length; i++)
            {
                char c = chars[i];
                chars[i] = Character.toUpperCase(c);
            }
        }

        return String.valueOf(chars);

    }


    private static void write_string_to_file(String result, String filename)
    {
        try (PrintWriter out = new PrintWriter(filename))
        {
            out.print(result);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    private static void usage()
    {
        System.err.println("Usage: Capitalize  [-w [string]] [-m string] [-f] [-i|-I] [-o] <filename>");
    }

    private static void file_not_found()
    {
        System.err.println("File Not Found");
    }

}