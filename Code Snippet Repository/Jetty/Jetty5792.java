    @Test
    public void testExist_BadURINull() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);
        
        Path path = dir.resolve("a.jsp");
        Files.createFile(path);

        try
        {
            // request with null at end
            URI uri = testdir.getPath().toUri().resolve("a.jsp%00");
            assertThat("Null URI",uri,notNullValue());

            Resource r = newResource(uri);
            
            // if we have r, then it better not exist
            assertFalse(r.exists());
        }
        catch (InvalidPathException e)
        {
            // Exception is acceptable
        }
    }
