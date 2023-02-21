package LAB9.ZAD3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;

class BNode<E> {

    public E info;
    public boolean visited=false;
    public BNode<E> left;
    public BNode<E> right;
    char ltag;
    char rtag;

    static int LEFT = 1;
    static int RIGHT = 2;

    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
        ltag = '-';
        rtag = '-';
    }

}

class BTree<E> {

    public BNode<E> head;

    public BTree() {
        head = new BNode<E>(null);
        // po definicija ako nema koren, t.e. ako stebloto e prazno
        head.left = head;
        head.ltag = '-';
        // kaj vodacot sekogas desnata vrska pokazuva kon samiot sebe
        head.right = head;
        head.rtag = '+';
    }

    public BNode<E> makeRoot(E elem) {
        BNode<E> tmp = new BNode<E>(elem);
        head.left = tmp;
        head.ltag = '+';

        tmp.left = head;
        tmp.ltag = '-';
        tmp.right = head;
        tmp.rtag = '-';

        return tmp;
    }

    public BNode<E> makeRootNode(BNode<E> tmp) {
        head.left = tmp;
        head.ltag = '+';

        tmp.left = head;
        tmp.ltag = '-';
        tmp.right = head;
        tmp.rtag = '-';

        return tmp;
    }

    public BNode<E> addChild(BNode<E> node, int where, E elem) {
        BNode<E> tmp = new BNode<E>(elem);

        if (where == BNode.LEFT) {

            if (node.ltag == '+')   // veke postoi element
                return null;

            tmp.left = node.left;
            tmp.ltag = '-';
            tmp.right = node;
            tmp.rtag = '-';
            node.left = tmp;
            node.ltag = '+';
        } else {

            if (node.rtag == '+')   // veke postoi element
                return null;

            tmp.right = node.right;
            tmp.rtag = '-';
            tmp.left = node;
            tmp.ltag = '-';
            node.right = tmp;
            node.rtag = '+';
        }

        return tmp;
    }

    public BNode<E> addChildNode(BNode<E> node, int where, BNode<E> tmp) {

        if (where == BNode.LEFT) {

            if (node.ltag == '+')   // veke postoi element
                return null;

            tmp.left = node.left;
            tmp.ltag = '-';
            tmp.right = node;
            tmp.rtag = '-';
            node.left = tmp;
            node.ltag = '+';
        } else {

            if (node.rtag == '+')   // veke postoi element
                return null;

            tmp.right = node.right;
            tmp.rtag = '-';
            tmp.left = node;
            tmp.ltag = '-';
            node.right = tmp;
            node.rtag = '+';
        }

        return tmp;
    }

    public BNode<E> insertRight(BNode<E> parent, E info) {

        BNode<E> child = new BNode<E>(info);

        child.ltag = '-';
        child.left = parent;
        child.rtag = parent.rtag;
        child.right = parent.right;

        parent.right = child;
        parent.rtag = '+';

        if (child.rtag == '+') {
            BNode<E> temp = child.right;
            while (temp.ltag == '+')
                temp = temp.left;
            temp.left = child;
        }

        return child;
    }


    public boolean intOrder(BNode<Integer> node)
    {
        if(node==null) {
            System.out.println("Pagja na list");
            return true;

        }
        if(!intOrder(node.left))
        {
            System.out.println("pagja na left");
        return false;
        }
        if(node.left!=null&&node.info!=(node.left.info+1))
        {
            System.out.println("pagja na desno");
            return false;
        }
        System.out.println("Pred desno na kraj");
        return intOrder(node.right);
    }
    public boolean checkNodes(BNode<Integer> node,int prev)
    {
       if(node==null || node.visited)
           return true;
       if(node!=null&&node.info != prev+1)
           return false;
       prev=node.info;
       node.visited=true;
       return checkNodes(node.left,prev)&&checkNodes(node.right,prev);
    }

//    public static boolean checkNodes(BNode<Integer> root)
//    {
//        Stack<BNode<Integer>> stack=new Stack<>();
//        BNode<Integer> curr=root;
//        int prev=Integer.MAX_VALUE;
//        while(curr!=null ||!stack.isEmpty())
//        {
//            while (curr!=null)
//            {
//                stack.push(curr);
//                curr=curr.left;
//            }
//            curr=stack.pop();
//            if(curr.info!=prev+1)
//            {
//                System.out.println("false");
//                return false;
//            }
//            prev=curr.info;
//            curr=curr.right;
//        }
//        System.out.println("true");
//        return true;
//    }
//        public boolean da(BNode<Integer> node)
//        {
//            if(node.left==null &node.right==null || node.visited)
//            {
//                node.visited=true;
//                return true;
//            }
//            if(node.left!=null&&node.left.info+1!=node.info)
//               return false;
//            node.visited=true;
//            if(node.right!=null&&node.right.info!=node.info+1)
//                return false;
//            node.visited=true;
//            return da(node.right);
//        }

}

public class ConsecutiveNumbers {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());


        @SuppressWarnings("unchecked")
        BNode<Integer> nodes[] = new BNode[N];
        BTree<Integer> tree = new BTree<Integer>();

        for (i=0;i<N;i++)
            nodes[i] = null;

        for (i = 0; i < N; i++) {
            String line = br.readLine();
            st = new StringTokenizer(line);
            int index = Integer.parseInt(st.nextToken());
            nodes[index] = new BNode<Integer>(Integer.parseInt(st.nextToken()));
            String action = st.nextToken();
            if (action.equals("LEFT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
            } else if (action.equals("RIGHT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
            } else {
                // this node is the root
                tree.makeRootNode(nodes[index]);
            }
        }

        br.close();

        // vasiot kod ovde
        //System.out.println(tree.intOrder(tree.head));
        System.out.println(tree.checkNodes(tree.head,Integer.MIN_VALUE));



    }

}
