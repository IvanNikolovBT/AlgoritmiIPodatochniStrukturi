package LAB1;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class PushZero
{
    static void pushZerosToEnd(int arr[], int n) {

        int niza[]=new int[100];
        int j=0;
        System.out.println("Transformiranata niza e:");
        for (int i = 0; i < n; i++)
        {
            if(arr[i]!=0)
            {
                System.out.print(arr[i]+" ");
                j++;
            }

        }
        for (int i=j;i<n;i++)
        {
            System.out.print(0+" ");
        }
    }

    public static void main (String[] args)
    {
        int []arr = new int[100];
        Scanner input =new Scanner(System.in);
        int n=input.nextInt();
        for (int i=0;i<n;i++)
        {
            arr[i]=input.nextInt();
        }
        pushZerosToEnd(arr, n);

    }
}
