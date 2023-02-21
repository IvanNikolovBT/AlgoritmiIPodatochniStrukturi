package LAB3;



import java.util.Scanner;

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;
    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        } else
            return null;
    }

    public E delete(DLLNode<E> node) {
        if (node == first) {
            return deleteFirst();
        }
        if (node == last) {
            return deleteLast();
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int getSize() {
        int listSize = 0;
        DLLNode<E> tmp = first;
        while(tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp.toString();
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp.toString();
            while (tmp.pred != null) {
                tmp = tmp.pred;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

    public void setFirst(DLLNode<E> node) {
        this.first = node;
    }

    public void setLast(DLLNode<E> node) {
        this.last = node;
    }

    public void mirror() {

        DLLNode<E> tmp = null;
        DLLNode<E> current = first;
        last = first;
        while(current!=null) {
            tmp = current.pred;
            current.pred = current.succ;
            current.succ = tmp;
            current = current.pred;
        }

        if(tmp!=null && tmp.pred!=null) {
            first=tmp.pred;
        }
    }
}

public class DLLVojska {

    public static DLL<Integer> vojska(DLL<Integer> lista, int a, int b, int c, int d) {



        DLLNode<Integer> tmp=lista.getFirst();
        //A POCHETOK NA INTERVAL 1 B KRAJ NA INTERVAL 1
        //C POCHETOK NA INTERVAL 2 D KRAJ NA INTERVAL 2
        DLLNode<Integer> posleden=lista.getLast();
        DLLNode<Integer> A=lista.find(a);
        DLLNode<Integer> B=lista.find(b);
        DLLNode<Integer> C=lista.find(c);
        DLLNode<Integer> D=lista.find(d);
       //System.out.println(lista);
       // System.out.println("A "+A+" B"+B+" C"+C+" D"+D);
       // System.out.println(lista);

        while(!tmp.equals(A))
        {

            lista.insertLast(tmp.element);
            //lista.deleteFirst();
            tmp=tmp.succ;
            //dodavaj dodeka ne go najdish A
        }

        //System.out.println("Dodadeni do A");
        tmp=C;
       // System.out.println(tmp);
        //System.out.println(lista);
        while(!tmp.pred.equals(D))
        {
            lista.insertLast(tmp.element);
            tmp=tmp.succ;
            // OD C DODAVAJ DODEKA NE GO NAJDISH D
        }
       // System.out.println("OD C DODAVAJ DODEKA NE GO NAJDISH D");
        tmp=B.succ;
       // System.out.println(tmp);
        //System.out.println(lista);
        while(tmp!=C)
        {
            //DODAVAJ DO NE GO NAJDISH C ( OD B DO C )
            lista.insertLast(tmp.element);
            tmp=tmp.succ;
        }
       // System.out.println("/DODAVAJ DO NE GO NAJDISH C ( OD B DO C )");
        tmp=A;
       // System.out.println(tmp);
       // System.out.println(lista);
        while(tmp.pred!=B)
        {
            //DODAVAJ OD A DO BO
            lista.insertLast(tmp.element);
            tmp=tmp.succ;
            //lista.deleteFirst();
        }
      //  System.out.println("//DODAVAJ OD A DO BO");
       // System.out.println(lista);
        tmp=D.succ;
       // System.out.println(D+" OVA E D TUKA ");
       // System.out.println("PREDKRAJ  "+lista.getSize());
       // System.out.println(lista);
       // System.out.println("\n");
        while(tmp!=posleden.succ)
        {
            //OD D BARAJ GO ELEMENTOT SHO BESHE PRV  i DODAVAJ SE PO PATO
            lista.insertLast(tmp.element);
            tmp=tmp.succ;
        }
        //System.out.println("KRAJ");
        //System.out.println(lista);
        lista.setFirst(tmp);

        return lista;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        DLL<Integer> lista = new DLL<>();
        for(int i=0;i<n;i++) {
            lista.insertLast(input.nextInt());
        }

        int a = input.nextInt();
        int b = input.nextInt();
        //System.out.println(a +" "+b);
        int c = input.nextInt();
        int d = input.nextInt();
        //System.out.println(c +" "+d);
        DLL<Integer> result = vojska(lista, a, b, c, d);

        System.out.println(result);

    }
}