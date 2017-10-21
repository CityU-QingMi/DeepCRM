    @Test
    public void testStdErrThrowable()
    {
        // Common Throwable (for test)
        Throwable th = new Throwable("Message");

        // Capture raw string form
        StringWriter tout = new StringWriter();
        th.printStackTrace(new PrintWriter(tout));
        String ths = tout.toString();

        // Start test
        StdErrLog log = new StdErrLog("test",new Properties());
        StdErrCapture output = new StdErrCapture(log);

        log.warn("ex",th);
        output.assertContains(ths);

        th = new Throwable("Message with \033 escape");

        log.warn("ex",th);
        output.assertNotContains("Message with \033 escape");
        log.info(th.toString());
        output.assertNotContains("Message with \033 escape");

        log.warn("ex",th);
        output.assertContains("Message with ? escape");
        log.info(th.toString());
        output.assertContains("Message with ? escape");
    }
