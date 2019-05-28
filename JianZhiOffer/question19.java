import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        if(matrix == null || matrix[0] == null){
            return null;
        }
        int startXY = 0;
        int endX = matrix.length - 1;
        int endY = matrix[0].length - 1;
        ArrayList<Integer> result = new ArrayList<>(matrix.length * matrix[0].length);
        while(startXY <= endX && startXY <= endY){
            printEveryCicle(result, matrix, startXY, startXY++, endX--, endY--);
        }
        return result;
    }
    private void printEveryCicle(ArrayList<Integer> result, int[][] matrix, 
                                 int startX, int startY, int endX, int endY){
        //注意处理单行数据的方法，如果没有，则下面的通用方法不能很好的处理单行数据
        if(startX == endX){
            for(int i = startY; i <= endY; ++i){
                result.add(matrix[startX][i]);
            }
            return;
        }
        if(startY == endY){
            for(int i = startX; i <= endX; ++i){
                result.add(matrix[i][startY]);
            }
            return;
        }
        for(int i = startY; i < endY; ++i){
            result.add(matrix[startX][i]);
        }
        for(int i = startX; i < endX; ++i){
            result.add(matrix[i][endY]);
        }
        for(int i = endY; i > startY; --i){
            result.add(matrix[endX][i]);
        }
        for(int i = endX; i > startX; --i){
            result.add(matrix[i][startY]);
        }
    }
}