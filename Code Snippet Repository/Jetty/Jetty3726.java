    @Test
    public void testNormalize() throws Exception
    {
        final String path="/down/.././index.html";
        Resource resource=context.getResource(path);
        assertEquals("index.html",resource.getFile().getName());
        assertEquals(docroot,resource.getFile().getParentFile());
        assertTrue(resource.exists());
        
        URL url=context.getServletContext().getResource(path);
        assertEquals(docroot,new File(url.toURI()).getParentFile());
    }
