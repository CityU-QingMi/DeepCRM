    @Test
    public void testAsyncLogUsesCurrentThreadName() throws Exception {
        final File file = new File("target", "AsyncLoggerTest.log");
        // System.out.println(f.getAbsolutePath());
        file.delete();
        final Logger log = LogManager.getLogger("com.foo.Bar");
        final String msg = "Async logger msg";
        log.info(msg);
        Thread.currentThread().setName("MODIFIED-THREADNAME");
        log.info(msg);
        CoreLoggerContexts.stopLoggerContext(file); // stop async thread

        final BufferedReader reader = new BufferedReader(new FileReader(file));
        final String line1 = reader.readLine();
        final String line2 = reader.readLine();
        // System.out.println(line1);
        // System.out.println(line2);
        reader.close();
        file.delete();
        assertNotNull("line1", line1);
        assertNotNull("line2", line2);
        assertTrue("line1", line1.endsWith(" INFO c.f.Bar [main]   Async logger msg "));
        assertTrue("line2", line2.endsWith(" INFO c.f.Bar [MODIFIED-THREADNAME]   Async logger msg "));
    }
