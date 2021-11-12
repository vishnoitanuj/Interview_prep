package Flipkart;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public static List<Integer> spiralOrder(int[][] matrix)
    {
        if(matrix == null || matrix.length == 0)
            return null;
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> res = new ArrayList<>();
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int di=0, r=0, c=0;
        boolean[][] seen = new boolean[m][n];
        for(int i=0;i<m*n;i++){
            res.add(matrix[r][c]);
            System.out.println(matrix[r][c]+" ");
            seen[r][c] = true;
            int cr = r + dr[di];
            int cc = c + dc[di];
            if(cr>=0 && cc>=0 && cr<m && cc<n && !seen[cr][cc]){
                r=cr;
                c=cc;
            } else {
                di = (di+1)%4;
                r += dr[di];
                c += dc[di];
            }
        }
        return res;
    }

    // Driver Code
    public static void main(String[] args)
    {
        int a[][] = { { 1, 2, 3 },
                {51,52,53},
                { 5, 6, 7},
                { 9, 10, 11 },
                { 13, 14, 15 } };

        System.out.println(spiralOrder(a));
    }
}
