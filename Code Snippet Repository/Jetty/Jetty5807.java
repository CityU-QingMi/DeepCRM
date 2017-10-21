    @Test
    public void testIsContainedIn() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);
        Path foo = dir.resolve("foo");
        Files.createFile(foo);

        try (Resource base = newResource(dir.toFile()))
        {
            Resource res = base.addPath("foo");
            assertThat("is contained in",res.isContainedIn(base),is(false));
        }
    }
