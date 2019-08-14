package Graphs;

//  https://www.hackerearth.com/problem/algorithm/kingdom-and-its-roads/
import java.util.*;

class KingdomRoads {
    static class Pair implements Comparable<Pair>{
        int u,v,w;
        Pair(int u,int v,int w){
            this.u=u;
            this.v=v;
            this.w=w;
        }
        public int compareTo(Pair o){
            return this.w-o.w;
        }
    }
    public static void main(String args[] ) throws Exception {
        Scanner d=new Scanner(System.in);
        int t=d.nextInt();
        while(t-->0){
            int n=d.nextInt();
            int m=d.nextInt();
            ArrayList<Pair> adj=new ArrayList<>();
            for(int i=0;i<m;i++){
                int u=d.nextInt();
                int v=d.nextInt();
                Pair p=new Pair(u,v,0);
                adj.add(p);
            }
            int k=d.nextInt();
            for(int i=0;i<k;i++){
                int u=d.nextInt();
                int v=d.nextInt();
                int w=d.nextInt();
                Pair p=new Pair(u,v,w);
                adj.add(p);
            }
            Collections.sort(adj);
            parent=new int[n+1];
            height=new int[n+1];
            for(int i=1;i<=n;i++)
                parent[i]=i;
            int cost=0;
            for(int i=0;i<adj.size();i++){
                Pair p=adj.get(i);
                if(find(p.u)!=find(p.v)){
                    union(p.u,p.v);
                    cost+=p.w;
                }
            }
            System.out.println(cost);
        }

    }
    
    static int[] parent, height;
    public static int find(int x){
        while(x!=parent[x])
            x=parent[parent[x]];
        return x;
    }
    
    public static void union(int u,int v){
        int x=find(u);
        int y=find(v);
        if(height[x]<height[y])
            parent[x]=y;
        else if(height[x]>height[y])
            parent[y]=x;
        else{
            parent[y]=x;
            height[x]++;
        }
            
    }
}

