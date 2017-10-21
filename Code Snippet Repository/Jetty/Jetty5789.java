    @Test
    public void testCaret() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);
        
        try
        {
            // attempt to create file
            Path foo = dir.resolve("foo^3.txt");
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

            Resource res = base.addPath("foo^3.txt");
            assertThat("Alias: " + res,res.getAlias(),nullValue());
        }
    }
