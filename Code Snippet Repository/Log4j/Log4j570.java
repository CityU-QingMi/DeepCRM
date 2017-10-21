    private void testThreads(final Map<String, String> contextData) throws Exception {
        System.out.println("Testing multithreading");
        final int threadedCount = COUNT; // THREADED_COUNT * threadCount < COUNT ? COUNT / threadCount : THREADED_COUNT;
        final int[] threadCounts = new int[] {1, 2, 5, 10, 20, 50};
        for (final int threadCount : threadCounts) {
            System.out.println("Testing " + threadCount + " threads, "
                    + (contextData.isEmpty() ? "EMPTY context" : "NON-EMPTY context"));
            final Worker[] workers = new Worker[threadCount];
            final long[] results = new long[threadCount];
            for (int i=0; i < threadCount; ++i) {
                workers[i] = new Worker(Target.LOG4J2, threadedCount, results, i, contextData);
            }
            for (int i=0; i < threadCount; ++i) {
                workers[i].start();
            }
            long total = 0;
            for (int i=0; i < threadCount; ++i) {
                workers[i].join();
                total += results[i];
            }
            final long result3 = total / threadCount;
            total = 0;
            for (int i=0; i < threadCount; ++i) {
                workers[i] = new Worker(Target.LOGBACK, threadedCount, results, i, contextData);
            }
            for (int i=0; i < threadCount; ++i) {
                workers[i].start();
            }
            for (int i=0; i < threadCount; ++i) {
                workers[i].join();
                total += results[i];
            }
            final long result2 = total / threadCount;
            System.out.println("###############################################");
            System.out.println("Logback: " + result2);
            System.out.println("Log4j 2.0: " + result3 );
            System.out.println("###############################################");
        }
    }
