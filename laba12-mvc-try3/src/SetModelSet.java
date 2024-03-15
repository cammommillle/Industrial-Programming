import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SetModelSet<T> {
    private List<T> set;
    public static <T> SetModelSet<T> create() {
        return new SetModelSet<>();
    }
    public SetModelSet() {
        set = new ArrayList<>();
    }
    public MyIterator createIterator() {
        return new MyIterator();
    }
    public class MyIterator {
        private int index = 0;
        MyIterator()
        {
            index=0;
            // this.set=st;
        }
        boolean hasNext() {
            return index <= size() - 1;
        }

        void next() throws IndexOutOfBoundsException {
            if (hasNext()) {
                index++;
            } else throw new IndexOutOfBoundsException();
        }
        T currentValue()
        {
            return (T) get(index);
        }

    }

    int size() { return set.size(); }
    boolean isEmpty() { return set.isEmpty(); }
    void clear() { set.clear(); }
    boolean equals(SetModelSet<T> st) { return set.equals(st.set); }

    T get(int i) { return set.get(i);}

    public String toString() {
        MyIterator it = createIterator();
        StringBuilder sb = new StringBuilder("set: ");

        while(it.hasNext()) {
            sb.append(it.currentValue()+" ");
            it.next();
        }

        return sb.toString();
    }

    public Object[] toJListStructure() {
        MyIterator it = createIterator();
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

    public void addAll(SetModelSet<T> st) {
        MyIterator it = st.createIterator();
        while(it.hasNext())
        {
            add(it.currentValue());
            it.next();
        }
    }

    public SetModelSet<T> union(SetModelSet<T> st) {
        SetModelSet<T> result = new SetModelSet<>();
        result.addAll(this);
        result.addAll(st);
        return result;
    }

    public SetModelSet<T> intersection(SetModelSet<T> st) {
        SetModelSet<T> result = new SetModelSet<>();
        for (T element : this.set) {
            if (st.set.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public SetModelSet<T> difference(SetModelSet<T> st) {
        SetModelSet<T> result = union(st);
        SetModelSet<T> temp = intersection(st);

        MyIterator it = temp.createIterator();
        while(it.hasNext())
        {
            result.remove(it.currentValue());
            it.next();
        }
        return result;
    }
}
