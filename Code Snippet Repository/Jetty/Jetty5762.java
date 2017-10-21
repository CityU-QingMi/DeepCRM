    @Test
    public void testIsDirectory() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);
        Path foo = dir.resolve("foo");
        Files.createFile(foo);

        Path subdir = dir.resolve("sub");
        Files.createDirectories(subdir);

        try (Resource base = newResource(dir.toFile()))
        {
            Resource res = base.addPath("foo");
            assertThat("foo.isDirectory",res.isDirectory(),is(false));

            Resource sub = base.addPath("sub");
            assertThat("sub/.isDirectory",sub.isDirectory(),is(true));
        }
    }
