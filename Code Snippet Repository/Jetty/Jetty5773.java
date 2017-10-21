    @Test
    public void testGetURI() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);

        Path file = dir.resolve("foo");
        Files.createFile(file);

        URI expected = file.toUri();

        try (Resource base = newResource(dir.toFile()))
        {
            Resource foo = base.addPath("foo");
            assertThat("getURI",foo.getURI(),is(expected));
        }
    }
