package LAB1;



import java.sql.SQLOutput;
import java.util.Scanner;

public class Initials {

    static void printInitials(String name)
    {

        int n=name.length();
        name=name.toUpperCase();
        for(int i=0;i<n;i++)
        {

            if(i==0 || name.charAt(i)==' ')
            {
                if(i==0)
                {
                    System.out.print(name.charAt(0));

                }
                else
                    System.out.print(name.charAt(i+1));
            }
        }
    }

    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        // System.out.println("vnesi broj na n");
        int n=input.nextInt();
        String name;
        input.nextLine();
        for(int i=0; i<n; i++){
            name = input.nextLine();
            printInitials(name);
            System.out.println();
        }
    }
}



