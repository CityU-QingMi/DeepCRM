    @Test
    public void testAddPath() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        
        Path subdir = dir.resolve("sub");
        FS.ensureDirExists(subdir.toFile());

        try (Resource base = newResource(dir.toFile()))
        {
            Resource sub = base.addPath("sub");
            assertThat("sub/.isDirectory",sub.isDirectory(),is(true));

            Resource tmp = sub.addPath("/tmp");
            assertThat("No root",tmp.exists(),is(false));
        }
    }
