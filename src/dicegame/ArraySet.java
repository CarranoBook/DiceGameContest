package dicegame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created on Mar 4, 2016.
 *
 * @author Wes Hampson
 */
public class ArraySet<E> implements Set<E>
{
    private final List<E> set;
    
    public ArraySet()
    {
        set = new ArrayList<>();
    }
    
    @Override
    public int size()
    {
        return set.size();
    }
    
    @Override
    public boolean isEmpty()
    {
        return set.isEmpty();
    }
    
    @Override
    public boolean contains(Object o)
    {
        return set.contains(o);
    }
    
    @Override
    public Iterator<E> iterator()
    {
        return set.iterator();
    }
    
    @Override
    public Object[] toArray()
    {
        return set.toArray();
    }
    
    @Override
    public <T> T[] toArray(T[] a)
    {
        return set.toArray(a);
    }
    
    @Override
    public boolean add(E e)
    {
        if (set.contains(e)) {
            return false;
        }
        
        set.add(e);
        return true;
    }
    
    @Override
    public boolean remove(Object o)
    {
        return set.remove(o);
    }
    
    @Override
    public boolean containsAll(Collection<?> c)
    {
        return set.containsAll(c);
    }
    
    @Override
    public boolean addAll(Collection<? extends E> c)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean retainAll(Collection<?> c)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean removeAll(Collection<?> c)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void clear()
    {
        set.clear();
    }
}
