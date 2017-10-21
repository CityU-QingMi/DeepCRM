    @Test
    public void testConcurrentLogging() throws Throwable {
        final Logger log = context.getLogger(ConcurrentLoggingWithJsonLayoutTest.class);
        final Set<Thread> threads = Collections.synchronizedSet(new HashSet<Thread>());
        final List<Throwable> thrown = Collections.synchronizedList(new ArrayList<Throwable>());

        for (int x = 0; x < Runtime.getRuntime().availableProcessors() * 2; x++) {
            final Thread t = new LoggingThread(threads, log);
            threads.add(t);

            // Appender is configured with ignoreExceptions="false";
            // any exceptions are propagated to the caller, so we can catch them here.
            t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(final Thread t, final Throwable e) {
                    thrown.add(e);
                }
            });
            t.start();
        }

        while (!threads.isEmpty()) {
            log.info("not done going to sleep...");
            Thread.sleep(10);
        }

        // if any error occurred, fail this test
        if (!thrown.isEmpty()) {
            throw thrown.get(0);
        }

        // simple test to ensure content is not corrupted
        if (new File(PATH).exists()) {
            final List<String> lines = Files.readAllLines(new File(PATH).toPath(), Charset.defaultCharset());
            for (final String line : lines) {
                assertThat(line, startsWith("{\"timeMillis\":"));
                assertThat(line, endsWith("\"threadPriority\":5}"));
            }
        }
    }
