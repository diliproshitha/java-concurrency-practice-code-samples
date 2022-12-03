package multithreading.atomic.reference;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class LockFreeStack<T> {

    AtomicReference<StackNode<T>> head = new AtomicReference<>();
    AtomicInteger counter = new AtomicInteger(0);

    public void push(T value) {
        StackNode<T> newStackNode = new StackNode<>(value);

        while(true) {
            StackNode<T> currentHead = head.get();
            newStackNode.next = currentHead;

            if (head.compareAndSet(currentHead, newStackNode)) {
                counter.incrementAndGet();
                break;
            } else {
                LockSupport.parkNanos(1);
            }
        }
    }

    public T pop() {
        StackNode<T> currentHeadNode = head.get();
        StackNode<T> newHeadNode;

        while(currentHeadNode != null) {
            newHeadNode = currentHeadNode.next;

            if (head.compareAndSet(currentHeadNode, newHeadNode)) {
                counter.decrementAndGet();
                break;
            } else {
                LockSupport.parkNanos(1);
                currentHeadNode = head.get();
            }
        }
        return currentHeadNode != null ? currentHeadNode.value : null;
    }

    public int getCounter () {
        return counter.get();
    }

}
