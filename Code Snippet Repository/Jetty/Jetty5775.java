    @Test
    public void testList() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);
        
        Files.createFile(dir.resolve("foo"));
        Files.createFile(dir.resolve("bar"));
        Files.createDirectories(dir.resolve("tick"));
        Files.createDirectories(dir.resolve("tock"));
        
        List<String> expected = new ArrayList<>();
        expected.add("foo");
        expected.add("bar");
        expected.add("tick/");
        expected.add("tock/");

        try (Resource base = newResource(dir.toFile()))
        {
            String list[] = base.list();
            List<String> actual = Arrays.asList(list);
            
            assertEquals(expected.size(),actual.size());
            for (String s : expected)
                assertEquals(true,actual.contains(s));
            
        }
    }
