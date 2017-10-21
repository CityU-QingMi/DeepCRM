    @Test
    public void testLastModified_NotExists() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        
        try (Resource base = newResource(dir.toFile()))
        {
            Resource res = base.addPath("foo");
            assertThat("foo.lastModified",res.lastModified(),is(0L));
        }
    }
