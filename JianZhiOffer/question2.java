public class Solution {
    public String replaceSpace(StringBuffer str) {
        int originLength = str.length();
        int spaceCount = 0;
        for(int i = 0; i < originLength; ++i){
            if(str.charAt(i) == ' '){
                ++spaceCount;
            }
        }
        StringBuilder result = new StringBuilder(originLength + (spaceCount << 1));
        for(int i = 0; i < originLength; ++i){
            char currentChar = str.charAt(i);
            if(currentChar != ' '){
                result.append(currentChar);
            }
            else{
                result.append("%20");
            }
        }
        return result.toString();
    }
}