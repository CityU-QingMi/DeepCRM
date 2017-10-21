    @Test
    public void testSubdir() throws Exception
    {
        final String path="/subdir";
        Resource resource=context.getResource(path);
        assertEquals(docroot,resource.getFile().getParentFile());
        assertTrue(resource.exists());
        assertTrue(resource.isDirectory());
        assertTrue(resource.toString().endsWith("/"));
        
        URL url=context.getServletContext().getResource(path);
        assertEquals(docroot,new File(url.toURI()).getParentFile());
    }
