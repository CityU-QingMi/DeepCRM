    @Test
    public void testDelete_NotExists() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);
        
        try (Resource base = newResource(dir.toFile()))
        {
            // Is it there?
            Resource res = base.addPath("foo");
            assertThat("foo.exists",res.exists(),is(false));
            // delete it
            assertThat("foo.delete",res.delete(),is(false));
            // is it there?
            assertThat("foo.exists",res.exists(),is(false));
        }
    }
