    @Test
    public void testDeep() throws Exception
    {
        final String path="/subdir/data.txt";
        Resource resource=context.getResource(path);
        assertEquals("data.txt",resource.getFile().getName());
        assertEquals(docroot,resource.getFile().getParentFile().getParentFile());
        assertTrue(resource.exists());
        
        URL url=context.getServletContext().getResource(path);
        assertEquals(docroot,new File(url.toURI()).getParentFile().getParentFile());
    }
