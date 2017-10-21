    @Test
    public void testFlushAtEndOfBatch() throws Exception {
        final File file = new File("target", "XmlCompactFileAppenderTest.log");
        file.delete();
        final Logger log = LogManager.getLogger("com.foo.Bar");
        final String logMsg = "Message flushed with immediate flush=false";
        log.info(logMsg);
        CoreLoggerContexts.stopLoggerContext(false, file); // stop async thread

        String line1;
        try (final BufferedReader reader = new BufferedReader(new FileReader(file))) {
            line1 = reader.readLine();
        } finally {
            file.delete();
        }
        assertNotNull("line1", line1);
        final String msg1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        assertTrue("line1 incorrect: [" + line1 + "], does not contain: [" + msg1 + ']', line1.contains(msg1));

        final String msg2 = "<Events xmlns=\"http://logging.apache.org/log4j/2.0/events\">";
        assertTrue("line1 incorrect: [" + line1 + "], does not contain: [" + msg2 + ']', line1.contains(msg2));

        final String msg3 = "<Event ";
        assertTrue("line1 incorrect: [" + line1 + "], does not contain: [" + msg3 + ']', line1.contains(msg3));

        final String msg4 = logMsg;
        assertTrue("line1 incorrect: [" + line1 + "], does not contain: [" + msg4 + ']', line1.contains(msg4));

        final String location = "testFlushAtEndOfBatch";
        assertTrue("no location", !line1.contains(location));

        assertTrue(line1.indexOf('\r') == -1);
        assertTrue(line1.indexOf('\n') == -1);
    }
