    @Test
    public void testRoot() throws Exception
    {
        final String path="/";
        Resource resource=context.getResource(path);
        assertEquals(docroot,resource.getFile());
        assertTrue(resource.exists());
        assertTrue(resource.isDirectory());
        
        URL url=context.getServletContext().getResource(path);
        assertEquals(docroot,new File(url.toURI()));
    }
