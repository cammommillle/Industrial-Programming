import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        try {

                BinaryTree<Student> tSt = new BinaryTree();
                Student s1 = new Student(2, "Goncharenko");
                Student s2 = new Student(1, "Abramchuk");
                Student s3 = new Student(2, "Alexeichik");
                Student s4 = new Student(1, "Goncharenko");
                Student s5 = new Student(2, "Abramchuk");
                tSt.add(s1);
                tSt.add(s2);
                tSt.add(s3);
                tSt.add(s4);
                tSt.add(s5);

                System.out.println(tSt.containsNode(s1));
                tSt.delete(s1);
                System.out.println(tSt.containsNode(s1));
                tSt.traverseLRootR();
                System.out.println();
                tSt.traverseRootLR();
                System.out.println();
                tSt.traverseLRRoot();


/*
            BinaryTree<Double> t = new BinaryTree();
            t.add(60.);
            t.add(40.);
            t.add(80.);
            t.add(30.);
            t.add(50.);
            t.add(70.);
            t.add(90.);
            t.add(30.);
            t.add(75.);
            t.add(65.);
            t.add(66.);
            t.add(63.);

            System.out.println(t.containsNode(60.));
            t.delete(60.);
            System.out.println(t.containsNode(60.));
            t.traverseLRootR();
            System.out.println();
            t.traverseRootLR();
            System.out.println();
            t.traverseLRRoot();
*/

        }
        catch (NullPointerException e)
        {
            System.out.println(e.getMessage());
        }

        //System.out.println("Hello world!");
    }
}