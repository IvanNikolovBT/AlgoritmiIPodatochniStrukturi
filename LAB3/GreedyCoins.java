package LAB3;

import java.util.Scanner;

public class GreedyCoins {

    public static int max(int[] niza)
    {
        int maximum=0;
        for (int j : niza) {
            if (j > maximum)
                maximum = j;
        }
        return maximum;
    }
    public static int maxID(int[] niza)
    {
        int maximum=0,INDEX=0;
        for(int i=0;i<niza.length;i++)
        {
            if(niza[i]>maximum)
            {
                maximum=niza[i];
                INDEX=i;
            }
        }
        return INDEX;
    }
    public static int minNumCoins(int []coins, int sum)
    {
        //Vasiot kod ovde
        int brCoins=0;
        for(int i=0;i<coins.length;i++)
        {
            int max=max(coins);
            brCoins=brCoins+sum/max;
            sum=sum-sum/max*max;
            int INDEX=maxID(coins);
            coins[INDEX]=0;
        }
        return brCoins;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String coinsStringLine = input.nextLine();
        String coinsString[] = coinsStringLine.split(" ");
        int coins[] = new int[coinsString.length];
        for(int i=0;i<coinsString.length;i++) {
            coins[i] = Integer.parseInt(coinsString[i]);
        }

        int sum = input.nextInt();


        System.out.println(minNumCoins(coins,sum));
    }
}