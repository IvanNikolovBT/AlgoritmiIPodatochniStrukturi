package LAB6.ZAD2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShakerSort {
    static void printArray(int a[]) {
        for(int i = 0; i <a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    static void shakerSort(int[] array, int n) {
        if(n == 1) {
            System.out.println(array[n - 1] + " ");
            System.out.println(array[n - 1] + " ");
            return;
        }
        for (int i = 0; i < n/2; i++) {
            boolean smena = false;
            for (int j = n - 1 - i; j > i; j--) {   // min
                if (array[j] < array[j-1]) {
                    int swap = array[j];
                    array[j] = array[j-1];
                    array[j-1] = swap;
                    smena = true;
                }
            }
            printArray(array);
            for (int j = i; j < n - i - 1; j++) {   // max
                if (array[j] > array[j+1]) {
                    int swap = array[j];
                    array[j] = array[j+1];
                    array[j+1] = swap;
                    smena = true;
                }
            }

            printArray(array);
            if(!smena) break;
        }
    }

    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        shakerSort(a,n);
    }
}