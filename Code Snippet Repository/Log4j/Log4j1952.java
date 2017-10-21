    @Test
    public void testAsyncLogWritesToLog() throws Exception {
        final File file = new File("target", "AsyncLoggerTest.log");
        // System.out.println(f.getAbsolutePath());
        file.delete();
        
        ThreadContext.push("stackvalue");
        ThreadContext.put("KEY", "mapvalue");
        
        final Logger log = LogManager.getLogger("com.foo.Bar");
        final String msg = "Async logger msg";
        log.info(msg, new InternalError("this is not a real error"));
        CoreLoggerContexts.stopLoggerContext(false, file); // stop async thread

        final BufferedReader reader = new BufferedReader(new FileReader(file));
        final String line1 = reader.readLine();
        reader.close();
        file.delete();
        assertNotNull("line1", line1);
        assertTrue("line1 correct", line1.contains(msg));

        assertTrue("ThreadContext.map", line1.contains("mapvalue"));
        assertTrue("ThreadContext.stack", line1.contains("stackvalue"));
    }
