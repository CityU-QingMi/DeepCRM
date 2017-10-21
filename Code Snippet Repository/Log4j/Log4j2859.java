    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public byte[] iso8859_1CustomVerifyAndCast() throws CharacterCodingException {
        final int length = LOGMSG.length();
        final byte[] result = new byte[length];
        int j = 0;
        for (int i = 0; i < length; i++) {
            final char c = LOGMSG.charAt(i);
            if (c <= 255) {
                result[j++] = (byte) c;
            } else {
                i = nonIsoChar(LOGMSG, i);
                result[j++] = (byte) '?';
            }
        }
        return result;
    }
