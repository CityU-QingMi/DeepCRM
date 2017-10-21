    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] iso8859_1CustomPortedJDK8CopyArray() throws CharacterCodingException {
        final char[] charArray = LOGMSG.toCharArray();
        final int length = charArray.length;
        final byte[] result = new byte[length];
        encode0(charArray, 0, length, result);
        return result;
    }
