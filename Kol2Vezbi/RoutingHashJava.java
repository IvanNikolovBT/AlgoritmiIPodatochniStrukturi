package Kol2Vezbi;



import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RoutingHashJava {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(bufferedReader.readLine());
        CBHT<String,String> tabela=new CBHT<>(n+1);
        for(int i=0;i<n;i++)
        {
            String vnes1=bufferedReader.readLine();
            String vnes2= bufferedReader.readLine();
            String []splitter=vnes2.split(",");
            int b= splitter.length;
            for(int j=0;j<b;j++)
            tabela.insert(splitter[0],splitter[j++]);
        }
        n=Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<n;i++)
        {
            String vnes1=bufferedReader.readLine();
            String vnes2= bufferedReader.readLine();
            System.out.println("vnes 1 "+vnes1);
            System.out.println("vnes 2 "+vnes2);
            String []splitter=vnes2.split("\\.");
            stringBuilder.setLength(0);
            stringBuilder.append(splitter[0]).append(".").append(splitter[1]).append(".").append(splitter[2]);
            SLLNode<MapEntry<String,String>> curr=tabela.search(vnes1);
            System.out.println("curr e "+curr);
           // System.out.println("String builder"+stringBuilder.toString());
            while(curr!=null && !curr.element.value.contains(stringBuilder.toString()))
            {
                System.out.println("Segashen element"+curr.element.value);
                curr=curr.succ;
            }
            System.out.println("curr"+curr);
            if(curr==null)
                System.out.println("ne postoi");
            else System.out.println("postoi ");

        }
    }
}
