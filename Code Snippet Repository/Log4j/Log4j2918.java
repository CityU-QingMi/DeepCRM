    private static void doTestRunBoth(final ClassicLogger classic, final NoGcLogger nogc, final int repetitions, final int n) {
        startTime[n] = System.currentTimeMillis();
        final long start = System.nanoTime();
        for (int i = 0; i < repetitions; i++) {
            classic.log("Test message str={}, double={}, int={}, obj={}", "abc", i / 2.5, i, "XYX");
            nogc.log("Test message str={}, double={}, int={}, obj={}", "abc", box(i / 2.5), box(i), "XYX");

            if (classic.appender.checksum != nogc.appender.checksum) {
                throw new IllegalStateException();
            }
        }
        duration[n] = System.nanoTime() - start;
    }
