    @Test
    public void testWarnFiltering() throws UnsupportedEncodingException
    {
        StdErrLog log = new StdErrLog(StdErrLogTest.class.getName(),new Properties());
        try (StacklessLogging stackless = new StacklessLogging(log))
        {
            StdErrCapture output = new StdErrCapture(log);

            // Start with default level
            log.warn("See Me");

            // Set to debug level
            log.setLevel(StdErrLog.LEVEL_DEBUG);
            log.warn("Hear Me");

            // Set to warn level
            log.setLevel(StdErrLog.LEVEL_WARN);
            log.warn("Cheer Me");

            log.warn("<zoom>", new Throwable("out of focus"));
            log.warn(new Throwable("scene lost"));

            // Validate Output
            // System.err.print(output);
            output.assertContains("See Me");
            output.assertContains("Hear Me");
            output.assertContains("Cheer Me");

            // Validate Stack Traces
            output.assertContains(".StdErrLogTest:tname: <zoom>");
            output.assertContains("java.lang.Throwable: out of focus");
            output.assertContains("java.lang.Throwable: scene lost");
        }
    }
