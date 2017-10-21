    @Test
    public void testSlashSlash() throws Exception
    {
        File expected = new File(docroot, OS.separators("subdir/data.txt"));
        URL expectedUrl = expected.toURI().toURL();
        
        String path="//subdir/data.txt";
        Resource resource=context.getResource(path);
        assertThat("Resource: " + resource, resource, nullValue());
        URL url=context.getServletContext().getResource(path);
        assertThat("Resource: " + url, url, nullValue());

        path="/subdir//data.txt";
        resource=context.getResource(path);
        assertThat("Resource: " + resource, resource, nullValue());
        url=context.getServletContext().getResource(path);
        assertThat("Resource: " + url, url, nullValue());
    }
