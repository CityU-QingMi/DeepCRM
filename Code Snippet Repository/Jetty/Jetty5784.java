    @Test
    public void testSemicolon() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        
        try
        {
            // attempt to create file
            Path foo = dir.resolve("foo;");
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
            Resource res = base.addPath("foo;");
            assertThat("Alias: " + res,res,hasNoAlias());
        }
    }
