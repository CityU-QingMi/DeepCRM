    @Test
    public void testDeserialization() throws Exception {
        testSerialization();
        final File file = new File(DAT_PATH);
        final FileInputStream fis = new FileInputStream(file);
        try (final ObjectInputStream ois = new ObjectInputStream(fis) ) {
            final LogEvent event = (LogEvent) ois.readObject();
            assertNotNull(event);
        }
    }
