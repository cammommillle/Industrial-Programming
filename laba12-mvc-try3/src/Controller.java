

import javax.swing.*;

public class Controller {
    private final SetModelSet<Integer> leftSet;
    private final SetModelSet<Integer> rightSet;
    private final SetModelSet<Integer> resultSet;
    private final View<Integer> leftView;
    private final View<Integer> rightView;
    private final View<Integer> resultView;
    public Controller(SetModelSet<Integer> leftSet, SetModelSet<Integer> rightSet, SetModelSet<Integer> resultSet, View<Integer> leftView, View<Integer> rightView, View<Integer> resultView) {
        this.leftSet = leftSet;
        this.rightSet = rightSet;
        this.resultSet = resultSet;
        this.leftView = leftView;
        this.rightView = rightView;
        this.resultView = resultView;
    }

    public void union() {
        resultSet.clear();
        resultSet.addAll(leftSet.union(rightSet));
        resultView.repaintList();
    }
    public void intersection() {
        resultSet.clear();
        resultSet.addAll(leftSet.intersection(rightSet));
        resultView.repaintList();
    }

    public void difference() {
        resultSet.clear();
        resultSet.addAll(leftSet.difference(rightSet));
        resultView.repaintList();
    }
    public void add(View<Integer> view, SetModelSet<Integer> set, Integer value) {
        set.add(value);
        view.repaintList();
    }
    public void remove(View<Integer> view, SetModelSet<Integer> set, Integer value) {
        set.remove(value);
        view.repaintList();
    }
    public void clear(View<Integer> view, SetModelSet<Integer> set) {
        set.clear();
        view.repaintList();
    }

    public void equals(JFrame frame) {
        String s = leftSet.equals(rightSet) ? "The sets are equals" : "The sets are different";
        JOptionPane.showMessageDialog(frame, s, "EQUALS", JOptionPane.INFORMATION_MESSAGE);
    }

    public void size(JFrame frame, SetModelSet<Integer> set) {
        String s = "Set contains " + set.size() + " element";
        JOptionPane.showMessageDialog(frame, s, "SIZE", JOptionPane.INFORMATION_MESSAGE);
    }
}