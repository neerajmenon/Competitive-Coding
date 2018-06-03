import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;



public class River {

    class AdjListNode {

        public int key;
        public int weight;

        public AdjListNode(int key,int weight){
            this.key = key;
            this.weight = weight;
        }
    }

    LinkedList<AdjListNode> list[];
    boolean visited[];
    int  V,E;
    Stack<Integer> stack;

    public River(int V, int E){
        this.V = V;
        this.E = E;
        list = new LinkedList[V+1];
        for(int i=1;i<=V;i++)
            list[i] = new LinkedList<>();
        visited = new boolean[V+1];
    }

    public void addEdge(int src,int dest,int weight){
        list[src].add(new AdjListNode(dest,weight));
    }

    public void topologicalSortUtil(int i,boolean visited[],Stack s){

        visited[i] = true;
        Iterator<AdjListNode> it = list[i].iterator();
        AdjListNode a;
        while(it.hasNext()){
            a = it.next();
            if(!visited[a.key])
                topologicalSortUtil(a.key,visited,stack);
        }


        stack.push(i);
    }

    public void topologicalSort(){
        stack = new Stack<>();

        for(int i =1;i<=V;i++)
            visited[i] = false;

        int i =1;
      
        if(!visited[i])
            topologicalSortUtil(i,visited,stack);
       
        updateDistances(stack);
    }

    public void updateDistances(Stack s){

        int dist[] = new int[V+1];
        dist[1] = 0;
        for(int i = 2;i<=V;i++)
            dist[i] = Integer.MIN_VALUE;

        while(!s.empty()){
            int u = (Integer) s.pop();
            Iterator<AdjListNode> it = list[u].iterator();
            AdjListNode v;
            while(it.hasNext()){
                v = it.next();
                if(dist[v.key]<dist[u]+v.weight)
                    dist[v.key] = dist[u]+v.weight;
            }
        }
        System.out.println(dist[V]);

    }

    public static void main(String args[])throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[];
        input = (br.readLine()).split(" ");
        int v = Integer.parseInt(input[0]);
        int e = Integer.parseInt(input[1]);
        if(v == 0 || e == 0)
        {
            System.out.println(0);
            return;
        }
        River g = new River(v,e);
        for(int i=0;i<e;i++){
            input = (br.readLine()).split(" ");
            if(input[0]==input[1]) // ignore self loop
                continue;
            g.addEdge(Integer.parseInt(input[0]),Integer.parseInt(input[1]),
                    Integer.parseInt(input[2]));
       
        }


        g.topologicalSort();
    }
}
