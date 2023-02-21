package LAB4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int presmetaj(char c[], int l, int r) {
       int n=c.length;
       Stack<Character> zag=new Stack<>();
       Stack<Character> znak=new Stack<>();
       Stack<Integer> broj=new Stack<>();
       for(int i=0;i<n;i++)
       {
           if(c[i]=='(')
               zag.push('(');
           if(c[i]=='+'||c[i]=='-')
               znak.push(c[i]);
           if(Character.isDigit(c[i]))
           {
               broj.push(Character.getNumericValue(c[i]));
           }
           if(c[i]==')')
           {
               int x=broj.pop();
               int y=broj.pop();
               if(znak.peek()=='+')
               {
                   znak.pop();
                   zag.pop();
                   broj.push(x+y);
               }
               else
               {
                   znak.pop();
                   zag.pop();
                   broj.push(x-y);
               }
           }

       }
        return broj.pop();
    }



    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);
        br.close();

    }

}
