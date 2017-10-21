    @Test
    public void testDebugOutput()
    {
        jul.clear();

        // Common Throwable (for test)
        Throwable th = new Throwable("Message");

        // Capture raw string form
        StringWriter tout = new StringWriter();
        th.printStackTrace(new PrintWriter(tout));
        String ths = tout.toString();

        // Tests
        JavaUtilLog log = new JavaUtilLog("test.de.bug");
        setJulLevel("test.de.bug",Level.FINE);

        log.debug("Simple debug");
        log.debug("Debug with {} parameter",1);
        log.debug("Debug with {} {} parameters", 2, "spiffy");
        log.debug("Debug with throwable", th);
        log.debug(th);

        // jul.dump();

        jul.assertContainsLine("FINE|test.de.bug|Simple debug");
        jul.assertContainsLine("FINE|test.de.bug|Debug with 1 parameter");
        jul.assertContainsLine("FINE|test.de.bug|Debug with 2 spiffy parameters");
        jul.assertContainsLine("FINE|test.de.bug|Debug with throwable");
        jul.assertContainsLine(ths);
    }
