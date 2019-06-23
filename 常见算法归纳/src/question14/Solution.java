package question14;



/**
 * תȦ��ӡ����
 * �������ξ�����ת90��
 * �����ô��˼�룬һȦһȦ�Ľ��б���
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
		printByCircle(array);
		System.out.println();
		rotateMatrix(array);
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[0].length; j++) {
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
	}
	/**
	 * תȦ��ӡ����
	 * ��Ҫ˼·������һ�ִ�ֹۣ�һȦһȦ�Ĵ�ӡ���ȴ�ӡ(0,0)-(length-1,length-1)Χ�ɵ�Ȧ���ٴ�ӡ��(1,1)-(length-2,length-2)Χ�ɵ�Ȧ����
	 * @param array
	 */
	public static void printByCircle(int[][] array) {
		if(array == null) {
			return;
		}
		int leftX = 0;
		int leftY = 0;
		int rightX = array.length - 1;
		int rightY = array[0].length - 1;
		while(leftX <= rightX && leftY <= rightY) {
			printEachCircle(array, leftX++, leftY++, rightX--, rightY--);
		}
	}
	private static void printEachCircle(int[][] array, int leftX, int leftY, int rightX, int rightY) {
		if(leftX == rightX) {
			for(int i = leftY; i <= rightY; i++) {
				System.out.print(array[leftX][i]+" ");
			}
		}
		else if(leftY == rightY) {
			for(int i = leftX; i <= rightX; i++) {
				System.out.print(array[i][leftY]+" ");
			}
		}
		else {
			int currentX = leftX;
			int currentY = leftY;
			while(currentY < rightY) {
				System.out.print(array[currentX][currentY++]+" ");
			}
			while(currentX < rightX) {
				System.out.print(array[currentX++][currentY]+" ");
			}
			while(currentY > leftY) {
				System.out.print(array[currentX][currentY--]+" ");
			}
			while(currentX > leftX) {
				System.out.print(array[currentX--][currentY]+" ");
			}
		}
	}
	
	/**
	 * �õ���˼·���������һ�£�ÿ�ζ�ÿһȦ������ת
	 * ���嵽ĳ��Ȧ�Ĺ����ǣ�һ���ƶ��������϶�Ӧλ�õ��ĸ��㣬ʹ��һ���߳��ȵ�ѭ�������������
	 * @param array
	 */
	public static void rotateMatrix(int[][] array) {
		if(array == null) {
			return;
		}
		//��ΪҪ����ת���������Σ�����ÿ�������ʹ��һ���������ܱ�ʾ
		int leftPoint = 0;
		int rightPoint = array.length - 1;
		while(leftPoint < rightPoint) {
			rotateEachCircle(array, leftPoint++, rightPoint--);
		}
	}
	private static void rotateEachCircle(int[][] array, int leftPoint, int rightPoint) {
		for(int i = 0; i < rightPoint-leftPoint; i++) {
			int temp = array[leftPoint][leftPoint+i];
			array[leftPoint][leftPoint+i] = array[rightPoint-i][leftPoint];
			array[rightPoint-i][leftPoint] = array[rightPoint][rightPoint-i];
			array[rightPoint][rightPoint-i] = array[leftPoint+i][rightPoint];
			array[leftPoint+i][rightPoint] = temp;
		}
//		for(int i = leftPoint; i < rightPoint; i++) {
//			int temp = array[leftPoint][i];
//			array[leftPoint][i] = array[rightPoint-i][leftPoint];
//			array[rightPoint-i][leftPoint] = array[rightPoint][rightPoint-i];
//			array[rightPoint][rightPoint-i] = array[i][rightPoint]; 
//			array[i][rightPoint] = temp;
//		}
	}
}