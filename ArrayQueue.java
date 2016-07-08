package edu.wit.comp2071.irwinc.adt3;

/**
 A class that implements the ADT queue by using an expandable
 circular array with one unused location.

 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 4.0
 */
public final class ArrayQueue<T> implements QueueInterface<T>
{
    private T[] queue; // Circular array of queue entries and one unused location
    private int frontIndex;
    private int backIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ArrayQueue()
    {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    public ArrayQueue(int initialCapacity)
    {
        checkCapacity(initialCapacity);

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[]) new Object[initialCapacity + 1];
        queue = tempQueue;
        frontIndex = 0;
        backIndex = initialCapacity;
        initialized = true;
    } // end constructor

    public void enqueue(T newEntry)
    {
        checkInitialization();
        ensureCapacity();
        backIndex = (backIndex + 1) % queue.length;
        queue[backIndex] = newEntry;
    } // end enqueue
// Version 4.0

    public T dequeue()
    {
        checkInitialization();
        if (isEmpty())
            throw new EmptyQueueException();
        else
        {
            T front = queue[frontIndex];
            queue[frontIndex] = null;
            frontIndex = (frontIndex + 1) % queue.length;
            return front;
        } // end if
    } // end dequeue
// Version 4.0

    public T getFront()
    {
        checkInitialization();
        if (isEmpty())
            throw new EmptyQueueException();
        else
            return queue[frontIndex];
    } // end getFront
// Version 4.0

    // Doubles the size of the array queue if it is full
// Precondition: checkInitialization has been called.
    private void ensureCapacity()
    {
        if (frontIndex == ((backIndex + 2) % queue.length)) // if array is full,
        {                                                   // double size of array
            T[] oldQueue = queue;
            int oldSize = oldQueue.length;
            int newSize = 2 * oldSize;
            checkCapacity(newSize);

            // The cast is safe because the new array contains null entries
            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[]) new Object[2 * oldSize];
            queue = tempQueue;
            for (int index = 0; index < oldSize - 1; index++)
            {
                queue[index] = oldQueue[frontIndex];
                frontIndex = (frontIndex + 1) % oldSize;
            } // end for

            frontIndex = 0;
            backIndex = oldSize - 2;
        } // end if
    } // end ensureCapacity
// Version 4.0

    public boolean isEmpty()
    {
        return frontIndex == ((backIndex + 1) % queue.length);
    } // end isEmpty
// Version 4.0

    private void checkInitialization()
    {
        if (!initialized)
        {
            throw new SecurityException("Object is not initialized properly");
        }
    }

    private void checkCapacity(int initialCapacity)
    {
        if (initialCapacity > MAX_CAPACITY || initialCapacity <= 0)
        {
            throw new SecurityException("Object cannot be initialized with a size less than 1 or greater than 10000");
        }
    }

    public void clear()
    {

    }

//  < Implementations of the queue operations go here. >
//  . . .

} // end ArrayQueue

