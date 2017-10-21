    @Test
    public void testLog4j2Only() throws InterruptedException {
        final org.apache.logging.log4j.Logger log4JLogger = LogManager.getLogger(this.getClass());
        final int limit = 11; // more than unrolled varargs
        final Object[] args = createArray(limit);
        final Object[] originalArgs = Arrays.copyOf(args, args.length);

        listAppender.countDownLatch = new CountDownLatch(1);
        ((ExtendedLogger)log4JLogger).logIfEnabled("test", Level.ERROR, null, "test {}", args);

        listAppender.countDownLatch.await(1, TimeUnit.SECONDS);
        Assert.assertArrayEquals(Arrays.toString(args), originalArgs, args);

        ((ExtendedLogger)log4JLogger).logIfEnabled("test", Level.ERROR, null, "test {}", args);
        Assert.assertArrayEquals(Arrays.toString(args), originalArgs, args);
    }
