    @Test
    public void testEncodedSlash() throws Exception
    {
        final String path="/subdir%2Fdata.txt";
        
        Resource resource=context.getResource(path);
        assertEquals("subdir%2Fdata.txt",resource.getFile().getName());
        assertEquals(docroot,resource.getFile().getParentFile());
        assertFalse(resource.exists());
        
        URL url=context.getServletContext().getResource(path);
        assertNull(url);
    }
