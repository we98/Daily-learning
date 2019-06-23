package question39;


/**
 * 
 * @author CGWEI
 *
 */

public class Solution {
	
	public static final int M = 10000; // ����������
    
    public static void main(String[] args) {
        // ��ά����ÿһ�зֱ��� A��B��C��D��E ���㵽�����ľ���, 
        // A -> A ����Ϊ0, ����M Ϊ������
        int[][] weight1 = {
                {0,4,M,2,M}, 
                {4,0,4,1,M}, 
                {M,4,0,1,3}, 
                {2,1,1,0,7},   
                {M,M,3,7,0} 
            };

        int start = 0;
        
        int[] shortPath = dijkstra(weight1, start);

        for (int i = 0; i < shortPath.length; i++)
            System.out.println("��" + start + "������" + i + "����̾���Ϊ��" + shortPath[i]);
    }

    public static int[] dijkstra(int[][] weight, int start) {
        // ����һ������ͼ��Ȩ�ؾ��󣬺�һ�������start����0��ţ�������������У�
        // ����һ��int[] ���飬��ʾ��start���������·������
        int n = weight.length; // �������
        int[] shortPath = new int[n]; // ����start��������������·��
        String[] path = new String[n]; // ����start�������������·�����ַ�����ʾ
        for (int i = 0; i < n; i++)
            path[i] = new String(start + "-->" + i);
        int[] visited = new int[n]; // ��ǵ�ǰ�ö�������·���Ƿ��Ѿ����,1��ʾ�����

        // ��ʼ������һ�������Ѿ����
        shortPath[start] = 0;
        visited[start] = 1;

        for (int count = 1; count < n; count++) { // Ҫ����n-1������
            int k = -1; // ѡ��һ�������ʼ����start�����δ��Ƕ���
            int dmin = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (visited[i] == 0 && weight[start][i] < dmin) {
                    dmin = weight[start][i];
                    k = i;
                }
            }

            // ����ѡ���Ķ�����Ϊ��������·�����ҵ�start�����·������dmin
            shortPath[k] = dmin;
            visited[k] = 1;

            // ��kΪ�м�㣬������start��δ���ʸ���ľ���
            for (int i = 0; i < n; i++) {
                //��� '��ʼ�㵽��ǰ�����' + '��ǰ�㵽ĳ�����' < '��ʼ�㵽ĳ�����', �����
                if (visited[i] == 0 && weight[start][k] + weight[k][i] < weight[start][i]) {
                    weight[start][i] = weight[start][k] + weight[k][i];
                    path[i] = path[k] + "-->" + i;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            
            System.out.println("��" + start + "������" + i + "�����·��Ϊ��" + path[i]);
        }
        System.out.println("=====================================");
        return shortPath;
    }
}