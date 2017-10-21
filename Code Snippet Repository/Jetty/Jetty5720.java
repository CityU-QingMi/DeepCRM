    @Test
    public void testWarnOutput()
    {
        jul.clear();

        // Common Throwable (for test)
        Throwable th = new Throwable("Message");

        // Capture raw string form
        StringWriter tout = new StringWriter();
        th.printStackTrace(new PrintWriter(tout));
        String ths = tout.toString();

        // Tests
        JavaUtilLog log = new JavaUtilLog("test.wa.rn");
        setJulLevel("test.wa.rn",Level.WARNING);

        log.warn("Simple warn");
        log.warn("Warn with {} parameter",1);
        log.warn("Warn with {} {} parameters", 2, "spiffy");
        log.warn("Warn with throwable", th);
        log.warn(th);

        // jul.dump();

        jul.assertContainsLine("WARNING|test.wa.rn|Simple warn");
        jul.assertContainsLine("WARNING|test.wa.rn|Warn with 1 parameter");
        jul.assertContainsLine("WARNING|test.wa.rn|Warn with 2 spiffy parameters");
        jul.assertContainsLine("WARNING|test.wa.rn|Warn with throwable");
        jul.assertContainsLine(ths);
    }
