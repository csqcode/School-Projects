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
| AVLTree.java | Creates the AVLTree class.|
| AVLTreeAPI.java | Contains the API and methods for the AVLTree class. |
| Dendrologist.java | Main method of the program. Takes a file of strings and executes them on an AVL Tree |

