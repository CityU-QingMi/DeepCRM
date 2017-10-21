    @Test
    public void testGetUnknown() throws Exception
    {
        final String path="/unknown.txt";
        Resource resource=context.getResource(path);
        assertEquals("unknown.txt",resource.getFile().getName());
        assertEquals(docroot,resource.getFile().getParentFile());
        assertFalse(resource.exists());
        
        URL url=context.getServletContext().getResource(path);
        assertNull(url);
    }
