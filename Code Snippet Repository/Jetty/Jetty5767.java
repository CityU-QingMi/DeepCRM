    @Test
    public void testDelete() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);
        Path file = dir.resolve("foo");
        Files.createFile(file);

        try (Resource base = newResource(dir.toFile()))
        {
            // Is it there?
            Resource res = base.addPath("foo");
            assertThat("foo.exists",res.exists(),is(true));
            // delete it
            assertThat("foo.delete",res.delete(),is(true));
            // is it there?
            assertThat("foo.exists",res.exists(),is(false));
        }
    }
