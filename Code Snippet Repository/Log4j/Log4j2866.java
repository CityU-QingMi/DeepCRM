    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public long byteArrayMCD() {
        final byte[] data =  PATTERN_M_C_D.toByteArray(EVENT);
        ByteBuffer buff = destination.getByteBuffer();
        if (buff.remaining() < data.length) {
            buff = destination.drain(buff);
        }
        buff.put(data);
        return destination.count;
    }
