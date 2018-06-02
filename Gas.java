import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Gas {

    public static void matrixCopy(int temp[][],int arr[][],int m,int n){
        for(int i =0;i<m;i++){
            System.arraycopy(temp[i],0,arr[i],0,n);
        }
    }

    public static void printMatrix(int arr[][],int m,int n){
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String args[])throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[];
        input = ((br.readLine())).split(" ");
        int m = Integer.parseInt(input[0]);
        int n = Integer.parseInt(input[1]);
        int temp[][] = new int[m][n];
        for(int i =0;i<m;i++){
            input = (br.readLine()).split(" ");
            for(int j=0;j<n;j++)
                temp[i][j] = Integer.parseInt(input[j]);
        }
        int arr[][] = new int[m][n];
        matrixCopy(temp,arr,m,n);

        int i,j;
        boolean changed = true;
        int count = -1;
        while(changed){
            count++;
            System.out.println(count);
            printMatrix(arr,m,n);
            System.out.println();
            changed = false;
            for(i=0;i<m;i++){
                for(j=0;j<n;j++){

                    if(arr[i][j]==2){
                        if(i>0 && arr[i-1][j]==1){
                            changed = true;
                            temp[i-1][j] = 2;
                        }
                        if(j>0 && arr[i][j-1]==1){
                            changed = true;
                            temp[i][j-1] = 2;
                        }
                        if(i<m-1 && arr[i+1][j]==1){
                            changed = true;
                            temp[i+1][j] = 2;
                        }
                        if(j<n-1 && arr[i][j+1]==1){
                            changed = true;
                            temp[i][j+1] = 2;
                        }
                    } // else continue
                }
            }
            matrixCopy(temp,arr,m,n);
        }

        boolean survive = false;
        for(i=0;i<m;i++)
            for(j=0;j<n;j++)
                if(arr[i][j] == 1){
                    survive = true;
                    break;
                }

        if(survive)
            System.out.println("AMIT SURVIVES");
        else
            System.out.println(count);

    }
}
