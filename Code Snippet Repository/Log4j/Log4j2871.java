    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public String synchronizedSimpleDateFmt() {
        final long timestamp = System.currentTimeMillis();
        synchronized (simpleDateFormat) {
            if (timestamp != currentTimestamp) {
                cachedTime = simpleDateFormat.format(date);
            }
            return cachedTime;
        }
    }
