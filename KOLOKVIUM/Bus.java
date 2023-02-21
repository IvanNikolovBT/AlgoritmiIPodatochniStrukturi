package KOLOKVIUM;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bus {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();
       int min,max=(M+N-1)*100;
       if(M==0||N==0)
       {
           if(M==0)
               System.out.println(N*100+"\n"+N*100);
           else
               System.out.println(M*100+"\n"+M*100);
           return;
       }
       if(M>N)
       {
           min=100*M;
       }
       else min=100*N;
        System.out.println(min+"\n"+max);

    }

}
