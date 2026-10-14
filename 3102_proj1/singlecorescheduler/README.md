## Summary
This project schedules simulated CPU jobs on a single-core processor. It functions in two modes, random and file. Random mode will randomly add jobs with randomized attributes. File mode will read jobs from a text file and parse them into objects. A priority queue ADT sorts the available jobs, and jobs with the lowest priority will be placed highest in the queue. The program completes when all cycles are ran.

## How to Run
This program takes three arguments: # of CPU cycles (int), -f/-r (file/random), and a third argument depending on chosen mode

Running with a file:
- Ensure the file is located in the same folder as the singlecorescheduler package
- Each line in the file should correlate to one object
- Each line should have the following format: pid priority #_of_cycles arrival_cycle
- Arguments will be: # of CPU cycles (int), -f, "fileName"

Running randomly:
- Arguments will be: # of CPU cycles (int), -r, x (any double 0 < x <= 1)
- x represents a double that will be compared against a random number generated each CPU cycle
- If the random number is less than or equal to x, a new process will be added to the queue. 
