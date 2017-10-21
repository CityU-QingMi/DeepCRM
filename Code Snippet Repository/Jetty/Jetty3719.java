    @Test
    public void testSymlinkUnknown() throws Exception
    {
        if (!OS.IS_UNIX)
            return;
        try
        {
            allowSymlinks.set(true);

            final String path="/other/unknown.txt";

            Resource resource=context.getResource(path);
            assertNotNull(resource);
            assertEquals("unknown.txt",resource.getFile().getName());
            assertEquals(docroot,resource.getFile().getParentFile().getParentFile());
            assertFalse(resource.exists());

            URL url=context.getServletContext().getResource(path);
            assertNull(url);
        }
        finally
        {
            allowSymlinks.set(false);
        }
    }
