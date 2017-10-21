    @Test
    public void testExist_Normal() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);
        
        Path path = dir.resolve("a.jsp");
        Files.createFile(path);

        URI ref = testdir.getPath().toUri().resolve("a.jsp");
        try (Resource fileres = newResource(ref))
        {
            assertThat("Resource: " + fileres,fileres.exists(),is(true));
        }
    }
