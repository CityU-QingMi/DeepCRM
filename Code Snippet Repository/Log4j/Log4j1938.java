    @Test
    public void testAsyncLogWritesToLog() throws Exception {
        final File[] files = new File[] {
                new File("target", "AsyncLoggerTest.log"), //
                new File("target", "SynchronousContextTest.log"), //
                new File("target", "AsyncLoggerAndAsyncAppenderTest.log"), //
                new File("target", "AsyncAppenderContextTest.log"), //
        };
        for (final File f : files) {
            f.delete();
        }

        ThreadContext.push("stackvalue");
        ThreadContext.put("KEY", "mapvalue");

        final Logger log = LogManager.getLogger("com.foo.Bar");
        final LoggerContext loggerContext = LogManager.getContext(false);
        final String loggerContextName = loggerContext.getClass().getSimpleName();
        RingBufferAdmin ring;
        if (loggerContext instanceof AsyncLoggerContext) {
            ring = ((AsyncLoggerContext) loggerContext).createRingBufferAdmin();
        } else {
            ring = ((AsyncLoggerConfig) ((org.apache.logging.log4j.core.Logger) log).get()).createRingBufferAdmin("");
        }

        for (int i = 0; i < LINE_COUNT; i++) {
            while (i >= 128 && ring.getRemainingCapacity() == 0) { // buffer may be full
                Thread.sleep(1);
            }
            if ((i & 1) == 1) {
                ThreadContext.put("count", String.valueOf(i));
            } else {
                ThreadContext.remove("count");
            }
            log.info("{} {} {} i={}", contextImpl, contextMap(), loggerContextName, Unbox.box(i));
        }
        ThreadContext.pop();
        CoreLoggerContexts.stopLoggerContext(false, files[0]); // stop async thread

        checkResult(files[0], loggerContextName);
        if (asyncMode == Mode.MIXED || asyncMode == Mode.BOTH_ALL_ASYNC_AND_MIXED) {
            for (int i = 1; i < files.length; i++) {
                checkResult(files[i], loggerContextName);
            }
        }
        LogManager.shutdown();
    }
