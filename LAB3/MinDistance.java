package LAB3;

import java.util.Scanner;
import java.lang.Math;
public class MinDistance {


   public static float calculateDistance(float x1,float x2,float y1, float y2)
   {
        double x=x1-x2;
        double y=y1-y2;
       return (float)(Math.sqrt(Math.pow(x,2)-Math.pow(y,2)));

   }
    public static float minimalDistance(float points[][])
    {

        //Vasiot kod ovde
        float minimal=100000;
        int n=points.length;
        for(int i=0;i<n;i++) {
            for (int j = i + 1; j < n; j++) {
                System.out.println(points[i][0]+" "+" "+ points[j][0]+" "+points[i][1]+" "+" "+ points[j][1]);
                //System.out.println(calculateDistance(points[i][0], points[j][0], points[i][1], points[j][1])+"\n");
                if (calculateDistance(points[i][0], points[j][0], points[i][1], points[j][1]) < minimal)
                    minimal = calculateDistance(points[i][0], points[j][0], points[i][1], points[j][1]);
            }
        }
        return minimal;
    }

    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();

        float points[][] = new float[N][2];

        for(int i=0;i<N;i++) {
            points[i][0] = input.nextFloat();
            points[i][1] = input.nextFloat();
        }

        System.out.printf("%.2f\n", minimalDistance(points));
    }
}