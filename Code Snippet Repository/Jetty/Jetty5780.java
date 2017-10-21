    @Test
    public void testNTFSFileStreamAlias() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);

        Path path = dir.resolve("testfile");
        Files.createFile(path);
        
        try (Resource base = newResource(dir.toFile()))
        {
            Resource resource = base.addPath("testfile");

            assertThat("resource.alias", resource, hasNoAlias());
            assertThat("resource.uri.alias", newResource(resource.getURI()), hasNoAlias());
            assertThat("resource.file.alias", newResource(resource.getFile()), hasNoAlias());

            try
            {
                // Attempt to reference same file, but via NTFS simple stream
                Resource alias = base.addPath("testfile:stream");
                if (alias.exists())
                {
                    // If it exists, it must be an alias
                    assertThat("resource.alias",alias,isAliasFor(resource));
                    assertThat("resource.uri.alias",newResource(alias.getURI()),isAliasFor(resource));
                    assertThat("resource.file.alias",newResource(alias.getFile()),isAliasFor(resource));
                }
            }
            catch (InvalidPathException e)
            {
                // NTFS filesystem streams are unsupported on some platforms.
                assumeNoException(e);
            }
        }
    }
