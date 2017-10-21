    @Before
    public void setUp() throws Exception {
        base = Files.createTempDirectory("tempDir", new FileAttribute<?>[0]);
        aaa = Files.createTempFile(base, "aaa", null, new FileAttribute<?>[0]);
        bbb = Files.createTempFile(base, "bbb", null, new FileAttribute<?>[0]);
        ccc = Files.createTempFile(base, "ccc", null, new FileAttribute<?>[0]);
        
        // lastModified granularity is 1 sec(!) on some file systems...
        final long now = System.currentTimeMillis();
        Files.setLastModifiedTime(aaa, FileTime.fromMillis(now));
        Files.setLastModifiedTime(bbb, FileTime.fromMillis(now + 1000));
        Files.setLastModifiedTime(ccc, FileTime.fromMillis(now + 2000));
    }
