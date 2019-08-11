package Graphs;

import java.util.Scanner;

public class PrimsMST {
	
	public static void prims(int a[][]) {
		int parent[]=new int[a.length];
		boolean visited[]=new boolean[a.length];
		int weight[]=new int[a.length];
		weight[0]=0;
		for(int i=1;i<a.length;i++)
			weight[i]=Integer.MAX_VALUE;
		for(int i=0;i<a.length;i++) {
			int min=findMinVertex(weight,visited);
			visited[min]=true;
			for(int j=0;j<a.length;j++) {
				if(a[min][j]!=0 && !visited[j]) {
					if(a[min][j]<weight[j]) {
						weight[j]=a[min][j];
						parent[j]=min;
					}
				}
			}
		}
		for(int i=1;i<a.length;i++) {
			if(parent[i]<i)
				System.out.println(parent[i]+" "+i+" "+weight[i]);
			else
				System.out.println(i+" "+parent[i]+" "+weight[i]);
		}
	}
	
	public static int findMinVertex(int a[],boolean visited[]) {
		int min=-1;
		for(int i=0;i<a.length;i++) {
			if(!visited[i] && (min==-1 || a[i]<a[min]))
				min=i;
		}
		return min;
	}

	public static void main(String[] args) {
		Scanner d=new Scanner(System.in);
		int V=d.nextInt();
		int E=d.nextInt();
		int a[][]=new int[V][V];
		for(int i=0;i<E;i++) {
			int sv=d.nextInt();
			int ev=d.nextInt();
			int w=d.nextInt();
			a[sv][ev]=w;
			a[ev][sv]=w;
		}
		prims(a);

	}

}
