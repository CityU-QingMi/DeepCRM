    public HsqlArrayHeap(int capacity,
                         Comparator comparator)
                         throws IllegalArgumentException {

        if (capacity <= 0) {
            throw new IllegalArgumentException("" + capacity);
        }

        if (comparator == null) {
            throw new IllegalArgumentException("null comparator");
        }

        heap = new Object[capacity];
        oc   = comparator;
    }
