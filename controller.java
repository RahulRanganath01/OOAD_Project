import mymodel.model;

import java.util.*;
import java.io.*;
import java.net.*;
import java.time.*;
import java.util.concurrent.CountDownLatch;
import java.text.*;
import java.sql.*;
import java.util.Date;

public class controller
{
    public static int num_per_ter = 0;
    public static int num_per_mobile_ter = 0;
    public static int count = 0;
    public static int count2 = 0;
    public static int tracker = 0;
    public static int tracker2 = 0;
    public static int full_check;
    public static void main(String[] args)
    {
        int num_ter;
        num_ter = Integer.parseInt(args[0]);
        num_per_ter = Integer.parseInt(args[1]);
        num_per_mobile_ter = Integer.parseInt(args[2]);
        //Scanner sc = new Scanner(System.in);
        //System.out.print("Enter the number of terminals : ");
        //int time = (num_ter * 10)*1000;
        int num_mob_ter;
        //int next_terminal = num_ter + 1;
        Thread[] terminal_threads = new Thread[num_ter];

        if(num_ter % 2 == 0)
        {
            num_mob_ter = (num_ter)/2;
        }
        else
        {
            num_mob_ter = (num_ter + 1)/2;
        }

        //int time2 = (num_ter * 5 + num_mob_ter )*1000;
        full_check = num_mob_ter;

        System.out.print("The number of terminals is : ");
        System.out.println(num_ter);
        System.out.print("The maximum number of people in a terminal is : ");
        System.out.println(num_per_ter);
        System.out.print("The number of mobile terminals is : ");
        System.out.println(num_mob_ter);
        System.out.print("The maximum number of people in a mobile terminal : ");
        System.out.println(num_per_mobile_ter);
        //CountDownLatch latch1 = new CountDownLatch(num_ter - 1);

        for(int i = 0; i < terminal_threads.length; i++)
        {
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }

            count++;
            terminal_threads[i] = new myThread();
            //thread.setName(Integer.toString(i));
            //latch1.countDown();
            terminal_threads[i].start();
        }

        for (Thread thread : terminal_threads)
        {
            try
            {
                thread.join();
            }
            catch (InterruptedException e)
            {
                // Handle the exception by printing an error message
                //System.err.println("Thread interrupted while waiting for completion.");
                e.printStackTrace();
            }
        }

        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        if(tracker == count)
        {
            count ++;
            Thread thread = new myThread();
            thread.start();
        }
        // try
        // {
        //     // Wait for the thread to finish by waiting for the latch to count down to zero
        //     latch1.await();
        // }
        // catch (InterruptedException e)
        // {
        //     // Handle the InterruptedException appropriately
        //     e.printStackTrace();
        // }

        try
        {
            Thread.sleep(30000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        //System.out.println("testing post thread");

        //CountDownLatch latch2 = new CountDownLatch(num_mob_ter - 1);

        Thread[] mobile_threads = new Thread[num_mob_ter];

        if(tracker == count)
        {
            for(int i = 0; i <mobile_threads.length; i++)
            {
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }

                count2++;
                mobile_threads[i] = new new_myThread();
                //smallThread.setName(Integer.toString(i));
                //latch2.countDown();
                mobile_threads[i].start();
            }
        }

        if(tracker == count)
        {
            //count++;
            for (Thread thread : mobile_threads)
            {
                try
                {
                    thread.join();
                }
                catch (InterruptedException e)
                {
                    // Handle the exception by printing an error message
                    //System.err.println("Thread interrupted while waiting for completion.");
                    e.printStackTrace();
                }
            }

        }

        // try
        // {
        //     // Wait for the thread to finish by waiting for the latch to count down to zero
        //     latch2.await();
        // }
        // catch (InterruptedException e)
        // {
        //     // Handle the InterruptedException appropriately
        //     e.printStackTrace();
        // }

        // try
        // {
        //     Thread.sleep(time2);
        // }
        // catch(InterruptedException e)
        // {
        //     e.printStackTrace();
        // }

        if(controller.tracker2 == controller.full_check)
        {
            System.out.println("All POS terminals are full !!!!");
        }

        Scanner sc = new Scanner(System.in);
        String nameoffile = null;
        int temporary = 0;
        String ter_name = null;
        System.out.print("Enter 1 for terminal and 2 for mobile terminal : ");
        temporary = sc.nextInt();
        sc.nextLine();
        switch(temporary)
        {
            case 1:
                System.out.print("Enter the terminal number : ");
                ter_name = sc.nextLine();
                nameoffile = "CrowdTerminalNo" + ter_name + ".txt";
                break;
            case 2:
                System.out.print("Enter the terminal number : ");
                ter_name = sc.nextLine();
                nameoffile = "CrowdMobileTerminalNo" + ter_name + ".txt";
                break;
            default:
            {
                System.out.println("Invalid number");
            }
        }
        sc.nextLine();
        //System.out.print("Enter the name of file from which to retrieve the text : ");
        System.out.print("Enter the datetime for retrieval of data : ");
        String val = sc.nextLine();
        System.out.println("The data which corresponds to the mentioned date time from " + ter_name +" is : ");
        model.retrieve(nameoffile, val);
    }
}

