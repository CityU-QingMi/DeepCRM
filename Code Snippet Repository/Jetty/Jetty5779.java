    @Test
    public void testCase8dot3Alias() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);
        
        Path path = dir.resolve("TextFile.Long.txt");
        Files.createFile(path);
        
        try (Resource base = newResource(dir.toFile()))
        {
            // Long filename
            Resource resource = base.addPath("TextFile.Long.txt");

            assertThat("resource.alias", resource, hasNoAlias());
            assertThat("resource.uri.alias", newResource(resource.getURI()), hasNoAlias());
            assertThat("resource.file.alias", newResource(resource.getFile()), hasNoAlias());

            // On some versions of Windows, the long filename can be referenced
            // via a short 8.3 equivalent filename.
            Resource alias = base.addPath("TEXTFI~1.TXT");
            if (alias.exists())
            {
                // If it exists, it must be an alias
                assertThat("alias", alias, isAliasFor(resource));
                assertThat("alias.uri", newResource(alias.getURI()), isAliasFor(resource));
                assertThat("alias.file", newResource(alias.getFile()), isAliasFor(resource));
            }
        }
    }
