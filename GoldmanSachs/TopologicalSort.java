package GoldmanSachs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Set;
import java.util.HashSet;

public class TopologicalSort {
    private static List<List<Integer>> adjList;

    public static void main(String[] args) {
        int n=2;
        String[] words = {"aa","abc"};
        System.out.println(getAlienLanguage(n, words));
    }

    public static char[] getAlienLanguage(int n, String[] dictionary) {
        // Write your code here.
        adjList = new ArrayList<>();
        TopologicalSort obj = new TopologicalSort();
        Set<Character> set = new HashSet<>();
        for(String word: dictionary){
            for(int i=0;i<word.length();i++)
                set.add(word.charAt(i));
        }
        int numberOfNodes = set.size();
        for (int i = 0; i < set.size(); i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            String w1 = dictionary[i];
            String w2 = dictionary[i + 1];
            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    obj.addEdge(w1.charAt(j) - 'a', w2.charAt(j) - 'a');
                    break;
                }
            }
        }
        return obj.topologicalSort(numberOfNodes);
    }


    public void addEdge(int v, int w) {
        adjList.get(v).add(w);
    }

    public char[] topologicalSort(int V) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }
        char[] output = new char[V];
        int count = 0;
        while (!stack.isEmpty())
            output[count++]=(char)(stack.pop() + 'a');
        return output;
    }

    private void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for (int i : adjList.get(v)) {
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }
        stack.push(v);
    }
}

//Space = O(U+V)   U = number of unique characters
// time = O(U+V)

// O(2V) --> O(V) = O(U)
