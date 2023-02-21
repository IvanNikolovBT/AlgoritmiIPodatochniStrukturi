package LAB5;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.lang.Object;
interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek ();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear ();
    // Go prazni stekot.

    public void push (E x);
    // Go dodava x na vrvot na stekot.

    public E pop ();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }


    public E peek () {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }


    public void clear () {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }


    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop () {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

public class PostFixEvaluation {

    static int evaluatePostfix(char [] izraz, int n)
    {
        int broj=0;
        ArrayStack<Integer> stek=new ArrayStack<Integer>(izraz.length);
        ArrayStack<Integer> mat=new ArrayStack<Integer>(100);
        for(int i=0;i< izraz.length;i++)
        {
            if(Character.isDigit(izraz[i]))
            {
                //System.out.println("is digit");

                mat.push(0);
                while(izraz[i]!=' ')
                {
                    mat.push(mat.pop()*10+Character.getNumericValue(izraz[i]));
                    i++;
                    // System.out.println("dolzina");
                }
                stek.push(mat.pop());
                System.out.println("cifra "+stek.peek());
                broj++;
            }


                //System.out.println("ima dva");

                    if(izraz[i]=='+')
                    {

                        stek.push(stek.pop()+stek.pop());

                        break;
                    }
                    if(izraz[i]=='*')
                    {

                        //System.out.println(i);
                        stek.push(stek.pop()*stek.pop());
                        break;
                    }
                    if(izraz[i]=='-')
                    {
                        //System.out.println(i);
                        stek.push(-stek.pop()+stek.pop());
                        break;
                    }
                    if(izraz[i]=='/')
                    {
                        //System.out.println(i);
                        int x=stek.pop();
                        int y=stek.pop();
                        stek.push(y/x);
                        break;
                    }



        }
        return stek.pop();
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = evaluatePostfix(exp, exp.length);
        System.out.println(rez);

        br.close();

    }

}