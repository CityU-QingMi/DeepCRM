    @Test
    public void testSymlinkKnown() throws Exception
    {
        Assume.assumeTrue(OS.IS_UNIX);
        
        try
        {
            allowSymlinks.set(true);

            final String path="/other/other.txt";

            Resource resource=context.getResource(path);
            assertNotNull(resource);
            assertEquals("other.txt",resource.getFile().getName());
            assertEquals(docroot,resource.getFile().getParentFile().getParentFile());
            assertTrue(resource.exists());

            URL url=context.getServletContext().getResource(path);
            assertEquals(docroot,new File(url.toURI()).getParentFile().getParentFile());
        }
        finally
        {
            allowSymlinks.set(false);
        } 
        
    }
