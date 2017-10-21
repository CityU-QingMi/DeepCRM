    static long calcNanoTimeCost() {
        final long iterations = 10000000;
        final long start = System.nanoTime();
        long finish = start;

        for (int i = 0; i < iterations; i++) {
            finish = System.nanoTime();
        }

        if (finish <= start) {
            throw new IllegalStateException();
        }

        finish = System.nanoTime();
        return (finish - start) / iterations;
    }
