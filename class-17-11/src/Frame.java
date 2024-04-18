import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;


public class Frame extends JFrame implements ActionListener{

    JRadioButton s1 = new JRadioButton("Set 1");
    JRadioButton s2 = new JRadioButton("Set 2");
    JTextArea set1 = new JTextArea("",20,30);
    JTextArea set2 = new JTextArea("",20,30);
    JTextArea answ = new JTextArea("",1,30);

    JButton add = new JButton("add");
    JButton addAll = new JButton("addAll");
    JTextField addValue = new JTextField("1");
    JButton clean = new JButton("clean");
    JButton size = new JButton("size");
    JButton isEmpty = new JButton("isEmpty");
    JButton equals = new JButton("equals");
    JButton remove = new JButton("remove");
    JButton union = new JButton("union");
    JButton intersection = new JButton("intersection");
    JButton difference = new JButton("difference");
    JButton average = new JButton("average");

    DefaultListModel listModel = new DefaultListModel();
    JList list = new JList(listModel);

    Frame(String title){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(900, 700);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        ButtonGroup type = new ButtonGroup();
        type.add(s1);
        type.add(s2);
        s1.setSelected(true);
        JPanel radioPanel = new JPanel();
        radioPanel.add(s1);
        radioPanel.add(s2);
        add(radioPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(answ);
        scrollPane.setBounds(20, 20, 100, 50);



        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.add(scrollPane, BorderLayout.NORTH);
        set1.setLineWrap(true);
        JScrollPane scrollPane1 = new JScrollPane(set1);
        scrollPane1.setBounds(50, 50, 300, 300);
        textPanel.add(scrollPane1, BorderLayout.WEST);
        set2.setLineWrap(true);
        JScrollPane scrollPane2 = new JScrollPane(set2);
        scrollPane2.setBounds(400, 100, 300, 300);
        textPanel.add(scrollPane2, BorderLayout.EAST);
        textPanel.add(list, BorderLayout.CENTER);
        textPanel.add(new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        add(textPanel, BorderLayout.CENTER);

        addValue.setSize(100,100);
        textPanel.add(addValue, BorderLayout.SOUTH);
        JPanel metods = new JPanel();
        metods.add(add);
        metods.add(addAll);
        metods.add(clean);
        metods.add(size);
        metods.add(isEmpty);
        metods.add(equals);
        metods.add(remove);
        metods.add(union);
        metods.add(intersection);
        metods.add(difference);
        metods.add(average);

        add(metods, BorderLayout.SOUTH);
        set1.setEditable(false);
        set2.setEditable(false);


        add.addActionListener(this);
        addAll.addActionListener(this);
        clean.addActionListener(this);
        size.addActionListener(this);
        isEmpty.addActionListener(this);
        equals.addActionListener(this);
        remove.addActionListener(this);
        union.addActionListener(this);
        intersection.addActionListener(this);
        difference.addActionListener(this);
        average.addActionListener(this);

    }
    static Set<CustInt> set0 = new Set<>();
    Set<CustInt> set01= new Set<>();
    Set<CustInt> set02 =new Set<>();
    public void actionPerformed(ActionEvent e)
    {
        JTextArea choose = null;

        if (s1.isSelected()) {
            choose = set1;
            set0 = set01;
        }
        else if(s2.isSelected()) {
            choose = set2;
            set0 = set02;
        }

        try
        {
            if(e.getSource() == add) {
                set0.add(new CustInt(Integer.parseInt(addValue.getText())));

            }
            if(e.getSource() == addAll)
            {
                Set<CustInt> st = new Set<>();
                StringTokenizer str = new StringTokenizer(addValue.getText(), " \t\n\r,.");
                while (str.hasMoreTokens())
                {
                    st.add(new CustInt(Integer.parseInt(str.nextToken())));
                }
                set0.addAll(st);

            }
            if (e.getSource() == clean)
            {
                set0.clear();
            }
            if(e.getSource() == size)
            {
                answ.setText(String.valueOf(set0.size()));
            }
            if(e.getSource() == isEmpty)
            {
                if(set0.isEmpty()) answ.setText("empty");
                else answ.setText("full");
            }
            if(e.getSource() == equals)
            {
                if(set01.equals(set02)) answ.setText("set1 and set2 are equal");
                else answ.setText("not equal");
            }
            if(e.getSource() == remove)
            {
                set0.remove(new CustInt(Integer.parseInt(addValue.getText())));
            }
            if(e.getSource()==union)
            {
                listModel.clear();
                Object[] arr = set01.union(set02).toJListStructure();
                for(Object value: arr)
                {
                    listModel.addElement(value);
                }
            }
            if(e.getSource()==intersection)
            {
                listModel.clear();
                Object[] arr = set01.intersection(set02).toJListStructure();
                for(Object value: arr)
                {
                    listModel.addElement(value);
                }
            }
            if(e.getSource()==difference)
            {
                listModel.clear();
                Object[] arr = set01.difference(set02).toJListStructure();
                for(Object value: arr)
                {
                    listModel.addElement(value);
                }
            }
            if(e.getSource()==average)
            {
                MyIterator<CustInt> it = set0.createIterator();
                MyVisitor vis = new MyVisitor();
                //CustInt temp;
                while (it.hasNext())
                {
                    //createCustInt(it.currentValue());
                    it.currentValue().accept(vis);
                    it.next();
                }
                answ.setText(String.valueOf(vis.countAverage()));
                vis.clean();
            }
            if (s1.isSelected()) {
                set01 = set0;
            }
            else if(s2.isSelected()){
                set02 = set0;
            }
            choose.setText(set0.toString());
           // set0.clear();
        }
        catch ( IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
