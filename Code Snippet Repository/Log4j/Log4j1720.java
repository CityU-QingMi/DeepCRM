    @Test
    public void testRemapAfterInitialMapSizeExceeded() throws IOException {
        final int mapSize = 64; // very small, on purpose
        final File file = File.createTempFile("log4j2", "test");
        file.deleteOnExit();
        assertEquals(0, file.length());

        final boolean append = false;
        final boolean immediateFlush = false;
        try (final MemoryMappedFileManager manager = MemoryMappedFileManager.getFileManager(file.getAbsolutePath(),
                append, immediateFlush, mapSize, null, null)) {
            byte[] msg;
            for (int i = 0; i < 1000; i++) {
                msg = ("Message " + i + "\n").getBytes();
                manager.write(msg, 0, msg.length, false);
            }

        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            for (int i = 0; i < 1000; i++) {
                assertNotNull("line", line);
                assertTrue("line incorrect", line.contains("Message " + i));
                line = reader.readLine();
            }
        }
    }
