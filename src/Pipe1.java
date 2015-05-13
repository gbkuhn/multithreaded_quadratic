import java.util.*;
import java.io.*;
import javagently.*;
import static java.lang.Double.parseDouble;

class Pipe1 extends Thread {
    double[] array1 = new double[3];           //array for inputs
    private DataOutputStream out;

    public Pipe1(OutputStream os) {
        out = new DataOutputStream(os);        //output stream to put inputs into the second class
    }

    public synchronized void readCoeffs() {            //read the coefficients
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter value for a: ");
        double a = parseDouble(scanner.nextLine());
        System.out.println("Enter value for b: ");
        double b = parseDouble(scanner.nextLine());
        System.out.println("Enter value for c: ");
        double c = parseDouble(scanner.nextLine());
        array1[0] = a;                                     //assign values to array
        array1[1] = b;
        array1[2] = c;

    }

    public void run() {
        while (true) {
            try {
                for (int i = 0; i <= 2; i++) {          //put our each element of coefficient array then flush
                    out.writeDouble(array1[i]);
                    out.flush();
                }

            } catch (IOException e) {
            }
        }

    }
}