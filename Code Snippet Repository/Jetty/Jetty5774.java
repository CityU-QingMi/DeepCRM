    @SuppressWarnings("")
    @Test
    public void testGetURL() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);

        Path file = dir.resolve("foo");
        Files.createFile(file);

        URL expected = file.toUri().toURL();

        try (Resource base = newResource(dir.toFile()))
        {
            Resource foo = base.addPath("foo");
            assertThat("getURL",foo.getURL(),is(expected));
        }
    }
