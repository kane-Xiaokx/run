package algorithm.link;

/**
 * 循环队列
 */
public class LoopQueue {

    private int[] data;
    private int begin;
    private int end;
    private int size;
    private final int limit;

    public LoopQueue(int limit){
        data = new int[limit];
        this.limit = limit;
    }

    public void add(int value){
        if(size == limit){
            throw new RuntimeException("队列满了,无法加入!!");
        }
        size ++;
        data[end] = value;
        end = getNextIndex(end);
    }

    public int pop(){
        if(size == 0){
            throw new RuntimeException("队列为空,无法弹出数据！");
        }
        size --;
        int result = data[begin];
        begin = getNextIndex(begin);
        return result;
    }

    public boolean isEmpty(){
        return size == 0;
    }


    private int getNextIndex(int index){
        return index == limit - 1 ? 0 : index + 1;
    }


}
