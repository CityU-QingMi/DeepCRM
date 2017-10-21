    @Test
    public void testSingleBackTick() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);
        
        try
        {
            // attempt to create file
            Path foo = dir.resolve("foo` bar");
            Files.createFile(foo);
        }
        catch (Exception e)
        {
            // if unable to create file, no point testing the rest.
            // this is the path that Microsoft Windows takes.
            assumeNoException(e);
        }

        try (Resource base = newResource(dir.toFile()))
        {
            // FileResource does not pass this test!
            assumeFalse(base instanceof FileResource);

            Resource res = base.addPath("foo` bar");
            assertThat("Alias: " + res,res.getAlias(),nullValue());
        }
    }
