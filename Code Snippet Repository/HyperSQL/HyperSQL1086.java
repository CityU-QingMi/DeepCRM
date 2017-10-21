    public synchronized void add(Object o)
    throws IllegalArgumentException, RuntimeException {

        int ci;    // current index
        int pi;    // parent index

        if (o == null) {
            throw new IllegalArgumentException("null element");
        }

        if (isFull()) {
            throw new RuntimeException("full");
        }

        if (count >= heap.length) {
            increaseCapacity();
        }

        ci = count;

        count++;

        do {
            if (ci <= 0) {
                break;
            }

            pi = (ci - 1) >>> 1;

            try {
                if (oc.compare(o, heap[pi]) >= 0) {
                    break;
                }
            } catch (Exception e) {
                throw new IllegalArgumentException(e.toString());
            }

            heap[ci] = heap[pi];
            ci       = pi;
        } while (true);

        heap[ci] = o;
    }
