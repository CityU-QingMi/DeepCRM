    @Test
    public void testIgnores() throws Exception
    {
        StdErrLog log = new StdErrLog(StdErrLogTest.class.getName(),new Properties());
        try (StacklessLogging stackless = new StacklessLogging(log))
        {
            StdErrCapture output = new StdErrCapture(log);

            // Normal/Default behavior
            log.ignore(new Throwable("IGNORE ME"));

            // Show Ignored
            log.setLevel(StdErrLog.LEVEL_ALL);
            log.ignore(new Throwable("Don't ignore me"));

            // Set to Debug level
            log.setLevel(StdErrLog.LEVEL_DEBUG);
            log.ignore(new Throwable("Debug me"));

            // Validate Output
            // System.err.print(output);
            output.assertNotContains("IGNORE ME");
            output.assertContains("Don't ignore me");
            output.assertNotContains("Debug me");
        }
    }
