package LAB5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Stack;

public class CheckXML {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String [] redovi = new String[n];

        for(int i=0;i<n;i++)
            redovi[i] = br.readLine();
        Stack<String> stek=new java.util.Stack<String>();
        int valid=1;

        for(int i=0;i< redovi.length;i++) {
            if (Character.compare(redovi[i].charAt(0),'[')>=0&& Character.compare(redovi[i].charAt(1),'/')!=0)
            {
                System.out.println("vlegva vo [");
                stek.push(redovi[i]);
                System.out.println(redovi[i]);
            }
            if(Character.compare(redovi[i].charAt(0),'[')>0&& Character.compare(redovi[i].charAt(1),'/')==0)
            {
                System.out.println("vlegva vo /");
                StringBuilder stringBuilder=new StringBuilder();
                String []niza=redovi[i].split("/");
                stringBuilder.append(niza[0]).append(niza[1]);
                System.out.println(stringBuilder+ "          "+stek.peek());
                String sa=stringBuilder.toString();
                if(stringBuilder.equals(stek.peek()))
                {
                    System.out.println("tochno");
                    stek.pop();
                }
                else
                {
                    System.out.println("greshno");
                    valid=0;
                    break;
                }

            }
        }
        // Vasiot kod tuka
        // Moze da koristite dopolnitelni funkcii ako vi se potrebni

        System.out.println(valid);

        br.close();
    }
}