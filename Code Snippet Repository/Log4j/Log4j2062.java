    @Test
    public void test() throws Exception {

        // To ensure our custom plugin is NOT included in the log4j plugin metadata file,
        // we make sure the class does not exist until after the build is finished.
        // So we don't create the custom plugin class until this test is run.
        final File orig = new File("target/test-classes/customplugin/FixedStringLayout.java.source");
        final File f = new File(orig.getParentFile(), "FixedStringLayout.java");
        assertTrue("renamed source file OK", orig.renameTo(f));
        compile(f);
        assertTrue("reverted source file OK", f.renameTo(orig));

        // load the compiled class
        Class.forName("customplugin.FixedStringLayout");

        // now that the custom plugin class exists, we load the config
        // with the packages element pointing to our custom plugin
        ctx = Configurator.initialize("Test1", "customplugin/log4j2-741.xml");
        config = ctx.getConfiguration();
        listAppender = config.getAppender("List");

        final Logger logger = LogManager.getLogger(PluginManagerPackagesTest.class);
        logger.info("this message is ignored");

        final List<String> messages = listAppender.getMessages();
        assertEquals(messages.toString(), 1, messages.size());
        assertEquals("abc123XYZ", messages.get(0));
    }
