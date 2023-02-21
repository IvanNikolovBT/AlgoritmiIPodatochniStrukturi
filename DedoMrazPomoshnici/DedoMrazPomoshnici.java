package DedoMrazPomoshnici;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "(" + key + "," + value + ")";
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

class CBHT<K extends Comparable<K>, E> {

    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {		// Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                curr.element = newEntry;
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
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

public class DedoMrazPomoshnici {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        CBHT<String, String> dobriDeca = new CBHT<String, String>(2*N);// Vie ja odreduvate goleminata na tabelata

        // vo imeDobriDeca se zachuvuvaat iminjata na dobrite deca
        String[] imeDobriDeca = new String[N];
        // tuka se zachuvuvaat soodvetnite поколони na decata
        String[] poklonDobriDeca = new String[N];
        String pom;
        for (int i = 0; i < N; i++) {
            pom = br.readLine();
            String[] del = pom.split(" ");
            imeDobriDeca[i] = del[0];
            poklonDobriDeca[i] = del[1];
        }

        //tuka se zapishuva imeto na deteto shto treba da se proveri
        String deteZaProverka = br.readLine();
        for(int i=0;i<N;i++)
            dobriDeca.insert(imeDobriDeca[i].toLowerCase(),poklonDobriDeca[i]);
        deteZaProverka=deteZaProverka.toLowerCase();
        if(deteZaProverka.contains("ch")||deteZaProverka.contains("zh")||deteZaProverka.contains("sh"))
        {

            System.out.println("gi naogja bukvite\n\n");
            String replaceString=deteZaProverka.replaceAll("ch","c");
            replaceString=replaceString.replaceAll("zh","z");
            replaceString=replaceString.replaceAll("sh","s");
            System.out.println(replaceString);
            if(dobriDeca.search(replaceString)==null)
            {
                System.out.println("Go nema vo listata ama imashhe ch zh sh vo nea");
                System.out.println("Nema poklon");
                return;
            }
            else
            {
                System.out.println("go ima vo listata so ch zh sh");
                SLLNode<MapEntry<String,String >> pomosh=dobriDeca.search(replaceString);
                System.out.println(pomosh);
                System.out.println(pomosh.element.value);
            }

        }
        else
        {
            if(dobriDeca.search(deteZaProverka)==null)
            {
                System.out.println("nema ch zh sh i go nema vo listata");
                System.out.println("Nema poklon");
                return;
            }
            else
            {
                System.out.println("nema ch zh sh i go ima vo listata");
                SLLNode<MapEntry<String,String >> pomosh=dobriDeca.search(deteZaProverka);
                System.out.println(pomosh.element.value);
            }
        }


    }
}