    public synchronized Object remove() {

        int    ci;     // current index
        int    li;     // left index
        int    ri;     // right index
        int    chi;    // child index
        Object co;
        Object ro;

        if (count == 0) {
            return null;
        }

        ci = 0;
        ro = heap[ci];

        count--;

        if (count == 0) {
            heap[0] = null;

            return ro;
        }

        co          = heap[count];
        heap[count] = null;

        do {
            li = (ci << 1) + 1;

            if (li >= count) {
                break;
            }

            ri  = (ci << 1) + 2;
            chi = (ri >= count || oc.compare(heap[li], heap[ri]) < 0) ? li
                                                                      : ri;

            if (oc.compare(co, heap[chi]) <= 0) {
                break;
            }

            heap[ci] = heap[chi];
            ci       = chi;
        } while (true);

        heap[ci] = co;

        return ro;
    }
