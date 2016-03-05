/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package heap;

/**
 *
 * @author NB <nb@fs.org>
 */
public interface MinHeapInterface<T extends Comparable<? super T>> {
    public void add(T newEntry);
    public T removeMin();
    public T getMin();
    public int getSize();
    public boolean isEmpty();
    public void clear();
}
