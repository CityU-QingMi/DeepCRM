    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public int latency3ParamsV3(final ThreadState state) {
        state.buffer.setLength(0);
        final String STR = "p1={}, p2={}, p3={}";
        final int length = STR.length();
        STR.getChars(0, length, state.copy, 0);
        final int count = ParameterFormatter.countArgumentPlaceholders3(state.copy, length, state.indices);
        ParameterFormatter.formatMessage3(state.buffer, state.copy, length, ARGS, count, state.indices);
        return state.buffer.length();
    }
