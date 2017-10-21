    @Test
    public void testSymlinkNested() throws Exception
    {
        Assume.assumeTrue(OS.IS_UNIX);
        
        try
        {
            allowSymlinks.set(true);

            final String path="/web/logs/file.log";

            Resource resource=context.getResource(path);
            assertNotNull(resource);
            assertEquals("file.log",resource.getFile().getName());
            assertTrue(resource.exists());
        }
        finally
        {
            allowSymlinks.set(false);
        } 

    }
