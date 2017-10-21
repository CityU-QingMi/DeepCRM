    @AfterClass
    public static void afterClass() throws Exception {
        long size = 0;
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(DIR))) {
            for (final Path path : directoryStream) {
                if (size == 0) {
                    size = Files.size(path);
                } else {
                    final long fileSize = Files.size(path);
                    assertTrue("Expected size: " + size + " Size of " + path.getFileName() + ": " + fileSize,
                            size == fileSize);
                }
                Files.delete(path);
            }
            Files.delete(Paths.get(DIR));
        }
    }
