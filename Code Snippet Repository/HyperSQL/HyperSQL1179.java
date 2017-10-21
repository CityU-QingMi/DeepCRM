    public synchronized long getTotalValues() {

        long total = 0;

        for (int i = 0; i < count; i++) {
            total += keys[i];
        }

        return total;
    }
