    @Test
    public void testAddInitialSlash() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);

        Path basePath = dir.resolve("base");
        FS.ensureDirExists(basePath);
        Path filePath = basePath.resolve("foo.txt");
        Files.createFile(filePath);

        try (Resource base = newResource(basePath.toFile()))
        {
            assertThat("Exists: " + basePath,base.exists(),is(true));
            assertThat("Alias: " + basePath,base,hasNoAlias());

            Resource r = base.addPath("/foo.txt");
            assertThat("getURI()", r.getURI().toASCIIString(), containsString("/foo.txt"));

            assertThat("isAlias()", r.isAlias(), is(false));
            assertThat("Exists: " + r, r.exists(), is(true));
        }
        catch (InvalidPathException e)
        {
            // Exception is acceptable
        }
    }
