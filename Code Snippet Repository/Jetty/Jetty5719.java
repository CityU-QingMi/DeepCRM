    @Test
    public void testInfoOutput()
    {
        jul.clear();

        // Common Throwable (for test)
        Throwable th = new Throwable("Message");

        // Capture raw string form
        StringWriter tout = new StringWriter();
        th.printStackTrace(new PrintWriter(tout));
        String ths = tout.toString();

        // Tests
        JavaUtilLog log = new JavaUtilLog("test.in.fo");
        setJulLevel("test.in.fo",Level.INFO);

        log.info("Simple info");
        log.info("Info with {} parameter",1);
        log.info("Info with {} {} parameters", 2, "spiffy");
        log.info("Info with throwable", th);
        log.info(th);

        // jul.dump();

        jul.assertContainsLine("INFO|test.in.fo|Simple info");
        jul.assertContainsLine("INFO|test.in.fo|Info with 1 parameter");
        jul.assertContainsLine("INFO|test.in.fo|Info with 2 spiffy parameters");
        jul.assertContainsLine("INFO|test.in.fo|Info with throwable");
        jul.assertContainsLine(ths);
    }
