    @Test
    public void testName() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);
        
        String expected = dir.toAbsolutePath().toString();

        try (Resource base = newResource(dir.toFile()))
        {
            assertThat("base.name",base.getName(),is(expected));
        }
    }
