    @Test
    public void testLength_NotExists() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);
        
        try (Resource base = newResource(dir.toFile()))
        {
            Resource res = base.addPath("foo");
            assertThat("foo.length",res.length(),is(0L));
        }
    }
