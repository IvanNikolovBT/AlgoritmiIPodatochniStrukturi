package Rodendeni;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Rodendeni
{
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(bufferedReader.readLine());
        CBHT<String,String> rodendeni=new CBHT<String,String>(n);
        for(int i=0;i<n;i++)
        {
            String vlez= bufferedReader.readLine();
            String[]splitter=vlez.split("\\s+");
            String ime=splitter[0];
            String tmpmesec=splitter[1];
            String[] tmp=tmpmesec.split("\\.");
            String mesec=tmp[1];
            SLLNode<MapEntry<String,String>> proverka=rodendeni.search(mesec);
            if(proverka!=null)
            {
                if(proverka.element.value.contains(ime))
                {

                }
                else
                {
                    String old=proverka.element.value;
                    old=old+" "+ime;
                    rodendeni.delete(mesec);
                    rodendeni.insert(mesec,old);
                }

            }
            else
            {
                rodendeni.insert(mesec,ime);
            }
        }
        String mesec= bufferedReader.readLine();
        SLLNode<MapEntry<String,String>> rez=rodendeni.search(mesec);
        if(mesec!=null)
        System.out.println(rez.element.value);
        else System.out.println("nema rodeni vo toj mesec");
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
    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K, E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is
        // equal
        // to targetKey. Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {    // Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                //Make newEntry replace the existing entry ...
                if (val.equals(curr.element.value)) {
                    curr.element = newEntry;
                    return;
                }
            }
        }
        // Insert newEntry at the front of the 1WLL in bucket b ...
        if (buckets[b] == null) {
            buckets[b] = new SLLNode<MapEntry<K, E>>(newEntry, buckets[b]);
        } else {
            SLLNode<MapEntry<K, E>> curr = buckets[b];
            while (curr.succ != null) {
                curr = curr.succ;
            }
            curr.succ = new SLLNode<MapEntry<K, E>>(newEntry, null);
        }

    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
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
            for (SLLNode<MapEntry<K, E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }
}