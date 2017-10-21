    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public long serializableMCD() {
        final String str = PATTERN_M_C_D.toSerializable(EVENT);
        final byte[] data = str.getBytes(CHARSET_DEFAULT);
        ByteBuffer buff = destination.getByteBuffer();
        if (buff.remaining() < data.length) {
            buff = destination.drain(buff);
        }
        buff.put(data);
        return destination.count;
    }
