    @Test
    public void testConsecutiveReconfigure() throws Exception {
        System.setProperty(ConfigurationFactory.CONFIGURATION_FILE_PROPERTY,
                "AsyncLoggerConfigTest2.xml");
        final File file = new File("target", "AsyncLoggerConfigTest2.log");
        assertTrue("Deleted old file before test", !file.exists() || file.delete());
        
        final Logger log = LogManager.getLogger("com.foo.Bar");
        final String msg = "Message before reconfig";
        log.info(msg);

        final LoggerContext ctx = LoggerContext.getContext(false);
        ctx.reconfigure();
        ctx.reconfigure();
        
        final String msg2 = "Message after reconfig";
        log.info(msg2);
        CoreLoggerContexts.stopLoggerContext(file); // stop async thread

        final BufferedReader reader = new BufferedReader(new FileReader(file));
        final String line1 = reader.readLine();
        final String line2 = reader.readLine();
        reader.close();
        file.delete();
        assertNotNull("line1", line1);
        assertNotNull("line2", line2);
        assertTrue("line1 " + line1, line1.contains(msg));
        assertTrue("line2 " + line2, line2.contains(msg2));
    }
