    @Test
    public void testFlushAtEndOfBatch() throws Exception {
        final Logger logger = this.loggerContextRule.getLogger("com.foo.Bar");
        final String logMsg = "Message flushed with immediate flush=true";
        logger.info(logMsg);
        logger.error(logMsg, new IllegalArgumentException("badarg"));
        this.loggerContextRule.getLoggerContext().stop(); // stops async thread
        String line1;
        String line2;
        String line3;
        String line4;
        String line5;
        String line6;
        try (final BufferedReader reader = new BufferedReader(new FileReader(this.logFile))) {
            line1 = reader.readLine();
            line2 = reader.readLine();
            line3 = reader.readLine();
            line4 = reader.readLine();
            line5 = reader.readLine();
            line6 = reader.readLine();
        }
        assertNotNull("line1", line1);
        final String msg1 = "[";
        assertTrue("line1 incorrect: [" + line1 + "], does not contain: [" + msg1 + ']', line1.equals(msg1));

        assertNotNull("line2", line2);
        final String msg2 = "{";
        assertTrue("line2 incorrect: [" + line2 + "], does not contain: [" + msg2 + ']', line2.equals(msg2));

        assertNotNull("line3", line3);
        final String msg3 = "  \"timeMillis\" : ";
        assertTrue("line3 incorrect: [" + line3 + "], does not contain: [" + msg3 + ']', line3.contains(msg3));

        assertNotNull("line4", line4);
        final String msg4 = "  \"thread\" : \"main\",";
        assertTrue("line4 incorrect: [" + line4 + "], does not contain: [" + msg4 + ']', line4.contains(msg4));

        assertNotNull("line5", line5);
        final String msg5 = "  \"level\" : \"INFO\",";
        assertTrue("line5 incorrect: [" + line5 + "], does not contain: [" + msg5 + ']', line5.contains(msg5));

        assertNotNull("line6", line6);
        final String msg6 = "  \"loggerName\" : \"com.foo.Bar\",";
        assertTrue("line5 incorrect: [" + line6 + "], does not contain: [" + msg6 + ']', line6.contains(msg6));

        final String location = "testFlushAtEndOfBatch";
        assertTrue("no location", !line1.contains(location));
    }
