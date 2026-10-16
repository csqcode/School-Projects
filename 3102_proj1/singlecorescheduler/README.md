## Summary
This project schedules simulated CPU jobs on a single-core processor. It functions in two modes, random and file. Random mode will randomly add jobs with randomized attributes. File mode will read jobs from a text file and parse them into objects. A priority queue ADT sorts the available jobs, and jobs with the lowest priority will be placed highest in the queue. The program completes when all cycles are ran. Once finished, the program will print the average number of processes created per cycle, average turnaround time, and average wait time per process.

## How to Run
This program takes three arguments: # of CPU cycles (int), -f/-r (file/random), and a third argument depending on chosen mode

Running with a file:
- Ensure the file is located in the same folder as the singlecorescheduler package (should not be inside singlecorescheduler)
- Each line in the file should correlate to one object
- Each line should have the following format: pid priority #_of_cycles arrival_cycle
- Arguments will be: # of CPU cycles (int), -f, "fileName"
- A sample file (SimulatedJobs.txt) is included in this repo)

Running randomly:
- Arguments will be: # of CPU cycles (int), -r, x (any double 0 < x <= 1)
- x represents a double that will be compared against a random number generated each CPU cycle
- If the random number is less than or equal to x, a new process will be added to the queue.
- Object properties will be filled out with random values before being added to the queue.


## File Breakdown
| File | Function |
| --- | --- |
| PCB.java | Creates the Process Control Block (PCB) class. This creates the objects that represent the individual jobs.|
| PQueue.java | Creates the PQueue class, implementing a min-heap priority queue ADT. |
| PQueueAPI.java | Specifies basic operations of a PQueue. |
| PQueueException.java | Reports PQueue exception with a specified message. |
| PQueueExceptionAPI.java | Specifies the toString method of the PQueueException. |
| SingleCoreScheduler.java | Simulates the scheduler of a single-core CPU by implementing a priority queue. Contains the main method of the program. |
| SimulatedJobs.txt | File containing 97 sample jobs to be used for running the program. |
