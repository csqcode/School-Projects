package singlecorescheduler;
import java.util.*;


/**
 * This class describes a priority min-queue that uses an array-list-based min binary heap 
 * that implements the PQueueAPI interface. The array list holds objects that implement 
 * the parameterized Comparable interface.
 * @author Duncan, Cole Cadarette
 * <pre>
 * Date: 9/29/2026
 * course: csc 3102
 * Programming Project: 1
 * Instructor: Dr. Duncan
 *
 * DO NOT REMOVE THIS NOTICE (GNU GPL V2):
 * Contact Information: duncanw@lsu.edu
 * Copyright (c) 2026 William E. Duncan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/> 
 * <pre>
 */
 
 
 /**
  * A min-priority queue implementation that uses an 
  * array list as its data container
  * @param <E> the priority queue element type.   
  */
public class PQueue<E extends Comparable<E>> implements PQueueAPI<E>
{    
   /**
    * A complete tree stored in an array list representing the 
    * binary heap
    */
   private ArrayList<E> tree;

   /**
    * A comparator lambda function that compares two elements of this
    * heap when rebuilding it; cmp.compare(x,y) gives 
    * 1. negative when x less than y
    * 2. positive when x greater than y 
    * 3. 0 when x equal y
    */   
   private Comparator<? super E> cmp;
   
   /**
    * Constructs an empty PQueue using the compareTo method of its data type as the 
	* comparator
    */
   public PQueue()
   {
      //implement this method
      tree = new ArrayList<>();
   }
   
   /**
    * copy constructor
    */
   public PQueue(PQueue pQ)
   {
      //implement this method	   
      this.tree = pQ.tree;
   }
   
   /**
    * A parameterized constructor that uses an externally defined comparator    
    * @param fn - a trichotomous integer value comparator function   
    */
   public PQueue(Comparator<? super E> fn)
   {
      tree = new ArrayList<>();
      cmp = fn;
   }

   public boolean isEmpty()
   {
      //implement this method
      if(tree.isEmpty())
      return true;
      
      return false;
   }

   public void add(E obj)
   {
      //implement this method
      tree.add(obj);
      this.heapifyUp(tree.size()-1);
   }

   public E remove() throws PQueueException
   {
      //Swaps root with last leaf, removes value, and sorts
      //implement this method
      
      if(!tree.isEmpty())
      {
      Collections.swap(tree,0,tree.size()-1);
      tree.remove(tree.size()-1);
      this.heapifyDown(0);
      }
	  return null;
   }
 
   public E peek() throws PQueueException
   {
      //implement this method
      if(!tree.isEmpty())
        return tree.get(0);
	  return null;
   }

   public int size()
   {
      if(!tree.isEmpty())
         return tree.size();
      //implement this method
      return 0;
   }
   
   /**
    * Swaps a parent and child elements of this heap at the specified indices
    * @param place an index of the child element on this heap
    * @param parent an index of the parent element on this heap
    */
   private void swap(int place, int parent)
   {
      //implement this method
      Collections.swap(tree, place, parent);
   }

    /**
     * Rebuild this priority queue from the 
     * specified index using a trickle up procedure
     * @param index the index at which to begin the rebuild
     */
    private void heapifyUp(int index)
    {
        //implement this method
        while (index != 0 && tree.size() > 1)
        {
            int parent = (index-1) / 2;
            /**!SECTION
             * tree.get(index) - PCB object
             * object.getPriority() - get priority of PCB object
             */
            int childPrior = ((PCB)(tree.get(index))).getPriority();
            int parentPrior = ((PCB)(tree.get(parent))).getPriority();

            if(childPrior < parentPrior)
            {
               Collections.swap(tree, index, parent);
            }
            else if (childPrior == parentPrior)
            {
               int childArrival = ((PCB)(tree.get(index))).getArrival();
            int parentArrival = ((PCB)(tree.get(parent))).getArrival();
            if(childArrival < parentArrival)
            {
               Collections.swap(tree, index, parent);
            }
            else{
               return;
            }
            }
            else
               return;
            this.heapifyUp(parent);
        }
    }
    
    /**
     * Rebuild this priority queue from the 
     * specified index using a trickle down procedure
     * @param index the index at which to begin the rebuild
     */   
    public void heapifyDown(int index)
    {

        //implement this method

         
        while((2 * index) + 1 < tree.size() || (2 * index) + 2 < tree.size() && tree.size() > 1){
         int smallest;
        int smallestPriority;
        int smallestArrival;



        //Case1 - Two Children
         if(2 * index + 2 < tree.size())
         {
         
        int leftChild = (2 * index) + 1;
        int rightChild = (2 * index) + 2;
        int leftPriority = ((PCB)tree.get(leftChild)).getPriority();
        int rightPriority = ((PCB)tree.get(rightChild)).getPriority();
        if (leftPriority < rightPriority)
        {
         smallest = leftChild;
         smallestPriority = leftPriority;
         smallestArrival = ((PCB)tree.get(leftChild)).getArrival();
        }
        else if (leftPriority == rightPriority){
         int leftArrival = ((PCB)tree.get(leftChild)).getArrival();
         int rightArrival = ((PCB)tree.get(rightChild)).getArrival();

         if(leftArrival <= rightArrival)
         {
            smallest = leftChild;
         smallestPriority = leftPriority;
         smallestArrival = leftArrival;
         }
         else{
            smallest = rightChild;
         smallestPriority = rightPriority;
         smallestArrival = rightArrival;
         }

        }
        else{
         smallest = rightChild;
         smallestPriority = rightPriority;
         smallestArrival = ((PCB)tree.get(rightChild)).getArrival();
        }
        int parentPriority = ((PCB)tree.get(index)).getPriority();
        int parentArrival = ((PCB)tree.get(index)).getArrival();

        if (smallestPriority < parentPriority)
        {
         Collections.swap(tree, smallest, index);
         this.heapifyDown(smallest);
        }
        else if (smallestPriority == parentPriority)
        {
         if(smallestArrival < parentArrival)
         {
            Collections.swap(tree, smallest, index);
            this.heapifyDown(smallest);
         }
         else{
            return;
         }
        }
        else
         return;
         }

         //Case2 - One Child
         else{
            int leftChild = (2 * index) + 1;
            int leftPriority = ((PCB)tree.get(leftChild)).getPriority();
            int parentPriority = ((PCB)tree.get(index)).getPriority();

        if (leftPriority < parentPriority)
        {
         Collections.swap(tree, leftChild, index);
         this.heapifyDown(leftChild);
        }
        else if (leftPriority == parentPriority)
        {
         int leftArrival = ((PCB)tree.get(leftChild)).getArrival();
         int parentArrival = ((PCB)tree.get(index)).getArrival();

         if(leftArrival < parentArrival)
         {
            Collections.swap(tree, leftChild,index);
            this.heapifyDown(leftChild);
         }
         else{
            return;
         }
        }
        else{
         return;
        }
         }
        }
    }
}