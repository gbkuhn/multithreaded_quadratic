
import java.util.*;
import java.io.*;
//import javagently.*;
import static java.lang.Double.parseDouble;

class Pipe3 extends Thread {
    double[] array3 = new double[3];                             //arrays to store incoming information from pipe
    double[] rootarray = new double[2];
    boolean check = true;
    boolean check1 = Double.isNaN(rootarray[0]);                   //checks for imaginary number
    boolean check2 = Double.isNaN(rootarray[1]);
    private DataInputStream in;

    public Pipe3(InputStream is) {
        in = new DataInputStream(is);
    }

    public synchronized void output() throws IOException {
        try {

            for (int i = 0; i <= 2; i++) {                              //takes in the coefficients
                array3[i] = in.readDouble();
                System.out.println("coeff_"+i+": "+array3[i]);
            }
            for (int i = 0; i <= 1; i++) {
                rootarray[i] = in.readDouble();
                System.out.println("root_"+i+": "+rootarray[i]); //takes in the roots
            }

            check1(rootarray[0]);
            check1(rootarray[1]);

            if (check1 || check2) {
                System.out.println("For coefficients:");                                       //prints to results.dat in same directory
                System.out.println("a: " + array3[0] + ";" + " b: " + array3[1] + ";" + " c: " + array3[2] + ";");
                System.out.println("real roots do not exist");

            }

        } catch (IOException e) {
            System.out.println("PIPE3: " + e);
        }
    }

    public boolean check1(double root_param){
        if(Double.isNaN(rootarray[0])){
            check1 = true;
        }else{
            check1=false;
        }
        return check1;
    }

    public boolean check2(double root_param2){
        if(Double.isNaN(rootarray[1])){
            check2 = true;
        }else{
            check2=false;
        }
        return check2;
    }

    public void run() {
        for (; ; ) {
            try {
                while (check == true) {
                    for (int i = 0; i <= 2; i++) {
                        array3[i] = in.readDouble();    //takes in coefficient array in order to print to file
                    }
                    PrintWriter out = new PrintWriter(new FileWriter("results.dat", true));    //create file.dat
                    out.println("For coefficients:");
                    out.println("a = " + array3[0] + "; b = " + array3[1] + "; c = " + array3[2] + ";"); //proper format
                    out.println("root1 = " + rootarray[0] + "; root2 = " + rootarray[1] + ";");
                    out.println();
                    out.close();      //close file
                    check = false;      //prevents printing multiple times
                }
            } catch (IOException e) {
            }
        }
    }
}