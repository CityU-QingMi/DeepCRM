    @Test
    public void testAddRootPath() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Path subdir = dir.resolve("sub");
        Files.createDirectories(subdir);

        String readableRootDir = findRootDir(dir.getFileSystem());
        assumeThat("Readable Root Dir found",readableRootDir,notNullValue());

        try (Resource base = newResource(dir.toFile()))
        {
            Resource sub = base.addPath("sub");
            assertThat("sub",sub.isDirectory(),is(true));

            try
            {
                Resource rrd = sub.addPath(readableRootDir);
                // valid path for unix and OSX
                assertThat("Readable Root Dir",rrd.exists(),is(false));
            }
            catch (MalformedURLException | InvalidPathException e)
            {
                // valid path on Windows
            }
        }
    }
