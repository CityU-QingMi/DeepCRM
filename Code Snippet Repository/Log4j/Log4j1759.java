    @Test
    public void testFlushAtEndOfBatch() throws Exception {
        final Logger logger = this.loggerContextRule.getLogger("com.foo.Bar");
        final String logMsg = "Message flushed with immediate flush=false";
        logger.info(logMsg);
        CoreLoggerContexts.stopLoggerContext(false, logFile); // stop async thread

        String line1;
        String line2;
        String line3;
        String line4;
        try (final BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            line1 = reader.readLine();
            line2 = reader.readLine();
            reader.readLine(); // ignore the empty line after the <Events> root
            line3 = reader.readLine();
            line4 = reader.readLine();
        } finally {
            logFile.delete();
        }
        assertNotNull("line1", line1);
        final String msg1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        assertTrue("line1 incorrect: [" + line1 + "], does not contain: [" + msg1 + ']', line1.equals(msg1));

        assertNotNull("line2", line2);
        final String msg2 = "<Events xmlns=\"http://logging.apache.org/log4j/2.0/events\">";
        assertTrue("line2 incorrect: [" + line2 + "], does not contain: [" + msg2 + ']', line2.equals(msg2));

        assertNotNull("line3", line3);
        final String msg3 = "<Event ";
        assertTrue("line3 incorrect: [" + line3 + "], does not contain: [" + msg3 + ']', line3.contains(msg3));

        assertNotNull("line4", line4);
        final String msg4 = logMsg;
        assertTrue("line4 incorrect: [" + line4 + "], does not contain: [" + msg4 + ']', line4.contains(msg4));

        final String location = "testFlushAtEndOfBatch";
        assertTrue("no location", !line1.contains(location));
    }
