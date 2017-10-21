    @Test
    public void testOffFiltering() throws Exception
    {
        StdErrLog log = new StdErrLog(StdErrLogTest.class.getName(),new Properties());
        try (StacklessLogging stackless = new StacklessLogging(log))
        {
            log.setLevel(StdErrLog.LEVEL_OFF);

            StdErrCapture output = new StdErrCapture(log);

            // Various logging events
            log.debug("Squelch");
            log.debug("Squelch", new RuntimeException("Squelch"));
            log.info("Squelch");
            log.info("Squelch", new IllegalStateException("Squelch"));
            log.warn("Squelch");
            log.warn("Squelch", new Exception("Squelch"));
            log.ignore(new Throwable("Squelch"));

            // Validate Output
            output.assertNotContains("Squelch");
        }
    }
