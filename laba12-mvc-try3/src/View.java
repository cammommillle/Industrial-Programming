

import javax.swing.*;

public class View<T> {
    private final DefaultListModel<Object> listModel;
    private final SetModelSet<T> model;
    private final JList<T> list;

    public JList<T> getList() {
        return list;
    }

    public View(SetModelSet<T> model) {
        listModel = new DefaultListModel<Object>();
        list = (JList<T>) new JList<>(listModel);
        this.model = model;
        repaintList();
    }

    public void repaintList() {
        listModel.clear();
        SetModelSet.MyIterator it = model.createIterator();
        while (it.hasNext()) {
            listModel.addElement(it.currentValue());
            it.next();
        }
    }
}