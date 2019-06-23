package question2;

/**
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * 
 * 这里使用了StringBuffer内置的replace函数，但是可以遍历str空格个数之后重新确定建一个新的StringBuffer对象
 * 通过新对象的append方法逐一添加字符
 * 
 * @author CGWEI
 *
 */
public class Solution {
	public String replaceSpace(StringBuffer str) {
    	for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ' '){
                str.replace(i, i+1, "%20");
            }
        }
        return str.toString();
    }
}