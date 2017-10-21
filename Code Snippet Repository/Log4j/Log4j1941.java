    @Test
    public void testAdditivity() throws Exception {
        final File file = new File("target", "AsyncLoggerConfigTest.log");
        assertTrue("Deleted old file before test", !file.exists() || file.delete());

        final Logger log = LogManager.getLogger("com.foo.Bar");
        final String msg = "Additive logging: 2 for the price of 1!";
        log.info(msg);
        CoreLoggerContexts.stopLoggerContext(file); // stop async thread

        final BufferedReader reader = new BufferedReader(new FileReader(file));
        final String line1 = reader.readLine();
        final String line2 = reader.readLine();
        reader.close();
        file.delete();
        assertNotNull("line1", line1);
        assertNotNull("line2", line2);
        assertTrue("line1 correct", line1.contains(msg));
        assertTrue("line2 correct", line2.contains(msg));

        final String location = "testAdditivity";
        assertTrue("location", line1.contains(location) || line2.contains(location));
    }
