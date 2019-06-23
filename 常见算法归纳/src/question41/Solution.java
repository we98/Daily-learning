package question41;

/**
 * 
 * @author CGWEI
 *
 */
public class Solution{
	public static void main(String[] args) {
		System.out.println(backspaceCompare("e##e#o##oyof##q", "e##e#fq##o##oyof##q"));
	}
	
	/**
	 * 做到了空间复杂度O(1)，也可以使用栈，比较简单
	 * @param S
	 * @param T
	 * @return
	 */
	public static boolean backspaceCompare(String S, String T) {
        int SDeletedKeyCount = 0;
        int TDeletedKeyCount = 0;
        int SIndex = S.length() - 1;
        int TIndex = T.length() - 1;
        while(SIndex >= 0 && TIndex >= 0) {
        	char currentSChar = S.charAt(SIndex);
        	char currentTChar = T.charAt(TIndex);
        	if(currentSChar == '#' || currentTChar == '#' || SDeletedKeyCount != 0 || TDeletedKeyCount != 0) {
        		if(currentSChar == '#') {
        			SDeletedKeyCount++;
            		SIndex--;
        		}
        		else {
        			if(SDeletedKeyCount > 0) {
        				SIndex--;
        				SDeletedKeyCount--;
        			}
        		}
        		if(currentTChar == '#') {
        			TDeletedKeyCount++;
        			TIndex--;
        		}
        		else {
        			if(TDeletedKeyCount > 0) {
        				TIndex--;
        				TDeletedKeyCount--;
        			}
        		}
        	}
        	else {
        		if(currentSChar != currentTChar) {
        			return false;
        		}
        		TIndex--;
        		SIndex--;
        	}
        }
        while(SIndex >= 0) {
        	if(S.charAt(SIndex) == '#') {
        		SDeletedKeyCount++;
        	}
        	else {
        		if(SDeletedKeyCount == 0) {
        			return false;
        		}
        		SDeletedKeyCount--;
        	}
        	SIndex--;
        }
        while(TIndex >= 0) {
        	if(T.charAt(TIndex) == '#') {
        		TDeletedKeyCount++;
        	}
        	else {
        		if(TDeletedKeyCount == 0) {
        			return false;
        		}
        		TDeletedKeyCount--;
        	}
        	TIndex--;
        }
        return true;
    }
}
