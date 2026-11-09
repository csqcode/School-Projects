## Summary
This program uses an AVL Tree data type to store strings. It then executes a series of statements from a file while performing a trace of the execution.

## How to Run
This program takes two arguments: order code and a file 

Order Codes: 
- 0 ordered by increasing string length, primary key, and reverse lexicographical order, secondary key
- -1 for reverse lexicographical order
- 1 for lexicographical order
- -2 ordered by decreasing string
- 2 ordered by increasing string
- -3 ordered by decreasing string length, primary key, and reverse lexicographical order, secondary key
- 3 ordered by increasing string length, primary key, and lexicographical order, secondary key

File:
Must be a file containing strings that will be used as commands for the AVLTree

Valid Commands:
- "insert x": Inserts x into the AVL tree
- "delete x": Removes x from the AVL tree
- "traverse": Prints an in-order traversal of the AVL tree
- "paths": Generates all root-to-leaf paths in the tree, one line for each path. Also prints the total number of these paths.
- "stats": Prints the size, height, number of full nodes, and whether or not the tree is a fibonacci tree.
- Any other commands will throw a file parsing error



## File Breakdown
| File | Function |
| --- | --- |
| AVLTree.java | Creates the AVLTree class.|
| AVLTreeAPI.java | Contains the API and methods for the AVLTree class. |
| Dendrologist.java | Main method of the program. Takes a file of strings and executes them on an AVL Tree |

