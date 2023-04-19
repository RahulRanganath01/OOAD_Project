package mymodel;

import java.util.*;
import java.io.*;
import java.net.*;
import java.time.*;
import java.util.concurrent.CountDownLatch;
import java.text.*;
import java.sql.*;
import java.util.Date;

public class model
{
    public static synchronized PrintWriter createPrintWriter(String filename) throws IOException
    {
        File file = new File(filename);
        FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        return printWriter;
    }

    public static void retrieve(String fileName, String substringToMatch)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (line.contains(substringToMatch))
                {
                    System.out.println(line);
                }
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
