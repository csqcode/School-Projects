package singlecorescheduler;

/**
 * A application to simulate a non-preemptive scheduler for a single-core CPU
 * using a heap-based implementation of a priority queue
 * @author William Duncan, Cole Cadarette
 * @see PQueue.java, PCB.java
 * <pre>
 * DATE: 9/29/2026
 * File:SingleCoreScheduler.java
 * Course: csc 3102
 * Programming Project # 1
 * Instructor: Dr. Duncan
 * Usage: SingleCoreScheduler <number of cylces> <-R or -r> <probability of a  process being created per cycle>  or,
 *        SingleCoreScheduler <number of cylces> <-F or -f> <file name of file containing processes>,
 *        The simulator runs in either random (-R or -r) or file (-F or -f) mode 
 * </pre>
 */


import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class SingleCoreScheduler
{
    /**
     * Single-core processor with non-preemptive scheduling simulator    
     * @param args an array of strings containing command line arguments
     * args[0] - number of cyles to run the simulation
     * args[1] - the mode: -r or -R for random mode and -f or -F for file mode
     * args[2] - if the mode is random, this entry contains the probability that
     * a process is created per cycle and if the simulator is running in
     *           file mode, this entry contains the name of the file containing the 
     *           the simulated jobs. In file mode, each line of the input file is  
     *           in this format: 
     * <process ID> <priority value> <cycle of process creation> <time required to execute> 
     */
    public static void main(String []args) throws PQueueException, IOException
    {
        int totalProcesses = 0;
        double turnaround = 0;
        double wait = 0;

        if (args.length != 3)
        {
            System.out.println("Usage: SingleCoreScheduler <number of cylces> <-R or -r> <probability of a  process being created per cycle>  or ");
            System.out.println("       SingleCoreScheduler <number of cylces> <-F or -f> <file name of file containing processes>");
            System.out.println("The simulator runs in either random (-R or -r) or file (-F or -f) mode.");
            System.exit(1);
        }
		//Complete the implementation of this method

        //Queue
        PQueue<PCB> rQueue = new PQueue<>();

        //Cycles
        int cycles = Integer.parseInt(args[0]);

        //Random mode
        if (args[1].toLowerCase().equals("-r"))
        {
            double probability = Double.parseDouble(args[2]);
            int pid = 0;
            int priority = 0;
            int burst = 0;
            int endBurst = 0;
            boolean burstOver = true; 
            PCB currentPCB = new PCB(0,0,0,0,0);
            PCB zeroPCB = new PCB(0,0,0,0,0);


            for (int i = 0; i < cycles; i++)
            {
                //Default seeds with time of day
                Random random = new Random();
                double newProb = random.nextDouble();

            System.out.println("*** Cycle #: " + i);

                if(rQueue.isEmpty() && currentPCB.getPid() == 0)
                    System.out.println("The CPU is idle.");
                else if(!(currentPCB).isExecuting() && burstOver) 
                {
                    currentPCB = rQueue.peek();
                    currentPCB.execute();
                    endBurst = (i + currentPCB.getBurst());
                    System.out.println("Process #" + currentPCB.getPid() + " is executing.");
                    currentPCB.setWait(i - currentPCB.getArrival());
                    burstOver = false;
                    rQueue.remove();
                }
                else if(i == endBurst)
                {
                    System.out.println("Process #" + currentPCB.getPid() + " has just terminated.");
                    turnaround += (currentPCB.getWait() + currentPCB.getBurst());
                    wait += currentPCB.getWait();
                    currentPCB = zeroPCB;
                    burstOver = true;
                }
                else{
                    System.out.println("Process #" + currentPCB.getPid() + " is executing.");
                }
                
                if(newProb <= probability)
                {
                    pid++;
                    priority = random.nextInt(40)-20;
                    burst = random.nextInt(100);

                    PCB addition = new PCB(pid, priority, 0, i, burst);
                    rQueue.add(addition);
                    System.out.println("Adding job with pid #" + addition.getPid() + " and priority " + addition.getPriority() + " and burst " + addition.getBurst() + ".");
                    totalProcesses++;  
                }
                else{
                    System.out.println("No new job this cycle.");
                }

            }
        }

        //File mode
        if (args[1].toLowerCase().equals("-f"))
        {
            FileReader stuff = new FileReader(args[2]);
            int pid = 0;
            int priority = 0;
            int arrival = 0;
            int burst = 0;
            Scanner input = new Scanner(stuff);
            int endBurst = 0;
            int entry = 0;
            boolean burstOver = true;
            PCB currentPCB = new PCB(0,0,0,0,0);
            PCB zeroPCB = new PCB(0,0,0,0,0);
            boolean addFinal = false;
            

            for(int i = 0; i < cycles; i++)
            {
                System.out.println("*** Cycle #: " + i);

                if(rQueue.isEmpty() && currentPCB.getPid()==0)
                    System.out.println("The CPU is idle.");

                else if(!(currentPCB).isExecuting() && burstOver)
                {
                    
                    currentPCB = rQueue.peek();
                    currentPCB.execute();
                    endBurst = (i + currentPCB.getBurst());
                    System.out.println("Process #" + currentPCB.getPid() + " is executing.");
                    currentPCB.setWait(i - currentPCB.getArrival());
                    burstOver = false;
                    rQueue.remove();
                }
                else if(i == endBurst)
                {
                    System.out.println("Process #" + currentPCB.getPid() + " has just terminated.");
                    turnaround += (currentPCB.getWait() + currentPCB.getBurst());
                    wait += currentPCB.getWait();
                    currentPCB = zeroPCB;
                    burstOver = true;
                }
                else{
                    System.out.println("Process #" + currentPCB.getPid() + " is executing.");
                }

                

                while(input.hasNextInt() || addFinal == true) 
                {
                    
                    if(i == 0)
                    {
                    pid = input.nextInt();  
                    priority = input.nextInt();
                    arrival = input.nextInt();
                    burst = input.nextInt(); 
                    entry = arrival; 
                    }

                    if(i == entry)
                    {
                        PCB addition = new PCB(pid, priority, 0, arrival, burst);
                        rQueue.add(addition);
                        System.out.println("Adding job with pid #" + addition.getPid() + " and priority " + addition.getPriority() + " and burst " + addition.getBurst() + ".");
                        totalProcesses++;
                    
                        if(input.hasNext()){
                    pid = input.nextInt();  
                    priority = input.nextInt();
                    arrival = input.nextInt();
                    burst = input.nextInt(); 
                    entry = arrival;
                    addFinal = false;
                        }

                    if(!input.hasNext())
                    {
                        addFinal = true;
                    }
                    break; 
                    }    

                    else{
                        System.out.println("No new job this cycle.");
                        break;
                    }
                    
                    
                  }
                 
                  
                }

            }
                
              double averageProcess = (double)totalProcesses/cycles;
        double averageTurn = turnaround/totalProcesses;
        double averageWait = (double)wait/totalProcesses;

            //Note: The directions want the average turnaround time, however the sample output has average throughput time
        System.out.println("The average number of process created per cycle is " + averageProcess + ".");
        System.out.println("The average turnaround time is " + averageTurn + ".");
        System.out.println("The average wait time per process is " + averageWait + "."); 
        }

        
        

        
       

    
    }










