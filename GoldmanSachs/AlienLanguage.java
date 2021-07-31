package GoldmanSachs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class AlienLanguage {

    private int V;
    private List<List<Integer>> adjList;

    public AlienLanguage(int V){
        this.V = V;
        adjList = new ArrayList<>();
        for(int i=0;i<V;i++){
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int v,int w){
        adjList.get(v).add(w);
    }

    public void topologicalSort(){
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        for(int i=0;i<V;i++){
            if(!visited[i])
                topologicalSortUtil(i, visited, stack);
        }

        while (!stack.isEmpty())
            System.out.print((char)(stack.pop()+'a') + "\s");
    }

    private void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v]=true;
        for (int i : adjList.get(v)) {
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }
        stack.push(v);
    }

    public void printOrder(String[] words){
        for(int i=0;i<words.length-1;i++){
            String w1 = words[i];
            String w2 = words[i+1];
            for(int j=0;j<Math.min(w1.length(), w2.length());j++){
                if(w1.charAt(j)!=w2.charAt(j)) {
                    addEdge(w1.charAt(j) - 'a', w2.charAt(j) - 'a');
                    break;
                }
            }
        }
        topologicalSort();
    }

    public static void main(String[] args) {
        AlienLanguage obj = new AlienLanguage(4);
        String[] words = {"baa", "abcd", "abca", "cab", "cad"};
        obj.printOrder(words);
    }
}
