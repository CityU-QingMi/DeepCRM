    @Test
    public void testInputStream() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);
        
        Path file = dir.resolve("foo");
        String content = "Foo is here";

        try (StringReader reader = new StringReader(content);
             BufferedWriter writer = Files.newBufferedWriter(file))
        {
            IO.copy(reader,writer);
        }

        try (Resource base = newResource(dir.toFile()))
        {
            Resource foo = base.addPath("foo");
            try (InputStream stream = foo.getInputStream(); InputStreamReader reader = new InputStreamReader(stream); StringWriter writer = new StringWriter())
            {
                IO.copy(reader,writer);
                assertThat("Stream",writer.toString(),is(content));
            }
        }
    }
