package LAB6.ZAD1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class OddEvenSort {

    static void oddEvenSort(int[] a, int n)
    {
        // Vasiot kod tuka


        ArrayList<Integer> parni=new ArrayList<>();
        ArrayList<Integer> neparni=new ArrayList<>();
        for(int i=0;i<a.length;i++)
        {
            if(a[i]%2==0)
                parni.add(a[i]);
            else neparni.add (a[i]);
        }
        int dparni=parni.size();
        int dneparni=neparni.size();
        for(int i=0;i<dparni-1;i++)
        {
            for(int j=0;j<dparni-1;j++)
                if(parni.get(j+1)<parni.get(j))
                {
                    int swap=parni.get(j);
                    parni.set(j,parni.get(j+1));
                    parni.set(j+1,swap);
                }
        }
        Collections.reverse(parni);
        for(int i=0;i<dneparni-1;i++)
        {
            for(int j=0;j<dneparni-1;j++)
                if(neparni.get(j+1)<neparni.get(j))
                {
                    int swap=neparni.get(j);
                    neparni.set(j,neparni.get(j+1));
                    neparni.set(j+1,swap);
                }
        }
        System.out.println(parni);
        System.out.println(neparni);
        for(int i=0;i<dparni;i++)
        {
            neparni.add(parni.get(i));
        }
        System.out.println(neparni);
        for(int i=0;i<neparni.size();i++)
        {
            a[i]=neparni.get(i);
        }

    }

    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        oddEvenSort(a,n);
        for(i=0;i<n-1;i++)
            System.out.print(a[i]+" ");
        System.out.print(a[i]);
    }
}