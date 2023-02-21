package KOLOKVIUM;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class ArrayQueue<E>{
    E[] elems;
    int length, front, rear;
    @SuppressWarnings("unchecked")
    public ArrayQueue (int maxlength) {
        elems = (E[]) new Object[maxlength];
        clear();
    }
    public boolean isEmpty () {
        return (length == 0);
    }
    public int size () {
        return length;
    }
    public E peek () {
        if (length > 0)
            return elems[front];
        else{
            System.out.println("Redicata e prazna");
            return null;
        }
    }
    public void clear () {
        length = 0;
        front = rear = 0;
    }
    public void enqueue (E x) {
        elems[rear++] = x;
        if (rear == elems.length)  rear = 0;
        length++;
    }
    public E dequeue () {
        if (length > 0) {
            E frontmost = elems[front];
            elems[front++] = null;
            if (front == elems.length)  front = 0;
            length--;
            return frontmost;
        } else{
            System.out.println("Redicata e prazna");
            return null;
        }
    }
}



public class card_trick {
    public static int count(int N){

        Stack<Integer>prevrti=new Stack<>();
        Stack<Integer> vrv=new Stack<>();
       Stack<Integer> prevrtivrv=new Stack<>();
       ArrayQueue<Integer> ajde=new ArrayQueue<>(7);

        int[] niza =new int[51];
        for(int i=0;i<51;i++)
            niza[i]=i+1;
        int broj=0;
        while(niza[0]!=N)
        {
           // for(int i=0;i< niza.length;i++)
            //    System.out.println(niza[i]);
           // System.out.println("BROJ IZNESSUVA "+broj);
            for(int i=0;i<7;i++)
                prevrti.push(niza[i]);  //zemi gi prvite 7  za prvertvnaje
            for(int i=0;i<7;i++)
                ajde.enqueue(niza[i+7]);
                //vrv.push(niza[i+7]);  //zemi gi vtorite 7 za dodavanje izmegju
            for(int i=0;i<37;i++) //ovde mozi da padni
                niza[i]=niza[i+14];   //pomesti se za 14 mesta
            for(int i=37;i<51;i++)
            {
                niza[i]=prevrti.pop();
                i++;
                niza[i]=ajde.dequeue();
                //niza[i]=vrv.pop();
            }
            for(int i=0;i< niza.length;i++)
                System.out.print(niza[i]+" INDEX "+i+"    ");
            broj++;
        }



        return broj;

    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
        System.out.println(count(Integer.parseInt(br.readLine())));
    }

}
