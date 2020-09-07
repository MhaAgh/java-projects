// Maha, CPCS204,  FCITrecurse, Due:  Thursday, November 13th, 2014 by 11:55PM 
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcitrecurse_2015_1st;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author مهاوي
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        String command; // used to save the command read from input file

        // OPEN FILES
        // Input File:
        File inputFile = new File("FCITrecurse_2015_1st.in.txt");
        if (!inputFile.exists()) {
            System.out.println("Input file, " + inputFile + ", does not exist.");
            System.exit(0);
        }
        // Output File:
        File outputFile = new File("FCITrecurse_2015_1st.out.txt");

        // Make Scanner variable to read from input file, and
        // make Printwriter variable to print to output
        Scanner input = new Scanner(inputFile);
        PrintWriter output = new PrintWriter(outputFile);
        int lines = input.nextInt();
        int c = 0; // make a counter to determine the number of line i reached from the file
        do {
            command = input.next();

            // COMMAND1

            if (command.equals("FCITmath") == true) {
                FCITmath(input, output);
                output.println();
                c++; // Increase the counter
            } // COMMAND2
            else if (command.equals("FCITshape") == true) {
                FCITshape(input, output);
                c++;
            } // COMMAND3
            else if (command.equals("FCIThop") == true) {
                FCITjump(input, output);
                c++;
            } // COMMAND3
            else if (command.equals("FCITgot42") == true) {
                FCITgot42(input, output);
                c++;
            } // print ERRORE
            else {
                System.out.println("Invalid Command: input invalid.");
            }

        } while (lines != c);

        // Close input and output
        input.close();
        output.close();


    }
// the wrapper method for fcitmath

    public static void FCITmath(Scanner input, PrintWriter output) {
        int num1 = input.nextInt();
        int num = input.nextInt();
        System.out.print("FCITmath: ");
        FCITmath1(num1, num);
    }

    // the wrapper method for fcitshape
    public static void FCITshape(Scanner input, PrintWriter output) {
        int n = input.nextInt();
        System.out.println("FCITshape: ");
        FCITshape1(n);
        System.out.println();
    }

    // the wrapper method for fcitjump
    public static void FCITjump(Scanner input, PrintWriter output) {
        int index = input.nextInt();
        int size = input.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = input.nextInt();
        }
        if (FCIThop(index, size, array) == true) {
            System.out.println("FCIThop:  Solvable");
        }
        if (FCIThop(index, size, array) == false) {
            System.out.println("FCIThop:  Not Solvable");
        }
    }

    // the wrapper method for fcitgot24
    public static void FCITgot42(Scanner input, PrintWriter output) {
        int i = input.nextInt();
        if (FCITgot42_(i) == true) {
            System.out.println("FCITgot42:  Solvable");
        }
        if (FCITgot42_(i) == false) {
            System.out.println("FCITgot42:  Not Solvable");
        }

    }
// math

    public static void FCITmath1(int num1, int num) {
        int i = num1;
        int array[] = new int[num - num1 + 1];
        for (int j = 0; j < array.length; j++) {
            if (i >= num1 && i <= num) {
                array[j] = fib(i);
                i++;
            }
        }
        int[] sum = new int[array.length];
        int j = 0;
        for (int m = 0; m < sum.length; m++) {
            if (j < array.length) {
                sum[m] = digitSum(array[j]);
                j++;
            }
        }
        System.out.println(sumArray(sum, sum.length - 1));
    }
// method to calculat the fib

    public static int fib(int num) {
        if (num < 2) {
            return num;

        }

        return fib(num - 1) + fib(num - 2);
    }

    // method to calculat the sum of digits
    public static int digitSum(int n) {
        if (n == 0) {
            return 0;
        }
        return n % 10 + digitSum(n / 10);
    }
