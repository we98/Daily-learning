import java.util.Stack;

public class Solution {

    //挑战用一个栈实现，每次push存进去两个值，先存当前节点，再存当前栈中最小值，因此最小值在栈顶
    Stack<Integer> data = new Stack<>();
    
    public void push(int node) {
        if(data.isEmpty()){
            data.push(node);
            data.push(node);
        }
        else{
            int currentMin = data.peek();
            data.push(node);
            if(node < currentMin){
                data.push(node);
            }
            else{
                data.push(currentMin);
            }
        }
    }
    
    public void pop() {
        if(data.isEmpty()){
            return;
        }
        data.pop();
        data.pop();
    }
    
    public int top() {
        if(data.isEmpty()){
            throw new RuntimeException();
        }
        int temp = data.pop();
        int result = data.peek();
        data.push(temp);
        return result;
    }
    
    public int min() {
        if(data.isEmpty()){
            throw new RuntimeException();
        }
        return data.peek();
    }
}