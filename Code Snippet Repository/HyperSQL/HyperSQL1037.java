    public long getTotalValues() {

        long total = 0;

        for (int i = 0; i < count; i++) {
            total += values[i];
        }

        return total;
    }
