    @Test
    public void testInfoFiltering() throws Exception
    {
        StdErrLog log = new StdErrLog(StdErrLogTest.class.getName(),new Properties());
        try (StacklessLogging stackless = new StacklessLogging(log))
        {
            StdErrCapture output = new StdErrCapture(log);

            // Normal/Default behavior
            log.info("I will not buy");

            // Level Debug
            log.setLevel(StdErrLog.LEVEL_DEBUG);
            log.info("this record");

            // Level All
            log.setLevel(StdErrLog.LEVEL_ALL);
            log.info("it is scratched.");

            log.info("<zoom>", new Throwable("out of focus"));
            log.info(new Throwable("scene lost"));

            // Level Warn
            log.setLevel(StdErrLog.LEVEL_WARN);
            log.info("sorry?");
            log.info("<spoken line>", new Throwable("on editing room floor"));

            // Validate Output
            output.assertContains("I will not buy");
            output.assertContains("this record");
            output.assertContains("it is scratched.");
            output.assertNotContains("sorry?");

            // Validate Stack Traces
            output.assertNotContains("<spoken line>");
            output.assertNotContains("on editing room floor");

            output.assertContains(".StdErrLogTest:tname: <zoom>");
            output.assertContains("java.lang.Throwable: out of focus");
            output.assertContains("java.lang.Throwable: scene lost");
        }
    }
