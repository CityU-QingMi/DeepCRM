    @Test
    public void testHostname() {
        final org.apache.logging.log4j.Logger testLogger = context.getLogger("org.apache.logging.log4j.hosttest");
        testLogger.debug("Hello, {}", "World");
        final List<String> msgs = host.getMessages();
        assertThat(msgs, hasSize(1));
        String expected = NetUtils.getLocalHostname() + Strings.LINE_SEPARATOR;
        assertThat(msgs.get(0), endsWith(expected));
        assertNotNull("No Host FileAppender file name", hostFile.getFileName());
        expected = "target/" + NetUtils.getLocalHostname() + ".log";
        String name = hostFile.getFileName();
        assertEquals("Incorrect HostFile FileAppender file name - expected " + expected + " actual - " + name, name,
            expected);
        name = hostFile.getFilePattern();
        assertNotNull("No file pattern", name);
        expected = "target/" + NetUtils.getLocalHostname() + "-%d{MM-dd-yyyy}-%i.log";
        assertEquals("Incorrect HostFile FileAppender file pattern - expected " + expected + " actual - " + name, name,
            expected);

    }
