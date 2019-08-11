package Graphs;
import java.util.*;

public class islands {
	
	public static int[]u=new int [5005];
	public static int[]v=new int [5005];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n,m;
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		for(int i=0;i<m;i++)
		{
			u[i]=scan.nextInt();
		}
		for(int i=0;i<m;i++)
		{
			v[i]=scan.nextInt();
		}
		System.out.println(solve(n,m,u,v));
	}
	
	public static int solve(int n,int m,int U[],int V[]) {
        int edges[][]=new int[5005][5005];
        for(int i=0;i<m;i++){
            edges[U[i]][V[i]]=1;
            edges[V[i]][U[i]]=1;
        }
        boolean visited[]=new boolean[5005];
        int c=0;
        for(int i=1;i<n;i++){
            if(!visited[i]){
                bfs(edges,visited,i);
                c++;
            }
        }
        return c;
    }
    
    private static void bfs(int edges[][], boolean visited[],int sv){
    	Queue<Integer> q=new LinkedList<>();
        q.add(sv);
        int n=edges.length;
        visited[sv]=true;
        while(!q.isEmpty()){
            int v=q.remove();
            for(int i=0;i<n;i++){
                if(edges[v][i]==1 && !visited[i]){
                    q.add(i);
                    visited[i]=true;
                }
            }    
        }
    }

}
