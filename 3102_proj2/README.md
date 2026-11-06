## Summary
This program uses an AVL Tree data type to store strings. It then executes a series of statements from a file while performing a trace of the execution.

## How to Run
This program takes two arguments: order code and file

Order Codes: 
- 0 ordered by increasing string length, primary key, and reverse lexicographical order, secondary key
- -1 for reverse lexicographical order
- 1 for lexicographical order
- -2 ordered by decreasing string
- 2 ordered by increasing string
- -3 ordered by decreasing string length, primary key, and reverse lexicographical order, secondary key
- 3 ordered by increasing string length, primary key, and lexicographical order, secondary key 



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
