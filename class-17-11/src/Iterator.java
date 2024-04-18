interface IterableSet<T> {
    Iterator<T> createIterator();
}
public interface Iterator<T> {
    void next();
    boolean hasNext();
    T currentValue() throws IndexOutOfBoundsException;
}

class MyIterator<T> implements Iterator<T> {
    private final Set<T> set;
    private int index=0;

    public MyIterator(Set<T> set) {
        this.set = set;
        index = 0;
    }

    @Override
    public void next() throws IndexOutOfBoundsException {
        if (hasNext()) {
            index++;
        } else throw new IndexOutOfBoundsException();
    }

    @Override
    public boolean hasNext() {
        return index <= set.size() - 1;
    }

    @Override
    public T currentValue() throws IndexOutOfBoundsException {
        return (T) set.get(index);
    }
}
