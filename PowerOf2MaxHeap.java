// The heap must satisfy the heap property.
// Every parent node in the heap must have 2^x children.
// The value of x must be a parameter of the heap’s constructor.
// The heap must implement an insert method.
// The heap must implement a pop max method.
// The heap must be implemented in Java.
// The heap must be performant.
// You must use a more descriptive variable name than x in your implementation.

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PowerOf2MaxHeap {
    private int numChildren;
    private List<Integer> heap;

    public PowerOf2MaxHeap(int power){
        this.numChildren = (int) Math.pow(2, power);
        this.heap = new ArrayList<>();
    }
    public void insert(int val){
        heap.add(val);
        int index = heap.size() - 1;
        int parentIndex = (index - 1) / numChildren;
        
        while (index > 0 && heap.get(parentIndex) < heap.get(index)){
            Collections.swap(heap, parentIndex, index);
            index = parentIndex;
            parentIndex = (index - 1) / numChildren;
        }        
    }

    public Integer popMax(){
        if (heap.isEmpty()){
            return null;
        }

        int max = heap.get(0);
        int lastElement = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()){
            heap.set(0, lastElement);
            percolateDown(0);
        }

        return max;
    }

    private void percolateDown(int index) {
        int childIndex = numChildren * index + 1;
        while (childIndex < heap.size()) {
            int maxIndex = childIndex;
            for (int i = 1; i < numChildren && i + childIndex < heap.size(); i++) {
                if (heap.get(i + childIndex) > heap.get(maxIndex)) {
                    maxIndex = i + childIndex;
                }
            }

            if (heap.get(maxIndex) > heap.get(index)) {
                Collections.swap(heap, maxIndex, index);
                index = maxIndex;
                childIndex = numChildren * index + 1;
            } else {
                break;
            }
        }
    }
}
