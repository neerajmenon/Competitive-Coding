import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Predicate;

import static java.util.Collections.copy;

class Beacon{

    int position;
    int power;
    boolean isDestroyed;

    Beacon(int position, int power){
        this.position = position;
        this.power = power;
        isDestroyed = false;
    }

}

public class Delighted {

    public static void resetBeaconStatus(ArrayList<Beacon> arr){
        Iterator<Beacon> it = arr.iterator();
        while(it.hasNext())
        {
            (it.next()).isDestroyed = false;
        }
    }

    public static int activate(ArrayList<Beacon>arr, Beacon b){

        if(b==null)
            return 0;

        int count = 0;
        arr.remove(b);
        ListIterator<Beacon> li = arr.listIterator(arr.size());

        while(li.hasPrevious()){
            Beacon t = li.previous();
            if(b.position - t.position <= b.power){
                t.isDestroyed = true;
                count++;
            }
        }

        arr.removeIf(new Predicate<Beacon>() {
            @Override
            public boolean test(Beacon beacon) {
                return beacon.isDestroyed;
            }
        });

        return count + activate(arr,arr.size()==0? null : arr.get(arr.size()-1));
    }

    public static void main(String args[])throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n  = Integer.parseInt(br.readLine());

        ArrayList<Beacon> arr = new ArrayList<>();
        int i;
        String input[];
        for(i=0;i<n;i++){
            int power, position;
            input = (br.readLine()).split(" ");
            position = Integer.parseInt(input[0]);
            power = Integer.parseInt(input[1]);
            arr.add(new Beacon(position,power));
        }

        Collections.sort(arr, new Comparator<Beacon>() {
            @Override
            public int compare(Beacon o1, Beacon o2) {
                return o1.position - o2.position;
            }
        });

        HashMap<Integer,Integer> hm = new HashMap<>();
        ListIterator<Beacon> li = arr.listIterator(arr.size());

        while(li.hasPrevious()){
            Beacon b = li.previous();

            ArrayList<Beacon> temp = new ArrayList<>(arr);
            temp.removeIf(new Predicate<Beacon>() {
                @Override
                public boolean test(Beacon beacon) {
                    return beacon.position >= b.position;
                }
            });

            int destroyed = activate(temp,b);
            hm.put(b.position,destroyed);
            resetBeaconStatus(arr);

        }

        int count=0;
        ListIterator<Beacon>lt = arr.listIterator(arr.size()-1);
        int value = hm.get(arr.get(arr.size()-1).position); // if no extra beacon

        while(lt.hasPrevious()){
            Beacon b = lt.previous();
            count++;
            if(hm.get(b.position) + count < value)
                value = hm.get(b.position) + count;
        }
        System.out.println(value);

    }
}
