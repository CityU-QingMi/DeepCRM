    @Test
    public void testEncodedNull() throws Exception
    {
        final String path="/subdir/data.txt%00";
        
        Resource resource=context.getResource(path);
        assertEquals("data.txt%00",resource.getFile().getName());
        assertEquals(docroot,resource.getFile().getParentFile().getParentFile());
        assertFalse(resource.exists());
        
        URL url=context.getServletContext().getResource(path);
        assertNull(url);
    }
