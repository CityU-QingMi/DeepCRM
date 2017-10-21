    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] iso8859_1CustomCastToByte() throws CharacterCodingException {
        final int length = LOGMSG.length();
        final byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            final char c = LOGMSG.charAt(i);
            result[i++] = (byte) c;
        }
        return result;
    }
