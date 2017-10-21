    public synchronized void add(final T item) {
        if (ring.length > 0) {
            ring[last] = item;
            if (++last == ring.length) {
                last = 0;
            }

            if (numElems < ring.length) {
                numElems++;
            } else if (++first == ring.length) {
                first = 0;
            }
        }
    }
