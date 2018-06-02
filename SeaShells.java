import java.io.*;
import java.util.*;

class ShinyObject{
    int rank;
    int quantity;

    ShinyObject(int rank, int quantity){
        this.rank = rank;
        this.quantity = quantity;
    }


}

public class SeaShells {


    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[], input2[];
        input = (br.readLine()).split(" ");
        int n = Integer.parseInt(input[0]);
        int fam = Integer.parseInt(input[1]);
        int friends  = Integer.parseInt(input[2]);


        ArrayList<ShinyObject> arr = new ArrayList<>();
        input = (br.readLine()).split(" ");
        input2 = (br.readLine()).split(" ");
        long sum = 0;
        for(int i=0;i<n;i++){
            int rank = Integer.parseInt(input[i]);
            int quant = Integer.parseInt(input2[i]);
            arr.add(new ShinyObject(rank,quant));
            sum+=(rank*quant);
        }


        Collections.sort(arr, Collections.reverseOrder(new Comparator<ShinyObject>() {
            @Override
            public int compare(ShinyObject o1, ShinyObject o2) {
                return o1.rank - o2.rank;
            }
        }));

        int nsv = (int) (sum / (fam+friends));
        int min = 0,count = 0;

        Iterator<ShinyObject> it = arr.iterator();

        while (it.hasNext()) {
            ShinyObject s = it.next();
            min = Math.floorDiv(nsv,s.rank); // 134 / 3 = 44

            nsv = nsv - (min*s.rank); // 134 - (3*44) = 2 left
            count+=min;

            if(nsv == 0)
                break;
        }
        System.out.println(count);



    }
}