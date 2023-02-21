package Radar;

//prviot del od inputot e info za tablici i vozacite
//potoa se dava max dozvolenata brzina
//potoa se dava lista od site tablici skenirani denes
//na output se bara ime i prezime na site so vozele prebrzo
//sortirani po vremenski redosled

//INPUT:


//OUTPUT:
//ime prezime


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

public class zadaca_kolok {

    public static boolean pokasno(String t1,String t2)
    {
        String[] split1 =t1.split(":");
        String[] split2 =t2.split(":");
        int h1=Integer.parseInt(split1[0]);
        int h2=Integer.parseInt(split2[0]);
        int m1=Integer.parseInt(split1[1]);
        int m2=Integer.parseInt(split2[1]);
        int s1=Integer.parseInt(split1[2]);
        int s2=Integer.parseInt(split2[2]);
        if(h1>h2)
            return true;
        if(h1==h2 && m1>m2)
            return true;
        if(h1==h2 && m1==m2 && s1>s2)
            return true;
        return false;
    }

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(bufferedReader.readLine());
        CBHT<String,String> radar=new CBHT<>(n);//hesh tabela
        String[] informacii=new String[n]; //celosni informacii
        for(int i=0;i<n*n;i++)
        {
            String vnes= bufferedReader.readLine();
            informacii[i]=vnes;
            String[]splitter=vnes.split("\\s+");
            radar.insert(splitter[0], splitter[1] + " " + splitter[2]);

            }
        int brzina=Integer.parseInt(bufferedReader.readLine());
        String[] vlez= bufferedReader.readLine().split("\\s+"); // celiot input
        String[] luge=new String[vlez.length/3];
        String[] vreme=new String[vlez.length/3];
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss");
        for(int i=0,k=0;i<n;i=i+3)
        {
            String registracija=vlez[i];
            int brVozac=Integer.parseInt(vlez[i+1]);
            String vremeS=vlez[i+2];
            if(brVozac>brzina)
            {
                luge[k]=registracija;
                vreme[k]=vremeS;
                k++;
            }
        }


    }
}
class Vozac{
    String ime;
    String prezime;
    Date time;

    public Vozac(String ime, String prezime,Date time) {
        this.ime = ime;
        this.prezime = prezime;
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
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