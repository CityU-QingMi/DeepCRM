    @Test
    public void testGetLoggerForAnonymousInnerClass1() throws IOException {
        final Closeable closeable = new Closeable() {
            
            Logger LOGGER = LogManager.getLogger();
            
            @Override
            public void close() throws IOException {
                Assert.assertEquals("org.apache.logging.log4j.LogManagerTest$1", LOGGER.getName());
            }
        };
        closeable.close();
    }
