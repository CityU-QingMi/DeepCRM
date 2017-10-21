    @Test
    public void testDebugFiltering() throws Exception
    {
        StdErrLog log = new StdErrLog(StdErrLogTest.class.getName(),new Properties());
        try(StacklessLogging stackless = new StacklessLogging(log))
        {
            StdErrCapture output = new StdErrCapture(log);

            // Normal/Default behavior
            log.debug("Tobacconist");
            log.debug("<spoken line>", new Throwable("on editing room floor"));

            // Level Debug
            log.setLevel(StdErrLog.LEVEL_DEBUG);
            log.debug("my hovercraft is");

            log.debug("<zoom>", new Throwable("out of focus"));
            log.debug(new Throwable("scene lost"));

            // Level All
            log.setLevel(StdErrLog.LEVEL_ALL);
            log.debug("full of eels.");

            // Level Warn
            log.setLevel(StdErrLog.LEVEL_WARN);
            log.debug("what?");

            // Validate Output
            // System.err.print(output);
            output.assertNotContains("Tobacconist");
            output.assertContains("my hovercraft is");
            output.assertContains("full of eels.");
            output.assertNotContains("what?");

            // Validate Stack Traces
            output.assertNotContains("<spoken line>");
            output.assertNotContains("on editing room floor");

            output.assertContains(".StdErrLogTest:tname: <zoom>");
            output.assertContains("java.lang.Throwable: out of focus");
            output.assertContains("java.lang.Throwable: scene lost");
        }
    }
