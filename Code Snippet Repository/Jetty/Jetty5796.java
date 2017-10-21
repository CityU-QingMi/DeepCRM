    @Test
    public void testAddPath_WindowsExtensionLess() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);

        Path basePath = dir.resolve("base");
        FS.ensureDirExists(basePath);
        Path dirPath = basePath.resolve("aa");
        FS.ensureDirExists(dirPath);
        Path filePath = dirPath.resolve("foo.txt");
        Files.createFile(filePath);

        try (Resource base = newResource(basePath.toFile()))
        {
            assertThat("Exists: " + basePath,base.exists(),is(true));
            assertThat("Alias: " + basePath,base,hasNoAlias());

            Resource r = base.addPath("aa./foo.txt");
            assertThat("getURI()", r.getURI().toASCIIString(), containsString("aa./foo.txt"));

            if(OS.IS_WINDOWS)
            {
                assertThat("isAlias()", r.isAlias(), is(true));
                assertThat("getAlias()", r.getAlias(), notNullValue());
                assertThat("getAlias()", r.getAlias().toASCIIString(), containsString("aa/foo.txt"));
                assertThat("Exists: " + r, r.exists(), is(true));
            }
            else
            {
                assertThat("isAlias()", r.isAlias(), is(false));
                assertThat("Exists: " + r, r.exists(), is(false));
            }
        }
        catch (InvalidPathException e)
        {
            // Exception is acceptable
        }
    }
