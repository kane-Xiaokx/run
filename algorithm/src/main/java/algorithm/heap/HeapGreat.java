package algorithm.heap;

import java.util.*;

/**
 * 加强堆
 */
public class HeapGreat<T> {

    private List<T> data; //数据
    private Map<T,Integer> map; //数据映射,表示堆在数据中的位置
    private Comparator<T> comparator; //比较器
    private int heapSize; //对大小

    public HeapGreat(Comparator<T> comparator){
        this.comparator = comparator;
        data = new ArrayList<>();
        map = new HashMap<>();
    }

    public boolean isEmpty(){
        return heapSize == 0;
    }

    public void push(T t){
        if(t == null){
            throw new NullPointerException("不允许添加空指!!");
        }
        data.add(t);
        map.put(t,heapSize);
        heapInsert(heapSize ++);
    }

    public T peek(){
        return data.get(0);
    }

    public boolean contain(T t){
        return map.containsKey(t);
    }


    public T poll(){
        T result = data.get(0);
        swap(0,heapSize - 1);
        map.remove(result);
        data.remove(--heapSize);
        heapify(0);
        return result;
    }

    public void remove(T t){
        if(t == null || !map.containsKey(t)){
            return;
        }
        T replace = data.get(heapSize - 1);
        Integer index = map.get(t);
        map.remove(t);
        data.remove(replace);
        if(replace != t){ //说明是最后一个
            data.set(index,replace);
            map.put(replace,index);
            resign(replace);
        }


    }

    public void resign(T t){
        heapInsert(map.get(t));
        heapify(map.get(t));
    }

    private void heapInsert(int index){
        while (comparator.compare(data.get(index),data.get((index - 1) / 2)) < 0){
            swap(index,(index - 1) / 2);
            index = (index - 1) / 2;
        }
    }


    private void heapify(int index){
        int left = index * 2 + 1;
        while (left < heapSize){
            int best = left + 1 < heapSize && comparator.compare(data.get(left),data.get(left + 1)) > 0
                    ?
                    left + 1 : left;
            best = comparator.compare(data.get(best), data.get(index)) < 0 ? best : index;
            if(best == index){
                break;
            }
            swap(best,index);
            index = best;
            left = best * 2 + 1;
        }


    }


    private void swap(int i,int j){
        T o1 = data.get(i);
        T o2 = data.get(j);
        data.set(i,o2);
        data.set(j,o1);
        map.put(o1,j);
        map.put(o2,i);

    }




}