// method to calculat the sum of the sum of all digits

    public static int sumArray(int[] array, int index) {
        int sum = 0;
        int k;
        if (index >= 0) {
            k = array[index];
            sum += k;
            return sum + sumArray(array, index - 1);
        }
        return 0;
    }

    // shape
    // method to print triangle of stars up
    public static String PrintStarsUp(int n) // 1
    {
        if (n > 0) {
            return "* " + PrintStarsUp(n - 1);
        } else {
            return " ";
        }
    }
// method to print triangle of stars down

    public static String PrintStarsDown(int n)// 2
    {
        if (n > 0) {
            return " * " + PrintStarsUp(n - 1);
        } else {
            return " ";
        }
    }
// method to print spaces

    public static String PrintSpace(int n) // 3
    {
        if (n > 0) {
            return " " + PrintSpace(n - 1);
        } else {
            return " ";
        }
    }
// // method to call the pr ones

    public static void FCITshape1(int n)// 4
    {
        for (int i = 1; i < n; i += 2) {
            System.out.print(PrintSpace(n - i));// 3
            System.out.println(PrintStarsUp(i));// 1

        }
        for (int i = 0; i < n; i += 2) {
            System.out.println(PrintStarsDown(n - i));// 2
            System.out.print(" " + PrintSpace(i));// 3

        }

    }

    // hop
    public static boolean FCIThop(int index, int j, int[] A) {
        int value = A[index];
        if (index == j - 1) { // we reached the end
            return true;
        }
        if (index < j - 1) {
            if (value + index < j) { //jump right
                return FCIThop(index + value, j, A);
            }
            if (value + index > j - 1 && index - value > 0) {
                return FCIThop(index - value, j, A); //jump left
            }
            if (value + index > j - 1 && index - value <= 0) { // we can't neither jump right or jump left
                return false; // so it's not solvable
            }
            return true;
        }
        return false;
    }

    public static boolean FCITgot42_(int n) {
        if (n == 42) { // we have exactly the number of riyals thet we want
            return true;
        }

        else if(n > 42) {

            if ((n % 2 == 0)
                    && ((n % 3 == 0)
                    && ((n % 10) * (n % 100) / 10) != 0
                    || (n % 4 == 0)
                    && ((n % 10) * (n % 100) / 10) != 0)
                    && (n % 5 == 0)) {// n accept all the conditions
                return FCITgot42_(n / 2)
                        || FCITgot42_(n - (n % 10 * n % 100))
                        || FCITgot42_(n - 42);// do whatever no matter

            } else if ((n % 2 == 0)
                    && ((n % 3 == 0)
                    && ((n % 10) * (n % 100) / 10) != 0
                    || ((n % 4 == 0)
                    && ((n % 10) * (n % 100) / 10) != 0))) { // n accept the first and seconds conditions
                return FCITgot42_(n / 2)
                        || FCITgot42_(n - (n % 10 * n % 100));

            } else if ((n % 2 == 0)
                    && (n % 5 == 0)) {//n accept the first and last conditions
                return FCITgot42_(n / 2)
                        || FCITgot42_(n - 42);

            } else if (((n % 3 == 0) //n accept the second and last conditions
                    && ((n % 10) * (n % 100) / 10) != 0
                    || ((n % 4 == 0)
                    && ((n % 10) * (n % 100) / 10) != 0))
                    && (n % 5 == 0)) {
                return FCITgot42_(n - (n % 10 * n % 100))
                        || FCITgot42_(n - 42);

            } else if (n % 5 == 0) { //n is only divisible by 5
                return FCITgot42_(n - 42);

            } else if (n % 2 == 0) {// n is only even
                return FCITgot42_(n / 2); //return the half of n

            } else if ((n % 3 == 0
                    && ((n % 10) * (n % 100) / 10) != 0)
                    || (n % 4 == 0
                    && ((n % 10) * (n % 100) / 10) != 0)) { //n is only divisible by 3 or 4
                return FCITgot42_(n - (n % 10 * n % 100)); // return multiply the last two digits of n
            }
        }
    return false;
    }
}
