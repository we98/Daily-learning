public class Solution {
    public void reOrderArray(int [] array) {
        if(array == null || array.length <= 1){
            return;
        }
        //插入排序的思想
        int maxIndex = array.length - 1;
        for(int i = 1; i <= maxIndex; ++i){
            for(int j = i; j > 0; --j){
                if(array[j] % 2 != 0 && array[j - 1] % 2 == 0){
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
        //本想用归并排序来着，但不通过。。
        //mergeSort(array, 0, array.length - 1);
    }
    
    private void mergeSort(int[] array, int start, int end){
        if(end == start){
            return;
        }
        if(end - start == 1){
            if(array[end] % 2 == 1 && array[start] % 2 == 0){
                int temp = array[start];
                array[start] = array[end];
                array[end] = temp;
            }
            return;
        }
        int middle = (start + end) >> 1;
        mergeSort(array, start, middle);
        mergeSort(array, middle + 1, end);
        int[] tempArray = new int[end - start + 1];
        int startCopy = start;
        int middleCopy = middle + 1;
        int tempArrayIndex = 0;
        while(startCopy <= middle && middleCopy <= end){
            if(array[startCopy] % 2 == 1){
                tempArray[tempArrayIndex++] = array[startCopy++];
            }
            else if(array[middleCopy] % 2 == 1){
                tempArray[tempArrayIndex++] = array[middleCopy++];
            }
            else{
                tempArray[tempArrayIndex++] = array[startCopy++];
            }
        }
        while(startCopy <= middle){
            tempArray[tempArrayIndex++] = array[startCopy++];
        }
        while(middleCopy <= end){
            tempArray[tempArrayIndex++] = array[middleCopy++];
        }
        System.arraycopy(tempArray, 0, array, start, tempArray.length);
    }
}