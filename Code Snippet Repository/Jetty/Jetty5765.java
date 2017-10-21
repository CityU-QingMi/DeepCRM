    @Test
    public void testLength() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);
        
        Path file = dir.resolve("foo");

        try (StringReader reader = new StringReader("foo");
             BufferedWriter writer = Files.newBufferedWriter(file))
        {
            IO.copy(reader,writer);
        }

        long expected = Files.size(file);

        try (Resource base = newResource(dir.toFile()))
        {
            Resource res = base.addPath("foo");
            assertThat("foo.length",res.length(),is(expected));
        }
    }
