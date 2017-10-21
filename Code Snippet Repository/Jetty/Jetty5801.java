    @Test
    public void testUtf8Dir() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Path utf8Dir = dir.resolve("bãm");
        Files.createDirectories(utf8Dir);
        
        Path file = utf8Dir.resolve("file.txt");
        Files.createFile(file);
        
        try (Resource base = newResource(utf8Dir.toFile()))
        {
            assertThat("Exists: " + utf8Dir,base.exists(),is(true));
            assertThat("Alias: " + utf8Dir,base,hasNoAlias());

            Resource r = base.addPath("file.txt");
            assertThat("Exists: " + r,r.exists(),is(true));
            assertThat("Alias: " + r,r,hasNoAlias());
        }
    }
