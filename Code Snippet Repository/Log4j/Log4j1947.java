    @Test
    public void testAsyncLogWritesToLog() throws Exception {
        final File file = new File("target", "AsyncLoggerLocationTest.log");
        // System.out.println(f.getAbsolutePath());
        final Logger log = LogManager.getLogger("com.foo.Bar");
        final String msg = "Async logger msg with location";
        log.info(msg);
        CoreLoggerContexts.stopLoggerContext(false, file); // stop async thread

        final BufferedReader reader = new BufferedReader(new FileReader(file));
        final String line1 = reader.readLine();
        reader.close();
        file.delete();
        assertNotNull("line1", line1);
        assertTrue("line1 correct", line1.contains(msg));

        final String location = "testAsyncLogWritesToLog";
        assertTrue("has location", line1.contains(location));
    }
