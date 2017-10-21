    @Test
    public void testAsyncLogWritesToLog() throws Exception {

        final File file = new File("target", "AsyncLoggerTimestampMessageTest.log");
        // System.out.println(f.getAbsolutePath());
        file.delete();
        final Logger log = LogManager.getLogger("com.foo.Bar");
        assertFalse(PoisonClock.called);
        log.info((Message) new TimeMsg("Async logger msg with embedded timestamp", 123456789000L));
        assertTrue(PoisonClock.called);
        CoreLoggerContexts.stopLoggerContext(false, file); // stop async thread

        final BufferedReader reader = new BufferedReader(new FileReader(file));
        final String line1 = reader.readLine();
        reader.close();
        file.delete();
        assertNotNull(line1);
        assertTrue("line1 correct", line1.equals("123456789000 Async logger msg with embedded timestamp"));
    }
