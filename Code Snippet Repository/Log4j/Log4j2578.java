    @Test
    public void testWatchManager() throws Exception {
        Assume.assumeFalse(IS_WINDOWS);
        final ConfigurationScheduler scheduler = new ConfigurationScheduler();
        scheduler.incrementScheduledItems();
        final WatchManager watchManager = new WatchManager(scheduler);
        watchManager.setIntervalSeconds(1);
        scheduler.start();
        watchManager.start();
        try {
            final File sourceFile = new File(originalFile);
            final FileOutputStream targetStream = new FileOutputStream(testFile);
            final File updateFile = new File(newFile);
            Path source = Paths.get(sourceFile.toURI());
            Files.copy(source, targetStream);
            final File targetFile = new File(testFile);
            final BlockingQueue<File> queue = new LinkedBlockingQueue<>();
            watchManager.watchFile(targetFile, new TestWatcher(queue));
            Thread.sleep(1000);
            source = Paths.get(updateFile.toURI());
            Files.copy(source, Paths.get(targetFile.toURI()), StandardCopyOption.REPLACE_EXISTING);
            Thread.sleep(1000);
            final File f = queue.poll(1, TimeUnit.SECONDS);
            assertNotNull("File change not detected", f);
        } finally {
            watchManager.stop();
            scheduler.stop();
        }
    }
