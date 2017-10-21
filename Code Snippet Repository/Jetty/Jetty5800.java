    @Test
    public void testEncoding() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);

        Path specials = dir.resolve("a file with,spe#ials");
        Files.createFile(specials);
        
        try(Resource res = newResource(specials.toFile()))
        {
            assertThat("Specials URL", res.getURI().toASCIIString(), containsString("a%20file%20with,spe%23ials"));
            assertThat("Specials Filename", res.getFile().toString(), containsString("a file with,spe#ials"));
            
            res.delete();
            assertThat("File should have been deleted.",res.exists(),is(false));
        }
    }
