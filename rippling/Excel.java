package rippling;
import java.util.*;
class Excel {
    private static class Cell{
        int val;
        Map<String, Integer> formula = new HashMap<>();
    };

    private Cell[][] table;
    public Excel(int height, char width) {
        table = new Cell[height+1][(int)width-'A'+1];
        for(int i=0;i<=height;i++){
            for(int j=0;j<(width-'A'+1);j++){
                table[i][j] = new Cell();
            }
        }
    }
    
    public void set(int row, char column, int val) {
        Cell cell = table[row][column-'A'];
        cell.formula.clear();
        cell.val = val;
    }
    
    public int get(int row, char column) {
        Cell cell = table[row][column-'A'];
        if(cell.formula.isEmpty()){
            return cell.val;
        } else{
            int sum=0;
            for(Map.Entry<String, Integer> entry: cell.formula.entrySet()){
                sum+= entry.getValue()*get(entry.getKey());
            }
            return sum;
        }
    }

    private int get(String ColRow){
        return get(Integer.parseInt(ColRow.substring(1)), ColRow.charAt(0));
    }
    
    public int sum(int row, char column, String[] numbers) {
        Cell cell = table[row][column-'A'];
        cell.formula.clear();
        for(String s: numbers){
            if(!s.contains(":")){
                cell.formula.put(s, cell.formula.getOrDefault(s, 0)+1);
            } else{
                String[] parts=s.split(":");
                String start = parts[0];
                String end = parts[1];
                
                int startRow = Integer.parseInt(start.substring(1));
                int endRow = Integer.parseInt(end.substring(1));

                char startCol = start.charAt(0);
                char endCol = end.charAt(0);

                for(int i=startRow;i<=endRow;i++){
                    for(char j=startCol;j<=endCol;j++){
                        String key = j+String.valueOf(i);
                        cell.formula.put(key, cell.formula.getOrDefault(key, 0) + 1);
                    }
                }
            }
        }
        return get(row, column);
    }
}

/**
 * Your Excel object will be instantiated and called as such:
 * Excel obj = new Excel(height, width);
 * obj.set(row,column,val);
 * int param_2 = obj.get(row,column);
 * int param_3 = obj.sum(row,column,numbers);
 */