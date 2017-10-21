    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public String atomicFastFormat() {
        final long timestamp = System.currentTimeMillis();
        final CachedTimeFastFormat current = cachedTimeFastFmt.get();
        if (timestamp != current.timestamp) {
            final CachedTimeFastFormat newTime = new CachedTimeFastFormat(timestamp);
            if (cachedTimeFastFmt.compareAndSet(current, newTime)) {
                return newTime.formatted;
            }
            return cachedTimeFastFmt.get().formatted;

        }
        return current.formatted;
    }
