import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {

    static int numWays = 0;

    public void countWays(int coins[],int rem,int n){
        if(rem == 0){
            System.out.println("Remainder Zero");
            numWays++;
            return;
        }
        System.out.println("Remainder : "+rem+" Elements left: "+n);
        for(int i=n-1;i>=0;i--){

            int chosen = coins[i];
            System.out.println("CHOSEN: "+chosen+" remainder: "+rem);
            int pos = Arrays.binarySearch(coins,(rem-chosen));
            int k = pos < 0 ? (Math.abs(pos) - 1) : pos+1; // cutoff num_elements 
            if(k>=0)
                countWays(coins,(rem-chosen),k);
        }
    }

    public static void main (String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_cases = Integer.parseInt(br.readLine());
        int n_denoms,payable;
        int coins[]; String input[];

        GFG g = new GFG();

        for(int i=1;i<=test_cases;i++){

            n_denoms = Integer.parseInt(br.readLine());
            input = (br.readLine()).split(" ");
            coins = new int[n_denoms];
            for(int j=0;j<n_denoms;j++)
                coins[j] = Integer.parseInt(input[j]);
            payable = Integer.parseInt(br.readLine());
            Arrays.sort(coins,0,n_denoms-1);
            g.countWays(coins,payable,n_denoms);
            System.out.println(numWays);

        }

    }
}