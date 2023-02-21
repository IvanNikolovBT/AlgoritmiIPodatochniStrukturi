package LAB4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class ZigZagSequence {

    static int najdiNajdolgaCikCak(int a[]) {
        // Vasiot kod tuka
        int n=a.length;
        int brojac=1;
        int max=1;
        for(int i=0;i<n-1;i++)
        {

            System.out.println(brojac);
            if(a[i]<0&&a[i+1]<0||a[i]>0&&a[i+1]>0||a[i]==0||a[i+1]==0)
        {
            System.out.println("vlegva");
            if(max<brojac)
                brojac=max;
            brojac=1;
            continue;
        }
            brojac++;
            System.out.println(brojac+"posle if ");
            if(Integer.compare(brojac,max)>0)
            {
                max=brojac;
            }
        }

        return max;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i=0;i<N;i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = najdiNajdolgaCikCak(a);
        System.out.println("\n");
        System.out.println(rez);

        br.close();

    }

}
