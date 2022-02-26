package algorithm.link;

import java.util.Stack;

/**
 * 用栈实现队列
 */
public class StackForQueue<T> {

    private Stack<T> push;
    private Stack<T> pop;

    public StackForQueue(){
        push = new Stack<>();
        pop = new Stack<>();
    }

    public void pushToPop(){
        if(pop.empty()){
            while (!push.empty()){
                pop.push(push.pop());
            }
        }
    }

    public void add(T t){
        push.push(t);
        pushToPop();
    }

    public T poll(){
        if(push.empty() && pop.empty()){
            throw new RuntimeException("队列为空!");
        }
        pushToPop();
        return pop.pop();
    }

    public T peek(){
        if(push.empty() && pop.empty()){
            throw new RuntimeException("队列为空!");
        }
        pushToPop();
        return pop.peek();
    }

}
