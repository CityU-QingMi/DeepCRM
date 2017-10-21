    @Test
    public void testEncodedSlosh() throws Exception
    {
        final String path="/subdir%5Cdata.txt";
        
        Resource resource=context.getResource(path);
        assertEquals("subdir%5Cdata.txt",resource.getFile().getName());
        assertEquals(docroot,resource.getFile().getParentFile());
        assertFalse(resource.exists());
        
        URL url=context.getServletContext().getResource(path);
        assertNull(url);
    }
