    private void increaseCapacity() {

        Object[] oldheap;

        // no handling of boundary conditions.
        // In the highly unlikely event of a rollover,
        // in theory, an exception will be thrown (negative array index in
        // array allocation?)
        oldheap = heap;

        // as per java collections, v.s. JDK 1.1 java util.
        heap = new Object[3 * heap.length / 2 + 1];

        System.arraycopy(oldheap, 0, heap, 0, count);
    }
