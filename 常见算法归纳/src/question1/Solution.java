package question1;
/**
 * ��һ����ά�����У�ÿ��һά����ĳ�����ͬ����ÿһ�ж����մ����ҵ�����˳������ÿһ�ж����մ��ϵ��µ�����˳������
 * �����һ������������������һ����ά�����һ���������ж��������Ƿ��и�������
 * 
 * ˼·����ͳ�ķ�����������ʼ����ʼ���ң���Ϊ���º����Ҷ��д����Լ������֣����Է���ȷ����
 * ��ˣ�����������½���Ϊ��ʼ�㿪ʼ���ң�������С�����ϣ�����������ҡ�
 * ��Ϊ���������������ĳһ�㴦�����·�һ�������Լ��������Ϸ�һ����С���Լ���
 * ��ˣ�ͨ��������ʣ������½���Ϊ��ʼ�㣬���ϵ����Ϻ����ң�������������»��ݣ�������ҵ�Ŀ�ꡣ
 * 
 * @author CGWEI
 * 
 */
public class Solution {
	public static void main(String[] args) {
		
	}
	public boolean Find(int target, int [][] array) {
        int actualColumn = array[0].length - 1;
        int row = array.length - 1;
        int column = 0;
        while(row >= 0 && column <= actualColumn){
            if(target == array[row][column]){
                return true;
            }
            else if(target > array[row][column]){
                column++;
            }
            else{
                row--;
            }
        }
        return false;
    }
}