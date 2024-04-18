import java.util.ArrayList;
import java.util.List;

public class Set<T> implements IterableSet<T>{
    private List<T> set;
    public Set() {
        set = new ArrayList<T>();
    }
    @Override
    public MyIterator<T> createIterator() {
        return new MyIterator<T>(this);
    }

    int size() { return set.size(); }
    boolean isEmpty() { return set.isEmpty(); }
    void clear() { set.clear(); }
    boolean equals(Set<T> st) { return set.equals(st.set); }

    T get(int i) { return set.get(i);}

    public String toString() {
        MyIterator<T> it = createIterator();
        StringBuilder sb = new StringBuilder("set: ");

        while(it.hasNext()) {
            sb.append(it.currentValue()+" ");
            it.next();
        }

        return sb.toString();
    }

    public Object[] toJListStructure() {
        MyIterator<T> it = createIterator();
        Object[] objects = new Object[size()];
        int i=0;
        while(it.hasNext()) {
            objects[i++]= it.currentValue();
            it.next();
        }
        return objects;
    }

    public void add(T element) {
        if (!set.contains(element)) {
            set.add(element);
        }
    }

    public void remove(T element) {
        set.remove(element);
    }

    public void addAll(Set<T> st) {
        MyIterator<T> it = st.createIterator();
        while(it.hasNext())
        {
           add(it.currentValue());
           it.next();
        }
    }

    public Set<T> union(Set<T> st) {
        Set<T> result = new Set<>();
        result.addAll(this);
        result.addAll(st);
        return result;
    }

    public Set<T> intersection(Set<T> st) {
        Set<T> result = new Set<>();
        for (T element : this.set) {
            if (st.set.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public Set<T> difference(Set<T> st) {
        Set<T> result = union(st);
        Set<T> temp = intersection(st);

        MyIterator<T> it = temp.createIterator();
        while(it.hasNext())
        {
            result.remove(it.currentValue());
            it.next();
        }
        return result;
    }
}