class myThread extends Thread
{
    public void run()
    {
        String ter_no = Integer.toString(controller.count);
        String fileName = "CrowdTerminalNo" + ter_no + ".txt";
        PrintWriter printWriter = null;
        try
        {
            ter_no = Integer.toString(controller.count);
            fileName = "CrowdTerminalNo" + ter_no + ".txt";
            printWriter = model.createPrintWriter(fileName);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        // FileWriter fileWriter = null;
        // PrintWriter writer = null;
        //String ter_no = Integer.toString(controller.count);
        int num_people = 0;
        int random_number;
        // System.setOut(controller.ps);
        // String fileName = "CrowdTerminalNo" + ter_no + ".txt";
        // try
        // {
        //     fileWriter = new FileWriter(fileName);
        //     writer = new PrintWriter(fileWriter);
        // }
        // catch (IOException e)
        // {
        //     //System.out.println("An error occurred while writing to the file.");
        //     e.printStackTrace();
        // }
        int temp = 1;
        int temp2 = 0;
        while(num_people <= controller.num_per_ter)
        {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat DateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date(timestamp.getTime());
            String Date_Time_format = DateFormat.format(date);
            Random rand = new Random();
            random_number = rand.nextInt(5);
            if(temp % 3 == 0)
            {
                num_people = num_people - random_number;
            }
            else
            {
                num_people = num_people + random_number;
            }


            if(num_people < 0)
            {
                temp2  = temp2 + 1;
                System.out.println("Terminal " + ter_no + " is temporarily closed");
                printWriter.println("Terminal " + ter_no + " is temporarily closed");
            }

            if(temp2 >= 3)
            {
                System.out.println("Terminal " + ter_no + " is closed");
                printWriter.println("Terminal " + ter_no + " is closed");
                return;
            }

            //System.out.println("no of people in POS terminal " + ter_no + " is " + Integer.toString(num_people) + " at " + Date_Time_format);
            //System.out.println(num_people);

            printWriter.println("no of people in POS terminal " + ter_no + " is " + Integer.toString(num_people) + " at " + Date_Time_format);

            temp = temp + 1;
            //printWriter.close();
            try
            {
                Thread.sleep(1000); // pause for 1 seconds
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        if(num_people > controller.num_per_ter)
        {
            controller.tracker = controller.tracker + 1;
            System.out.println("Terminal " + ter_no + " is full");
            //PrintWriter printWriter = model.createPrintWriter(fileName);
            printWriter.println("Terminal " + ter_no + " is full");
            printWriter.close();
            //controller.latch1.countDown();
            // try
            // {
            //     if(fileWriter != null)
            //     {
            //         fileWriter.close();
            //     }
            // }
            // catch(IOException e)
            // {
            //     e.printStackTrace();
            // }
            return;
        }
        //ps.close();
    }
}


class new_myThread extends Thread
{
    public void run()
    {
        String mob_ter_no = Integer.toString(controller.count2);
        String fileName = "CrowdMobileTerminalNo" + mob_ter_no + ".txt";
        PrintWriter printWriter = null;
        // FileWriter fileWriter = null;
        // PrintWriter writer = null;
        try
        {
            mob_ter_no = Integer.toString(controller.count2);
            fileName = "CrowdMobileTerminalNo" + mob_ter_no + ".txt";
            printWriter = model.createPrintWriter(fileName);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        //String mob_ter_no = Integer.toString(controller.count2);
        int num_people = 0;
        int random_number;
        //String fileName = "CrowdMobileTerminalNo" + mob_ter_no + ".txt";
        // try
        // {
        //     fileWriter = new FileWriter(fileName);
        //     writer = new PrintWriter(fileWriter);
        // }
        // catch (IOException e)
        // {
        //     //System.out.println("An error occurred while writing to the file.");
        //     e.printStackTrace();
        // }
        int temp = 1;
        int temp2 = 0;
        while(num_people <= controller.num_per_mobile_ter)
        {
            // String timestamp = Long.toString(System.currentTimeMillis());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat DateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date(timestamp.getTime());
            String Date_Time_format = DateFormat.format(date);
            Random rand = new Random();
            random_number = rand.nextInt(3);
            if(temp % 3 == 0)
            {
                num_people = num_people - random_number;
            }
            else
            {
                num_people = num_people + random_number;
            }

            if(num_people < 0)
            {
                temp2  = temp2 + 1;
                System.out.println("Moble Terminal " + mob_ter_no + " is temporarily closed");
                printWriter.println("Mobile Terminal " + mob_ter_no + " is temporarily closed");
            }

            if(temp2 >= 3)
            {
                System.out.println("Mobile Terminal " + mob_ter_no + " is closed");
                printWriter.println("Mobile Terminal " +  mob_ter_no + " is closed");
                return;
            }
            //System.setOut(controller.ps);
            //System.out.println("no of people in mobile POS terminal " + mob_ter_no + " is " + Integer.toString(num_people) + " at " + Date_Time_format);
            //System.out.println(num_people);
            printWriter.println("no of people in mobile POS terminal " + mob_ter_no + " is " + Integer.toString(num_people) + " at " + Date_Time_format);
            //printWriter.close();
            temp = temp + 1;
            try
            {
                Thread.sleep(1000); // pause for 1 seconds
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        if(num_people > controller.num_per_mobile_ter)
        {
            controller.tracker2 = controller.tracker2 + 1;
            System.out.println("Mobile terminal " + mob_ter_no + " is full");
            //PrintWriter printWriter = model.createPrintWriter(fileName);
            printWriter.println("Terminal " + mob_ter_no + " is full");
            printWriter.close();
            // controller.latch2.countDown();
            // try
            // {
            //     if(fileWriter != null)
            //     {
            //         fileWriter.close();
            //     }
            // }
            // catch(IOException e)
            // {
            //     e.printStackTrace();
            // }
            return;
        }
        //ps.close();
    }
}
