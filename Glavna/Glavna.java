package Glavna;
import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Glavna
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        int brP=0,brN=0;
        String []pom = new String[n];
        for(int i=0;i<n;i++)
        {
            String opstina = input.next();
            String prezime = input.next();
            String p_n = input.next();
            pom[i] = opstina + " " + prezime + " " + p_n;
            if(p_n.equals("позитивен"))
            {
                brP++;
            }
            else
            {
                brN++;
            }
        }
        System.out.println("BRP"+brP);
        System.out.println("BRN"+brN);
        CBHT<String,Integer> poz = new CBHT<String,Integer>(2*brP);
        CBHT<String,Integer> neg = new CBHT<String,Integer>(2*brN);

        for(int i=0;i<n;i++) {
            String opstina = pom[i].split(" ")[0];
            String prezime = pom[i].split(" ")[1];
            String p_n = pom[i].split(" ")[2];
            if (p_n.equals("позитивен")) {
                SLLNode<MapEntry<String, Integer>> proverka = poz.search(opstina);
                if (proverka != null) {
                    if(proverka.element.key.equals("Центар"))
                    {
                        System.out.println("vleze vo centar ");
                    }
                    int brPvoOpstina = proverka.element.value;
                    poz.delete(opstina);
                    poz.insert(opstina, brPvoOpstina++);
                } else
                    poz.insert(opstina, 1);
            } else {
                SLLNode<MapEntry<String, Integer>> proverka = neg.search(opstina);
                if (proverka != null) {
                    int brVoOpstina = proverka.element.value;
                    neg.delete(opstina);
                    neg.insert(opstina, brVoOpstina++);
                } else
                    neg.insert(opstina, 1);
            }
        }
        String opstina = input.next();
        int pp=0,nn=0;
        SLLNode<MapEntry<String,Integer>> curr = poz.search(opstina);
        if(curr!=null)
        {
            while(curr!=null)
            {
                if(curr.element.key.equals(opstina))
                {
                    pp=curr.element.value;
                    break;
                }
                curr=curr.succ;
            }
        }
        curr =neg.search(opstina);
        if(curr!=null)
        {
            while(curr!=null)
            {
                if(curr.element.key.equals(opstina))
                {
                    nn=curr.element.value;
                    break;
                }
                curr=curr.succ;
            }
        }
        System.out.println(pp);
        System.out.println(nn);
        float faktor = (float) pp/(nn+pp);
        System.out.println(String.format("%.2f",faktor));

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
        //for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
        //if (key.equals(((MapEntry<K, E>) curr.element).key)) {
        // Make newEntry replace the existing entry ...
        //curr.element = newEntry;
        // return;
        // }
        //}
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