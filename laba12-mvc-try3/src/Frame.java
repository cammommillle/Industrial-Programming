import javax.swing.*;
import java.awt.*;
import java.util.StringTokenizer;

public class Frame extends JFrame {
    private View<Integer> leftView;
    private View<Integer> rightView;
    private View<Integer> resultView;
    private Controller controller;
    private SetModelSet<Integer> leftSet;
    private SetModelSet<Integer> rightSet;
    private SetModelSet<Integer> resultSet;

    Frame() {

        super("Lab 11");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        initMVC();
        initListsPanel();
        initGeneralButtons();
        setVisible(true);
    }

    private void initMVC() {
        leftSet = SetModelSet.create();
        rightSet = SetModelSet.create();
        resultSet = SetModelSet.create();
        leftView = new View<>(leftSet);
        rightView = new View<>(rightSet);
        resultView = new View<>(resultSet);
        controller = new Controller(leftSet, rightSet, resultSet, leftView, rightView, resultView);

    }

    private void initGeneralButtons() {
        JPanel panel = new JPanel();
        initUnionButton(panel);
        initIntersectionButton(panel);
        initDifferenceButton(panel);
        initEqualButton(panel);
        add(panel, BorderLayout.SOUTH);
    }

    private void initEqualButton(JPanel panel) {
        JButton button = new JButton("equals");
        button.addActionListener(actionEvent -> controller.equals(this));
        panel.add(button);
    }

    private void initDifferenceButton(JPanel panel) {
        JButton button = new JButton("difference");
        button.addActionListener(actionEvent -> controller.difference());
        panel.add(button);
    }

    private void initIntersectionButton(JPanel panel) {
        JButton button = new JButton("intersection");
        button.addActionListener(actionEvent -> controller.intersection());
        panel.add(button);
    }

    private void initUnionButton(JPanel panel) {
        JButton button = new JButton("union");
        button.addActionListener(actionEvent -> controller.union());
        panel.add(button);
    }

    private void initListsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 0));
        initPanel(leftSet, leftView, panel);
        initPanel(rightSet, rightView, panel);
        initPanel(resultSet, resultView, panel);
        add(panel, BorderLayout.CENTER);
    }

    private void initPanel(SetModelSet<Integer> set, View<Integer> view, JPanel panel) {
        JPanel listPanel = new JPanel(new BorderLayout());
        JList<Integer> list = view.getList();

        JPanel listPanelButtons = new JPanel(new FlowLayout());
        initAddButton(set, view, listPanelButtons);
        initRemoveButton(set, view, listPanelButtons);
        initClearButton(set, view, listPanelButtons);
        initSizeButton(set, listPanelButtons);
        listPanel.add(listPanelButtons, BorderLayout.NORTH);
        listPanel.add(list, BorderLayout.CENTER);
        listPanel.add(new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        panel.add(listPanel);
    }

    private void initAddButton(SetModelSet<Integer> set, View<Integer> view, JPanel panel) {
        JButton addButton = new JButton("add");
        addButton.addActionListener(actionEvent -> {
            Integer value = 0;
            String inputValue = JOptionPane.showInputDialog("Введите числа:");

            if (inputValue != null && !inputValue.isEmpty()) {
                try {
                    StringTokenizer str = new StringTokenizer(inputValue, " \t\n\r,.");
                    while (str.hasMoreTokens()) {
                        value = Integer.parseInt(str.nextToken());
                        controller.add(view, set, value);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Некорректный ввод. Введите целое число.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(addButton);
    }



    private void initRemoveButton(SetModelSet<Integer> set, View<Integer> view, JPanel panel) {
        JButton removeButton = new JButton("remove");
        removeButton.addActionListener(actionEvent ->
        {
            Integer value = 0;
            String inputValue = JOptionPane.showInputDialog("Введите числo:");

            if (inputValue != null && !inputValue.isEmpty()) {
                try {
                     value = Integer.parseInt(inputValue);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Некорректный ввод. Введите целое число.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }

            controller.remove(view, set, value);
        });
        panel.add(removeButton);
    }

    private void initClearButton(SetModelSet<Integer> set, View<Integer> view, JPanel panel) {
        JButton clearButton = new JButton("clear");
        clearButton.addActionListener(actionEvent -> controller.clear(view, set));
        panel.add(clearButton);
    }

    private void initSizeButton(SetModelSet<Integer> set, JPanel panel) {
        JButton sizeButton = new JButton("size");
        sizeButton.addActionListener(actionEvent -> controller.size(this, set));
        panel.add(sizeButton);
    }
}