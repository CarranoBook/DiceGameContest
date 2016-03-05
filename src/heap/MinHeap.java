/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package heap;

import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author NB <nb@fs.org>
 */
public class MinHeap<T extends Comparable<? super T>>
                        implements MinHeapInterface<T> {
    
    private T[] heap;
    private int lastIndex;
    private static final int DEFAULT_INITIAL_CAPACITY = 25;
    
    public MinHeap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }
    
    public MinHeap(int size) {
        heap = (T[]) new Comparable[size+1];
        lastIndex = 0;
    }
    
    public MinHeap(T[] elements) {
        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[]) new Comparable[elements.length + 1];
        
        heap = tempHeap;
        lastIndex = elements.length;
        
        //construct the heap array
        for (int i  = 0; i < elements.length; i++)
            heap[i+1] = elements[i];
        
        //heapify the heap array
        for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
            reheap(rootIndex);
    }
    
    @Override
    public void add(T newEntry) {
        // identify where the new element will go and upshift lastIndex
        int heapIndex = ++lastIndex;
        //Ensure capacity of heap
        ensureCapacity();
        
        int parentIndex = heapIndex / 2;
        //while the parent is bigger than the newEntry, we move upward
        while (parentIndex > 0 && newEntry.compareTo(heap[parentIndex]) < 0 )
        {
            //move the parent to the lower level
            heap[heapIndex] = heap[parentIndex];
            //reset indices
            heapIndex = parentIndex;
            parentIndex = heapIndex / 2;
        }
        //assign newEntry to correct position
        heap[heapIndex] = newEntry;
        
    }
    
    /**
     * Doubles the size of the heap if required.
     */
    private void ensureCapacity() {
        if (lastIndex >= heap.length - 2)
            heap = Arrays.copyOf(heap, heap.length * 2);
    }

    @Override
    public T removeMin() {
        T result = null;
        
        if (!isEmpty()) {
            result = heap[1];
            //set tail element to head element and decrement lastIndex
            heap[1] = heap[lastIndex--];
            //reheap the semiheap
            reheap(1);
        }
        
        return result;
    }
    
    private void reheap(int rootIndex) {
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = rootIndex * 2;
        
        //identify the smaller child
        while (!done  && (leftChildIndex <= lastIndex)) {
            int smallerChildIndex = leftChildIndex; //asumption
            int rightChildIndex = leftChildIndex + 1;
            //if rightChild smaller than left child, change smallerChildIndex
            if ( rightChildIndex <= lastIndex &&
                    heap[rightChildIndex].compareTo(heap[leftChildIndex]) < 0 )
                smallerChildIndex++;
            //end if
            
            //compare orphan to smallerChild
            if ( orphan.compareTo(heap[smallerChildIndex]) > 0 ) {
                //push up the smaller child
                heap[rootIndex] = heap[smallerChildIndex];
                //reset indices
                rootIndex = smallerChildIndex;
                leftChildIndex = 2 * rootIndex;
            }
            else //rehash is complete
                done = true;
        }
        //place orphan in correct spot
        heap[rootIndex] = orphan;
    }

    @Override
    public T getMin() {
        return heap[1];
    }

    @Override
    public int getSize() {
        return lastIndex;
    }

    @Override
    public boolean isEmpty() {
        return lastIndex < 1;
    }

    @Override
    public void clear() {
        for (; lastIndex > -1; lastIndex--)
            heap[lastIndex] = null;
        
        lastIndex = 0;
    }
    
    /**
     * This method generates an iterator that steps through the heap
     * one element at a time.  It does this in the order the elements are
     * stored in the heap array
     * @return 
     */
    public Iterator<T> getIterator() {
        return new HeapIterator();
    }
    
    private class HeapIterator implements Iterator<T>
    {
        int index;
        
        HeapIterator() {
            index = 1;
        }
        
        @Override
        public boolean hasNext() {
            return index < lastIndex;
        }

        @Override
        public T next() {
            return heap[index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
