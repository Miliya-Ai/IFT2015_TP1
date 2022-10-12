public class myQueue {
    public void enqueue(T e) {
        if (n == queue.length) throw new OverflowException();
        int end = (start + n++) % queue.length; // Circular queue
        queue[end] = e;
    }

    public T dequeue() {
        if (isEmpty()) throw new UnderflowException();
        T e = queue(start);
        queue[start] = null;
        start = (start + 1) % queue.length; // Circular queue
        n--;
        return e;
    }
}
