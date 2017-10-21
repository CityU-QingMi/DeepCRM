    @Test
    public void testCaseInsensitiveAlias() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);
        Path path = dir.resolve("file");
        Files.createFile(path);
        
        try (Resource base = newResource(dir.toFile()))
        {
            // Reference to actual resource that exists
            Resource resource = base.addPath("file");

            assertThat("resource.alias", resource, hasNoAlias());
            assertThat("resource.uri.alias", newResource(resource.getURI()), hasNoAlias());
            assertThat("resource.file.alias", newResource(resource.getFile()), hasNoAlias());

            // On some case insensitive file systems, lets see if an alternate
            // case for the filename results in an alias reference
            Resource alias = base.addPath("FILE");
            if (alias.exists())
            {
                // If it exists, it must be an alias
                assertThat("alias", alias, isAliasFor(resource));
                assertThat("alias.uri", newResource(alias.getURI()), isAliasFor(resource));
                assertThat("alias.file", newResource(alias.getFile()), isAliasFor(resource));
            }
        }
    }
