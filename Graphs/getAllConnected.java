package Graphs;
import java.util.*;

public class getAllConnected {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int V = s.nextInt();
		int E = s.nextInt();

		int edges[][]=new int [V][V];
        for(int i=0;i<E;i++){
            int fv=s.nextInt();
            int sv=s.nextInt();
            edges[fv][sv]=1;
            edges[sv][fv]=1;
        }
        boolean visited[]=new boolean[V];
        for(int i=0;i<edges.length;i++){
            if(!visited[i]){
                ArrayList<Integer> out=getConnected(edges,visited,i);
                if(out!=null){
                    for(int j:out)
                        System.out.print(j+" ");
                }
                System.out.println();
            }
        }
	}
    
    private static ArrayList<Integer> getConnected(int edges[][], boolean visited[],int sv){
        ArrayList<Integer> out=new ArrayList<>();
        Queue<Integer> q=new LinkedList<>();
        q.add(sv);
        visited[sv]=true;
        out.add(sv);
        while(!q.isEmpty()){
            int v=q.remove();
            for(int i=0;i<edges.length;i++){
                if(edges[v][i]==1 && !visited[i]){
                    q.add(i);
                    visited[i]=true;
                    out.add(i);
                }
            }
        }
        return out;
    }

}
