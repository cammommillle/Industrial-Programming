interface Element {
    void accept (Visitor v);
}

class CustInt implements Element {
    Integer value;
    CustInt(Integer val)
    {
        value = val;
    }

    public String toString()
    {
        return value.toString();
    }

    @Override
    public void accept(Visitor v) {
        v.visitCastInt(this);
    }
}

interface Visitor {
    void visitCastInt(CustInt el);
}

class MyVisitor implements Visitor {
    Double sum = (double) 0;
    short size = 0;
    Double countAverage() {
        return sum / size ;
    }
    @Override
    public void visitCastInt(CustInt el) {
        sum += el.value;
        size++;
    }
    void clean()
    {
        sum=(double)0;
        size = 0;

    }
}