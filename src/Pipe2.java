import java.util.*;
import java.io.*;
//import javagently.*;
import static java.lang.Double.parseDouble;


class Pipe2 extends Thread {
    double[] array2 = new double[3];
    double[] rootarray = new double[2];
   // Graph g = new Graph("Quadratic (a * x^2 + b * x + c = 0)", "x-axis", "y-axis");
    private DataInputStream in;
    private DataOutputStream out;
    int num = 0;
    double d;              //discriminant
    double root1;
    double root2;

    public Pipe2(InputStream is, OutputStream os) {          //input and output steam constructor to act as filter
        in = new DataInputStream(is);
        out = new DataOutputStream(os);
    }

    public synchronized void calcRoots() throws IOException {           //method calculates the roots
        try {
            for (int i = 0; i <= 2; i++) {
                array2[i] = in.readDouble();
                double a = array2[0];
                double b = array2[1];
                double c = array2[2];


                d = Math.sqrt(b * b - 4 * a * c);    //discriminant
                root1 = (-b+d)/(2*a);
                root2 = (-b-d)/(2*a);             //root calculations
/*
                System.out.println("Root 1: "+root1);
                System.out.println("Root 2: "+root2);
                */
                rootarray[0] = root1;
                rootarray[1] = root2;

            }

        } catch (Exception e) {
        }

        display_roots(root1,root2);

    }

    public void display_roots(double root1, double root2){

        System.out.println("Root 1: "+root1);
        System.out.println("Root 2: "+root2);

    }

    public synchronized void drawGraph(int arg) {
       /*
        g.setSymbol(true);
        g.setSymbol(3);
        g.setColor(2);
        */
        for (int z = 0; z < arg; z++) {
            for (int y = 0; y < rootarray.length; y++) {        //assign values for graph
               //g.add(z, rootarray[z]);
                System.out.println("Z value: "+z);
            }
        }
        //g.showGraph();  //display graph
    }


    public void run() {      //running state
        for (;;) {
            try {
                for (int i = 0; i <= 2; i++) {                //puts out the triple array of coefficients to the next class
                    out.writeDouble(array2[i]);
                    out.flush();

                }
                for (int i = 0; i <= 1; i++) {              //this puts outs the double array of roots
                    out.writeDouble(rootarray[i]);
                    out.flush();
                }
            } catch (IOException e) {
            }
        }

    }
}