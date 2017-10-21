    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public long granularity_nanotime() {
        long cur;
        do {
            cur = System.nanoTime();
        } while (cur == lastValue);
        lastValue = cur;
        return cur;
    }
