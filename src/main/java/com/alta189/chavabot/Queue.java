package com.alta189.chavabot;

import java.util.Vector;

/**
 * @author: alta189
 */
public class Queue {
	Vector queue = new Vector();

	public void add(Object obj) {
		synchronized(queue) {
			queue.add(obj);
			queue.notify();
		}
	}

	public void addFront(Object obj) {
		synchronized(queue) {
			queue.add(0, obj);
			queue.notify();
		}
	}

	public Object next() {
		Object o = null;
        synchronized(queue) {
            if (queue.size() == 0) {
                try {
                    queue.wait();
                }
                catch (InterruptedException e) {
                    return null;
                }
            }

            // Return the Object.
            try {
                o = queue.firstElement();
                queue.removeElementAt(0);
            }
            catch (ArrayIndexOutOfBoundsException e) {
                throw new InternalError("Array Issue");
            }
        }
		return o;
	}

	public void clear() {
		synchronized(queue) {
			queue.removeAllElements();
		}
	}

	public int size() {
		return queue.size();
	}


}
