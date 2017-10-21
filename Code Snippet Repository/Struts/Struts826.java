    public long getNext() {
        long next = current;
        current += interval;

        if (wrap && (current > last)) {
            current -= ((1 + last) - first);
        }

        return next;
    }
