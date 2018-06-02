import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Partition {

    public static boolean partition(int arr[],int n,int sum){
        boolean part[][] = new boolean[sum/2 + 1][n+1];
        int i,j;

        for(i=1;i<=sum/2;i++)
            part[i][0] = false;
        for(i=0;i<=n;i++)
            part[0][i] = true;

        for (i = 1; i <= sum/2; i++)
        {
            for (j = 1; j <= n; j++)
            {
                part[i][j] = part[i][j-1];
                if (i >= arr[j-1])
                    part[i][j] = part[i][j] || part[i - arr[j-1]][j-1];
            }
        }

        return part[sum/2][n];
    }

    public static boolean isSubsetSum(int arr[],int n,int sum){
        if(sum == 0)
            return true;
        if(sum!=0 && n==0)
            return false;
        if(arr[n-1]>sum)
            return isSubsetSum(arr,n-1,sum);

        return isSubsetSum(arr,n-1,sum) || isSubsetSum(arr,n-1,sum-arr[n-1]);
    }

    public static void main(String args[])throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int arr[] = new int[n];
        String input[] = (br.readLine()).split(" ");

        int sum=0;
        for(int i = 0;i<n;i++){
            arr[i] = Integer.parseInt(input[i]);
            sum+=arr[i];
        }
        if(sum%2 == 1) // sum is odd, partition doesnt exist
        {
            System.out.println(-1);
            return;
        }

        if(isSubsetSum(arr,n,sum/2))
            System.out.println(sum/2);
    }
}
