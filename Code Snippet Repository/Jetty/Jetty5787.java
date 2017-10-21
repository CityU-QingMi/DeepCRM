    @Test
    public void testBrackets() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);
        
        try
        {
            // attempt to create file
            Path foo = dir.resolve("foo[1]");
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
            Resource res = base.addPath("foo[1]");
            assertThat("Alias: " + res,res.getAlias(),nullValue());
        }
    }
