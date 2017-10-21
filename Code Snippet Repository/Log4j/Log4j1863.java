    @Test
    public void testAppender() throws Exception {
        final File dir = new File(DIR);
        dir.mkdirs();
        try (final WatchService watcher = FileSystems.getDefault().newWatchService()) {
            WatchKey key = dir.toPath().register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

            for (int i = 0; i < 100; ++i) {
                logger.debug("This is test message number " + i);
            }
            Thread.sleep(50);
            assertTrue("Directory not created", dir.exists() && dir.listFiles().length > 0);
            final File[] files = dir.listFiles();
            assertNotNull(files);
            assertThat(files, hasItemInArray(that(hasName(that(endsWith(".gz"))))));

            int temporaryFilesCreated = 0;
            int compressedFiles = 0;
            key = watcher.take();

            for (final WatchEvent<?> event : key.pollEvents()) {
                final WatchEvent<Path> ev = (WatchEvent<Path>) event;
                final Path filename = ev.context();
                if (filename.toString().endsWith(".tmp")) {
                    temporaryFilesCreated++;
                }
                if (filename.toString().endsWith(".gz")) {
                    compressedFiles++;
                }
            }
            assertTrue("No temporary file created during compression", temporaryFilesCreated > 0);
            assertTrue("Temporarys file created not equals to compressed files",
                    compressedFiles == temporaryFilesCreated);
        }
    }
