    @Test
    public void testThreads() throws Exception {
        final Thread[] threads = new Thread[THREADS];
        final UUID[] uuids = new UUID[COUNT * THREADS];
        final long[] elapsed = new long[THREADS];
        for (int i=0; i < THREADS; ++i) {
            threads[i] = new Worker(uuids, elapsed, i, COUNT);
        }
        for (int i=0; i < THREADS; ++i) {
            threads[i].start();
        }
        long elapsedTime = 0;
        for (int i=0; i < THREADS; ++i) {
            threads[i].join();
            elapsedTime += elapsed[i];
        }
        System.out.println("Elapsed for " + COUNT * THREADS + " UUIDS = " + elapsedTime + " Average = " +
                elapsedTime / (COUNT * THREADS) + " ns");
        int errors = 0;
        for (int i=0; i < COUNT * THREADS; ++i) {
            for (int j=i+1; j < COUNT * THREADS; ++j) {
                if (uuids[i].equals(uuids[j])) {
                    ++errors;
                    System.out.println("UUID " + i + " equals UUID " + j);
                }
            }
        }
        assertEquals(errors + " duplicate UUIDS", 0, errors);
    }
