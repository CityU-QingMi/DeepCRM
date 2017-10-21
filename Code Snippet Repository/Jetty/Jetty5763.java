    @Test
    public void testLastModified() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        File file = testdir.getPathFile("foo").toFile();
        file.createNewFile();

        long expected = file.lastModified();

        try (Resource base = newResource(dir.toFile()))
        {
            Resource res = base.addPath("foo");
            assertThat("foo.lastModified",res.lastModified()/1000*1000, lessThanOrEqualTo(expected));
        }
    }
