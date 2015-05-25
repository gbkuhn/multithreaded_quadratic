import java.util.*;
import java.io.*;
//import javagently.*;
import static java.lang.Double.parseDouble;


public class Quadratic {

    public static void main(String args[]) throws InterruptedException {
        int arg = Integer.parseInt(args[0]);        //parse in command line argument, amount of times the program will run
        try {
            for (int x = 0; x < arg; x++) {
                PipedOutputStream pipeout1 = new PipedOutputStream();           //lining up the pipes end to end
                PipedInputStream pipein1 = new PipedInputStream(pipeout1);
                PipedOutputStream pipeout2 = new PipedOutputStream();
                PipedInputStream pin2 = new PipedInputStream(pipeout2);

                Pipe1 p1 = new Pipe1(pipeout1);               //first class has out output, middle is filter, last has output
                Pipe2 p2 = new Pipe2(pipein1, pipeout2);
                Pipe3 p3 = new Pipe3(pin2);

                p1.readCoeffs();      //read coefficients
                p1.start();          //start thread

                p2.calcRoots();      //calc roots
                p2.drawGraph(arg);//draw graph with command line argument for iterations
                p2.start();                 //start thread

                p3.output();              //print results
                p3.start();               //start thread
            }
        } catch (IOException e) {
        }
    }

}