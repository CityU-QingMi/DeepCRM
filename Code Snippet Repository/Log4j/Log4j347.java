    @Test
    public void testGetLoggerForAnonymousInnerClass2() throws IOException {
        final Closeable closeable = new Closeable() {
            
            Logger LOGGER = LogManager.getLogger(getClass());
            
            @Override
            public void close() throws IOException {
                Assert.assertEquals("org.apache.logging.log4j.LogManagerTest$2", LOGGER.getName());
            }
        };
        closeable.close();
    }
