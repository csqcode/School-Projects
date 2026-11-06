package dendrologist;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * A testbed for an augmented implementation of an AVL tree
 * @author William Duncan, Cole Cadarette
 * @see AVLTreeAPI
 * <pre>
 * Date: 10/29/2026
 * Course: CSC 3102 
 * Programming Project # 2
 * Instructor: Dr. Duncan 
 * </pre>
 */
public class Dendrologist
{
    public static void main(String[] args) throws FileNotFoundException 
    {
        String usage = "Dendrologist <order-code> <command-file>\n";
        usage += "  <order-code>:\n";
        usage += "  0 ordered by increasing string length, primary key, and reverse lexicographical order, secondary key\n";
        usage += "  -1 for reverse lexicographical order\n";
        usage += "  1 for lexicographical order\n";
        usage += "  -2 ordered by decreasing string\n";
        usage += "  2 ordered by increasing string\n";
        usage += "  -3 ordered by decreasing string length, primary key, and reverse lexicographical order, secondary key\n";
        usage += "  3 ordered by increasing string length, primary key, and lexicographical order, secondary key\n";      
        if (args.length != 2)
        {
            System.out.println(usage);
            throw new IllegalArgumentException("There should be 2 command line arguments.");
        }
            int cmpType = Integer.parseInt(args[0]);
            Comparator<String> cmp = Comparator.comparingInt((String s) -> s.length()).thenComparing((a,b) -> b.compareTo(a));
            FileReader words = new FileReader(args[1]);
            Scanner input = new Scanner(words);

            switch(cmpType){
                case 0:
                cmp = Comparator.comparingInt((String s) -> s.length()).thenComparing((a,b) -> b.compareTo(a));
                break;
                case 1:
                cmp = (a,b) -> a.compareTo(b);
                break;
                case -1:
                cmp = (a,b) -> b.compareTo(a);
                break;
                case 2:
                cmp = (a,b) -> Integer.compare(a.length(),b.length());
                break;
                case -2:
                cmp = (a,b) -> Integer.compare(b.length(),a.length());
                break;
                case 3:
                cmp = Comparator.comparingInt((String s) -> s.length()).thenComparing((a,b) -> a.compareTo(b));
                break;
                case -3:
                cmp = Comparator.comparingInt((String s) -> s.length()).reversed().thenComparing((a,b) -> b.compareTo(a));
                break;
            }

            AVLTree<String> avl = new AVLTree<>(cmp);

            while(input.hasNext())
            {
                String command = input.next();

                switch(command)
                {
                    case "insert":
                        String newWord = input.next();
                        avl.insert(newWord);
                        System.out.println("Inserted: " + newWord);
                        break;
                    case "delete":
                        String removeWord = input.next();
                        avl.remove(removeWord);
                        System.out.println("Deleted: " + removeWord);
                        break;
                    case "traverse":
                        System.out.println("In-Order Traversal:");
                        avl.traverse(item -> {
                            System.out.println(item);
                            return item;
                          });
                        break;
                    case "paths":
                        ArrayList paths = new ArrayList<>();
                        paths = avl.genPaths();
                        System.out.println("#Root-to-Leaf Paths: " + paths.size());
                        for(int i = 0; i < paths.size(); i++)
                        {
                            System.out.println(paths.get(i));
                        }
                        break;
                    case "stats":
                        int size = avl.size();
                        int height = avl.height();
                        int fullNodes = avl.fullCount();
                        boolean fibonacci = avl.isFibonacci();
                        System.out.println("Stats: size = " + size + ", height = " + height + ", #full-nodes = " + fullNodes + ", fibonacci? = " + fibonacci);
                        break;
                    default:
                        throw new IllegalArgumentException("File Parsing Error");

                }
            
            
            }
    }   
}
