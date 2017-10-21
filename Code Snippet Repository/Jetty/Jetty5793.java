    @Test
    public void testExist_BadURINullX() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);
        
        Path path = dir.resolve("a.jsp");
        Files.createFile(path);

        try
        {
            // request with null and x at end
            URI uri = testdir.getPath().toUri().resolve("a.jsp%00x");
            assertThat("NullX URI",uri,notNullValue());

            Resource r = newResource(uri);
            
            // if we have r, then it better not exist
            assertFalse(r.exists());
        }
        catch (InvalidPathException e)
        {
            // Exception is acceptable
        }
    }
