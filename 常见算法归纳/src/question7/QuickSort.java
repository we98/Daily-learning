package algorithm;

public class QuickSort {

    //双路快排 传统的方式
    public static void quickSort2(int[] array){
        quickSort2(array, 0, array.length - 1);
    }
    private static void quickSort2(int[] array, int left, int right){
        if(left >= right){
            return;
        }
        int key = array[left];
        int i = left;
        int j = right;
        while (i < j){
            while (array[j] >= key && i < j){
                --j;
            }
            while (array[i] <= key && i < j){
                ++i;
            }
            swap(array, i, j);
        }
        array[left] = array[i];
        array[i] = key;
        quickSort2(array, left, i - 1);
        quickSort2(array, i + 1, right);
    }


    //三路快排
    public static void quickSort3(int[] array){
        quickSort3(array, 0, array.length - 1);
    }
    private static void quickSort3(int[] array, int left, int right){
        if(left >= right){
            return;
        }
        int key = array[left];
        int smaller = left - 1;
        int larger = right + 1;
        int currentIndex = left;
        while (currentIndex < larger){
            if(array[currentIndex] == key){
                ++currentIndex;
            }
            else if(array[currentIndex] < key){
                swap(array, ++smaller, currentIndex++);
            }
            else{
                swap(array, --larger, currentIndex);
            }
        }
        quickSort3(array, left, smaller);
        quickSort3(array, larger, right);
    }

    private static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
