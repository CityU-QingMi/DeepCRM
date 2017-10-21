    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        File testDir = MavenTestingUtils.getTargetTestingDir(ScannerTest.class.getSimpleName());
        FS.ensureEmpty(testDir);
        
        // Use full path, pointing to a real directory (for FileSystems that are case-insensitive, like Windows and OSX to use)
        // This is only needed for the various comparisons below to make sense.
        _directory = testDir.toPath().toRealPath().toFile();

        _scanner = new Scanner();
        _scanner.addScanDir(_directory);
        _scanner.setScanInterval(0);
        _scanner.addListener(new Scanner.DiscreteListener()
        {
            public void fileRemoved(String filename) throws Exception
            {
                _queue.add(new Event(filename,Notification.REMOVED));
            }

            public void fileChanged(String filename) throws Exception
            {
                _queue.add(new Event(filename,Notification.CHANGED));
            }

            public void fileAdded(String filename) throws Exception
            {
                _queue.add(new Event(filename,Notification.ADDED));
            }
        });
        _scanner.addListener(new Scanner.BulkListener()
        {
            public void filesChanged(List<String> filenames) throws Exception
            {
                _bulk.add(filenames);
            }
        });
        _scanner.start();

        _scanner.scan();
        
        Assert.assertTrue(_queue.isEmpty());
        Assert.assertTrue(_bulk.isEmpty());
    }
