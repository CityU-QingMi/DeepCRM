    @Test
    public void testEncode_ALotWithoutErrors() throws Exception {
        final StringBuilderEncoder helper = new StringBuilderEncoder(Charset.defaultCharset());
        final StringBuilder text = new StringBuilder("2016-04-13 21:07:47,487 DEBUG [org.apache.logging.log4j.perf.jmh.FileAppenderBenchmark.log4j2ParameterizedString-jmh-worker-1] FileAppenderBenchmark  - This is a debug [2383178] message\r\n");
        final int DESTINATION_SIZE = 1024 * 1024;
        final SpyByteBufferDestination destination = new SpyByteBufferDestination(256 * 1024, DESTINATION_SIZE);

        final int max = DESTINATION_SIZE / text.length();
        for (int i = 0; i < max; i++) {
            helper.encode(text, destination);
        }
        // no error
    }
