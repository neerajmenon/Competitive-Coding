import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.DoubleBinaryOperator;

public class Facebook {

    public static long calculate(BufferedReader br)throws IOException{
        String input[];
        input = (br.readLine()).split(" ");
        int t = Integer.parseInt(input[0]);
        int n = Integer.parseInt(input[1]);
        int x = Integer.parseInt(input[2]);
        double f = Double.parseDouble(input[3]);
        double k = x;
        long sum = n;
        int yrs = 1;
        while(yrs<=t){
            int half = n/2;
            if(yrs>1){
                 k = (k*f);
            }
            int nextgen = (int) (half*2*k + half*k);
            sum = sum+nextgen;
            n=nextgen;
            yrs++;
        }

        return sum;
    }

    public static void main(String args[])throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int iter = Integer.parseInt(br.readLine());
        long sum[] = new long[iter];
        int k =0;
        while(k<iter){
            sum[k++] = calculate(br);
        }
        k=0;
        while(k<iter)
            System.out.println(sum[k++]);

    }
}
