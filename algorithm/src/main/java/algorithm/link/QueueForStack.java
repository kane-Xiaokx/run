package algorithm.link;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列实现栈
 */
public class QueueForStack<T> {

    private Queue<T> queue;
    private Queue<T> help;

    public QueueForStack(){
        this.queue = new LinkedList<>();
        this.help = new LinkedList<>();
    }

    public void push(T value){
        queue.add(value);
    }

    public T poll(){
        if(queue.isEmpty()){
            throw new RuntimeException("栈为空!");
        }
        T result = null;
        while (!queue.isEmpty()){
            result = queue.poll();
            if(!queue.isEmpty()){
                help.offer(result);
            }
        }
        Queue<T> tmp = queue;
        queue = help;
        help = tmp;
        return result;
    }

    public T peek(){
        T ans = null;
        while (queue.size() > 1){
            help.offer(ans = queue.poll());
        }
        Queue<T> tmp = queue;
        queue = help;
        help = tmp;
        return ans;
    }



}
