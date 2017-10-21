    @Test
    public void testFlushAtEndOfBatch() throws Exception {
        final File file = new File("target", "AsyncLoggerConfigAutoFlushTest.log");
        assertTrue("Deleted old file before test", !file.exists() || file.delete());
        
        final Logger log = LogManager.getLogger("com.foo.Bar");
        final String msg = "Message flushed with immediate flush=false";
        log.info(msg);
        CoreLoggerContexts.stopLoggerContext(file); // stop async thread
        final BufferedReader reader = new BufferedReader(new FileReader(file));
        final String line1 = reader.readLine();
        reader.close();
        file.delete();
        assertNotNull("line1", line1);
        assertTrue("line1 correct", line1.contains(msg));
    }
