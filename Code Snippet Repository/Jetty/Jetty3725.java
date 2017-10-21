    @Test
    public void testGetKnown() throws Exception
    {
        final String path="/index.html";
        Resource resource=context.getResource(path);
        assertEquals("index.html",resource.getFile().getName());
        assertEquals(docroot,resource.getFile().getParentFile());
        assertTrue(resource.exists());
        
        URL url=context.getServletContext().getResource(path);
        assertEquals(docroot,new File(url.toURI()).getParentFile());
    }
