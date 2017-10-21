    @Test
    public void testPrecidenceVsOrdering() throws Exception
    {
        PathMappings<String> p = new PathMappings<>();
        p.put(new ServletPathSpec("/dump/gzip/*"),"prefix");
        p.put(new ServletPathSpec("*.txt"),"suffix");
       
        assertEquals(null,p.getMatch("/foo/bar"));
        assertEquals("prefix",p.getMatch("/dump/gzip/something").getResource());
        assertEquals("suffix",p.getMatch("/foo/something.txt").getResource());
        assertEquals("prefix",p.getMatch("/dump/gzip/something.txt").getResource());
        
        p = new PathMappings<>();
        p.put(new ServletPathSpec("*.txt"),"suffix");
        p.put(new ServletPathSpec("/dump/gzip/*"),"prefix");
       
        assertEquals(null,p.getMatch("/foo/bar"));
        assertEquals("prefix",p.getMatch("/dump/gzip/something").getResource());
        assertEquals("suffix",p.getMatch("/foo/something.txt").getResource());
        assertEquals("prefix",p.getMatch("/dump/gzip/something.txt").getResource());
    }
