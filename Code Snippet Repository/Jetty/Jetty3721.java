    @Test
    public void testGetUnknownDir() throws Exception
    {
        final String path="/unknown/";
        Resource resource=context.getResource(path);
        assertEquals("unknown",resource.getFile().getName());
        assertEquals(docroot,resource.getFile().getParentFile());
        assertFalse(resource.exists());
        
        URL url=context.getServletContext().getResource(path);
        assertNull(url);
    }
