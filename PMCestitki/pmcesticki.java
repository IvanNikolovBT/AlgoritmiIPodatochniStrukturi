package PMCestitki;

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
public class pmcesticki {
    public static void main (String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        CBHT<String, Integer> pozitivni = new CBHT<>(2*n);
        CBHT<String, Integer> negativni = new CBHT<>(2*n);
        for(int i=0;i<n;i++)
        {
            String vnes= br.readLine();
            String[] splitter=vnes.split("\\s+");
            String opstina=splitter[0];
            String test=splitter[2];
            if(test.equals("негативен"))
            {
                if(negativni.search(opstina)==null)
                    negativni.insert(opstina,1);
                else
                {
                    int old=negativni.search(opstina).element.value;
                    negativni.delete(opstina);
                    negativni.insert(opstina,old+1);
                }
            }
            else
            {
                if(pozitivni.search(opstina)==null)
                    pozitivni.insert(opstina,1);
                else
                {
                    int old=pozitivni.search(opstina).element.value;
                    pozitivni.delete(opstina);
                    pozitivni.insert(opstina,old+1);
                }
            }
            }
            String opstinaT=br.readLine();
            int pp=0,nn=0;
            SLLNode<MapEntry<String ,Integer>> proverka=pozitivni.search(opstinaT);
            if(proverka!=null)
                pp=proverka.element.value;
            proverka=negativni.search(opstinaT);
            if(proverka!=null)
                nn=proverka.element.value;
            double PROCENT=(double)pp/(pp+nn);
        System.out.println(PROCENT);
        }
    }

