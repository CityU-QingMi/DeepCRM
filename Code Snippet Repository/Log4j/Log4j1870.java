    @Test
    public void testAppenderCompressPermissions() throws Exception {
        for (int i = 0; i < 500; ++i) {
            final String message = "This is test message number " + i;
            logger.debug(message);
            if (i % 100 == 0) {
                Thread.sleep(500);
            }
        }
        if (!loggerContextRule.getLoggerContext().stop(30, TimeUnit.SECONDS)) {
            System.err.println("Could not stop cleanly " + loggerContextRule + " for " + this);
        }
        final File dir = new File(DIR);
        assertTrue("Directory not created", dir.exists());
        final File[] files = dir.listFiles();
        assertNotNull(files);
        int gzippedFiles1 = 0;
        int gzippedFiles2 = 0;
        for (final File file : files) {
            final FileExtension ext = FileExtension.lookupForFile(file.getName());
            if (ext != null) {
                if (file.getName().startsWith("test1")) {
                    gzippedFiles1++;
                    assertEquals("rw-------",
                            PosixFilePermissions.toString(Files.getPosixFilePermissions(file.toPath())));
                } else {
                    gzippedFiles2++;
                    assertEquals("r--r--r--",
                            PosixFilePermissions.toString(Files.getPosixFilePermissions(file.toPath())));
                }
            } else {
                if (file.getName().startsWith("test1")) {
                    assertEquals("rw-------",
                            PosixFilePermissions.toString(Files.getPosixFilePermissions(file.toPath())));
                } else {
                    assertEquals("rwx------",
                            PosixFilePermissions.toString(Files.getPosixFilePermissions(file.toPath())));
                }
            }
        }
        assertTrue("Files not rolled : " + files.length, files.length > 2);
        assertTrue("Files 1 gzipped not rolled : " + gzippedFiles1, gzippedFiles1 > 0);
        assertTrue("Files 2 gzipped not rolled : " + gzippedFiles2, gzippedFiles2 > 0);
    }
