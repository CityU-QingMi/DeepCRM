    public void testWriteToMicrobenchmark() throws IOException
    {
        int capacity = 1024 * 128;
        int iterations = 100;
        int testRuns = 10;
        byte[] bytes = new byte[capacity];
        ThreadLocalRandom.current().nextBytes(bytes);
        ByteBuffer buffer = BufferUtil.allocate(capacity);
        BufferUtil.append(buffer, bytes, 0, capacity);
        long startTest = System.nanoTime();
        for (int i = 0; i < testRuns; i++)
        {
            long start = System.nanoTime();
            for (int j = 0; j < iterations; j++)
            {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                long startRun = System.nanoTime();
                BufferUtil.writeTo(buffer.asReadOnlyBuffer(), out);
                long elapsedRun = System.nanoTime() - startRun;
//                LOG.warn("run elapsed={}ms", elapsedRun / 1000);
                assertThat("Bytes in out equal bytes in buffer", Arrays.equals(bytes, out.toByteArray()), is(true));
            }
            long elapsed = System.nanoTime() - start;
            LOG.warn("elapsed={}ms average={}ms", elapsed / 1000, elapsed/iterations/1000);
        }
        LOG.warn("overall average: {}ms", (System.nanoTime() - startTest) / testRuns / iterations / 1000);
    }
