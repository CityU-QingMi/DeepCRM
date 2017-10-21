    @Test
    public void testFlushAtEndOfBatch() throws Exception {
        final File file = new File("target", "XmlFileAppenderTest.log");
        // System.out.println(f.getAbsolutePath());
        file.delete();
        final Logger log = LogManager.getLogger("com.foo.Bar");
        final String logMsg = "Message flushed with immediate flush=false";
        log.info(logMsg);
        CoreLoggerContexts.stopLoggerContext(false, file); // stop async thread

        String line1;
        String line2;
        String line3;
        try (final BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine(); // first line is empty, so ignore it
            line1 = reader.readLine();
            line2 = reader.readLine();
            line3 = reader.readLine();
        } finally {
            file.delete();
        }
        assertNotNull("line1", line1);

        assertNotNull("line1", line1);
        final String msg1 = "<Event ";
        assertTrue("line1 incorrect: [" + line1 + "], does not contain: [" + msg1 + ']', line1.contains(msg1));

        assertNotNull("line2", line2);
        final String msg2 = logMsg;
        assertTrue("line2 incorrect: [" + line2 + "], does not contain: [" + msg2 + ']', line2.contains(msg2));

        assertNotNull("line3", line3);
        final String msg3 = "</Event>";
        assertTrue("line3 incorrect: [" + line3 + "], does not contain: [" + msg3 + ']', line3.contains(msg3));

        final String location = "testFlushAtEndOfBatch";
        assertTrue("no location", !line1.contains(location));
    }
