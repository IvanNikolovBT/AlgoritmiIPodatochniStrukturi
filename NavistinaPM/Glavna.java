package NavistinaPM;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Glavna
{
    public static void main(String[] args) throws IOException {
//        Scanner input = new Scanner(System.in);
//        int n = input.nextInt();
//        CBHT<String,Merenje> tabela = new CBHT<>(2*n);
//        for(int i=0;i<n;i++)
//        {
//            String opstina = input.next();
//            double merenje = input.nextDouble();
//            SLLNode<MapEntry<String,Merenje>> curr = tabela.search(opstina);
//            if(curr!=null)
//            {
//                double vkupno = merenje + curr.element.value.suma;
//                int br = curr.element.value.br + 1;
//                tabela.insert(opstina,new Merenje(vkupno,br));
//            }
//            else
//            {
//                tabela.insert(opstina,new Merenje(merenje,1));
//            }
//        }
//        String opstina = input.next();
//        SLLNode<MapEntry<String,Merenje>> curr = tabela.search(opstina);
//        if(curr!=null)
//        {
//            System.out.printf("%.2f\n",curr.element.value.suma/curr.element.value.br);
//        }
//        else
//        {
//            System.out.println("Nema podatoci");
//        }
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(bufferedReader.readLine());
        Scanner input=new Scanner(System.in);
        CBHT<String,Merenje>merenjeCBHT=new CBHT<>(n);
        for(int i=0;i<n;i++)
        {
            System.out.println(i);
            String opstina = input.next();
            System.out.println("posle opstina");
            double suma = input.nextDouble();
            SLLNode<MapEntry<String,Merenje>>test=merenjeCBHT.search(opstina);
            if(test!=null)
            {
                System.out.println("Ne e null");
                double vkupno=suma+test.element.value.suma;
                int br=test.element.value.br+1;
                merenjeCBHT.delete(opstina);
                merenjeCBHT.insert(opstina,new Merenje(vkupno,br));

            }
            else
            {
                System.out.println("e null");
                Merenje tmp=new Merenje(suma,1);
                merenjeCBHT.insert(opstina,tmp);
            }

        }
        String opstina= bufferedReader.readLine();
        SLLNode<MapEntry<String,Merenje>>test=merenjeCBHT.search(opstina);
        if(test!=null)
        {
            double suma=test.element.value.suma;
            int br=test.element.value.br;
            System.out.println(suma/br);
        }
        else
            System.out.println("Nema podatoci");
    }
}
class Merenje
{
    double suma;
    int br;

    public Merenje(double suma, int br)
    {
        this.suma = suma;
        this.br = br;
    }

}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class MapEntry <K extends Comparable<K>,E> implements Comparable<K>
{
    K key;
    E value;

    public MapEntry (K key,E val)
    {
        this.key = key;
        this.value = val;
    }

    @Override
    public int compareTo(K that) {
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K, E>) that;
        return this.key.compareTo(other.key);
    }

    @Override
    public String toString() {
        return "<" + key + "," + value + ">";
    }
}
class CBHT<K extends Comparable<K>, E> {

    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is
        // equal
        // to targetKey. Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {    // Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                // Make newEntry replace the existing entry ...
                curr.element = newEntry;
                return;
            }
        }
        // Insert newEntry at the front of the 1WLL in bucket b ...
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K,E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }
}