package Datumi;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class MapEntry<K extends Comparable<K>, E> implements Comparable<K>{
    K key;
    E value;
    public MapEntry(K key, E val){
        this.key=key;
        this.value=val;
    }
    public int compareTo(K that){
        @SuppressWarnings("unchecked")
        MapEntry<K, E> other = (MapEntry<K, E>) that;
        return this.key.compareTo(other.key);
    }
    public String toString(){
        return "<"+key+","+value+">";
    }

    public K getKey() {
        return key;
    }

    public E getValue() {
        return value;
    }
}

class SLLNode<E>{
    protected E element;
    protected SLLNode<E> succ;
    public SLLNode(E elem, SLLNode<E> succ){
        this.element=elem;
        this.succ=succ;
    }
    @Override
    public String toString(){
        return element.toString();
    }
}

class CBHT<K extends Comparable<K>, E>{
    private SLLNode<MapEntry<K, E>>[] buckets;
    @SuppressWarnings("unchecked")
    public CBHT(int m){
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }
    private int hash(K key){
        return Math.abs(key.hashCode())% buckets.length;
    }
    public SLLNode<MapEntry<K, E>> search (K targetKey){
        int b = hash(targetKey);
        for(SLLNode<MapEntry<K, E>> curr = buckets[b]; curr!=null; curr=curr.succ){
            if (targetKey.equals(((MapEntry<K, E>)curr.element).key))
                return curr;
        }
        return null;
    }
    public void insert(K key, E val){
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for(SLLNode<MapEntry<K, E>> curr = buckets[b]; curr!=null; curr=curr.succ){
            if (key.equals(((MapEntry<K, E>)curr.element).key)){
                curr.element=newEntry;
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry<K, E>>(newEntry, buckets[b]);
    }
    public void delete(K key){
        int b =hash(key);
        for(SLLNode<MapEntry<K, E>> pred = null, curr = buckets[b]; curr!=null; pred=curr, curr=curr.succ){
            if (key.equals(((MapEntry<K, E>)curr.element).key)){
                if (pred==null)
                    buckets[b]=curr.succ;
                else
                    pred.succ=curr.succ;
                return;
            }
        }
    }
}
public class Datumi {
    public static void main (String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        CBHT<String, List<String>> tabela = new CBHT<String, List<String>>(2*n);
       for(int i=0;i<n;i++)
       {
           String vlez= br.readLine();
           String[]splitter=vlez.split("\\s+");
           String ime=splitter[0];
           String prezime=splitter[1];
           String datum=splitter[2];
           String[]datumSplit=datum.split("/");
           String mesec=datumSplit[1];
           SLLNode<MapEntry<String,List<String>>>rez=tabela.search(mesec);
            if(rez!=null)
            {
                System.out.println(" Go dodavam"+ime);
                List<String> nov=rez.element.value;
                nov.add(ime+prezime);
                tabela.delete(mesec);
                tabela.insert(mesec,nov);
            }
            else
            {
                System.out.println("Skroz novo");
                List<String> nov=new ArrayList<>();
                nov.add(ime+prezime);
                tabela.insert(mesec,nov);
            }
       }
       String vlez= br.readLine();
       String[] splitter=vlez.split("/");
       String mesec=splitter[1];
       SLLNode<MapEntry<String,List<String>>> rez=tabela.search(mesec);
       if(rez!=null)
       {
           List<String> da=rez.element.value;
           int x=da.size();
           int i=0;
           while(x!=0)
           {
               System.out.println(da.get(i));
               x--;
           }

       }
       else System.out.println("nema nikoj vo toj mesec");


    }
}
