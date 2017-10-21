    @Test
    public void testAsyncLogWritesToLog() throws Exception {
        final File file = new File("target", "AsyncLoggerTest.log");
        // System.out.println(f.getAbsolutePath());
        file.delete();
        
        final AsyncLogger log = (AsyncLogger) LogManager.getLogger("com.foo.Bar");
        assertTrue(log.getNanoClock() instanceof DummyNanoClock);
        
        final String msg = "Async logger msg";
        log.info(msg, new InternalError("this is not a real error"));
        CoreLoggerContexts.stopLoggerContext(false, file); // stop async thread

        final BufferedReader reader = new BufferedReader(new FileReader(file));
        final String line1 = reader.readLine();
        reader.close();
        file.delete();
        assertNotNull("line1", line1);
        assertTrue("line1 correct", line1.contains(msg));

        final String location = "testAsyncLogWritesToLog";
        assertTrue("no location", !line1.contains(location));
    }
