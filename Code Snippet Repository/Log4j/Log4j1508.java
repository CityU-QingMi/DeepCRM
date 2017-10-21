    @Override
    public long currentTimeMillis() {

        // The count field is not volatile on purpose to reduce contention on this field.
        // This means that some threads may not see the increments made to this field
        // by other threads. This is not a problem: the timestamp does not need to be
        // updated exactly every 1000 calls.
        if (++count > UPDATE_THRESHOLD) {
            millis = System.currentTimeMillis(); // update volatile field: store-store barrier
            count = 0; // after a memory barrier: this change _is_ visible to other threads
        }
        return millis;
    }
