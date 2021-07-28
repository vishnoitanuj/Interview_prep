package Graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {

    private List<List<Integer>> adjList;
    private int V;
    public TopologicalSort(int V){
        this.V = V;
        adjList = new ArrayList<>();
        for(int i=0;i<V;i++)
            adjList.add(new ArrayList<>());
    }

    public void addEdge(int v,int w){
        adjList.get(v).add(w);
    }

    public void topologicalSort(){
        Stack<Integer> stack = new Stack<>();
        boolean visited[] = new boolean[V];
        for(int i=0;i<V;i++){
            if(!visited[i])
                topologicalSortUtil(i,visited,stack);
        }

        while (!stack.isEmpty())
            System.out.print(stack.pop() + " ");
    }

    public void topologicalSortUtil(int v,boolean[] visited,Stack<Integer> stack){
        visited[v] = true;
        Iterator<Integer> it = adjList.get(v).iterator();
        while (it.hasNext()){
            int i = it.next();
            if(!visited[i])
                topologicalSortUtil(i, visited, stack);
        }
        stack.push(v);
    }
    public static void main(String[] args) {
        TopologicalSort obj = new TopologicalSort(6);
        obj.addEdge(5, 2);
        obj.addEdge(5, 0);
        obj.addEdge(4, 0);
        obj.addEdge(4, 1);
        obj.addEdge(2, 3);
        obj.addEdge(3, 1);

        obj.topologicalSort();
    }
}
