package question15;

/**
 * 以斜着“之”字形打印二维数组，如1,2,6,11,7,3……，这道题也是利用宏观的思想，设置AB两点，A先向右再向下移动，而B先向下再向右移动
 * 打印AB之间所有的点是非常简单的，这道题的宏观思想也就是此处，另外，设置AB两点也可以完成坐标的解耦。
 * 
 * @author CGWEI
 *
 */
public class Solution {
	public static void main(String[] args) {
		int array[][] = {
				{1,2,3,4,5,6},
				{6,7,8,9,10,11},
				{11,12,13,14,15,16},
				{16,17,18,19,20,21},
				{21,22,23,24,25,26},
				{26,27,28,29,30,31},
		};
		printArrayByZigZag(array);
	}
	
	
	public static void printArrayByZigZag(int[][] array) {
		if(array == null) {
			return;
		}
		int ARow = 0;
		int AColumn = 0;
		int BRow = 0;
		int BColumn = 0;
		int maxRow = array.length - 1;
		int maxColumn = array[0].length - 1;
		boolean upToDown = false;
		while(ARow <= maxRow) {
			printLine(array, ARow, AColumn, BRow, BColumn, upToDown);
			if(AColumn < maxColumn) {
				AColumn++;
			}
			else {
				ARow++;
			}
			if(BRow < maxRow) {
				BRow++;
			}
			else {
				BColumn++;
			}
			upToDown = !upToDown;
		}
	}
	private static void printLine(int[][] array, int ARow, int AColumn, int BRow, int BColumn, boolean upToDown) {
		if(upToDown) {
			while(AColumn >= BColumn) {
				System.out.print(array[ARow++][AColumn--]+"\t");
			}
			System.out.println();
		}
		else {
			while(AColumn >= BColumn) {
				System.out.print(array[BRow--][BColumn++]+"\t");
			}
			System.out.println();
		}
	}
}