    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public String atomicFixedFormat() {
        final long timestamp = System.currentTimeMillis();
        final CachedTimeFixedFmt current = cachedTimeFixedFmt.get();
        if (timestamp != current.timestamp) {
            final CachedTimeFixedFmt newTime = new CachedTimeFixedFmt(timestamp);
            if (cachedTimeFixedFmt.compareAndSet(current, newTime)) {
                return newTime.formatted;
            }
            return cachedTimeFixedFmt.get().formatted;

        }
        return current.formatted;
    }
