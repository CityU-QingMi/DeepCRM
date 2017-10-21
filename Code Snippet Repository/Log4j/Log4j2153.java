    @Test
    public void testLog4jLogEventUsesNanoTimeClock() throws Exception {
        final File file = new File("target", "NanoTimeToFileTest.log");
        // System.out.println(f.getAbsolutePath());
        file.delete();
        final Logger log = LogManager.getLogger("com.foo.Bar");
        final long before = System.nanoTime();
        log.info("Use actual System.nanoTime()");
        assertTrue("using SystemNanoClock", Log4jLogEvent.getNanoClock() instanceof SystemNanoClock);

        final long DUMMYNANOTIME = 123;
        Log4jLogEvent.setNanoClock(new DummyNanoClock(DUMMYNANOTIME));
        log.info("Use dummy nano clock");
        assertTrue("using SystemNanoClock", Log4jLogEvent.getNanoClock() instanceof DummyNanoClock);
        
        CoreLoggerContexts.stopLoggerContext(file); // stop async thread

        String line1;
        String line2;
        try (final BufferedReader reader = new BufferedReader(new FileReader(file))) {
            line1 = reader.readLine();
            line2 = reader.readLine();
            // System.out.println(line1);
            // System.out.println(line2);
        }
        file.delete();

        assertNotNull("line1", line1);
        assertNotNull("line2", line2);
        final String[] line1Parts = line1.split(" AND ");
        assertEquals("Use actual System.nanoTime()", line1Parts[2]);
        assertEquals(line1Parts[0], line1Parts[1]);
        final long loggedNanoTime = Long.parseLong(line1Parts[0]);
        assertTrue("used system nano time", loggedNanoTime - before < TimeUnit.SECONDS.toNanos(1));
        
        final String[] line2Parts = line2.split(" AND ");
        assertEquals("Use dummy nano clock", line2Parts[2]);
        assertEquals(String.valueOf(DUMMYNANOTIME), line2Parts[0]);
        assertEquals(String.valueOf(DUMMYNANOTIME), line2Parts[1]);
    }
