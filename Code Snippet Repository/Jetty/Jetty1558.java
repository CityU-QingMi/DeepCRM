    @Test
    public void testPrecidenceVsOrdering() throws Exception
    {
        PathMap<String> p = new PathMap<>();
        p.put("/dump/gzip/*","prefix");
        p.put("*.txt","suffix");
       
        assertEquals(null,p.getMatch("/foo/bar"));
        assertEquals("prefix",p.getMatch("/dump/gzip/something").getValue());
        assertEquals("suffix",p.getMatch("/foo/something.txt").getValue());
        assertEquals("prefix",p.getMatch("/dump/gzip/something.txt").getValue());
        
        p = new PathMap<>();
        p.put("*.txt","suffix");
        p.put("/dump/gzip/*","prefix");
       
        assertEquals(null,p.getMatch("/foo/bar"));
        assertEquals("prefix",p.getMatch("/dump/gzip/something").getValue());
        assertEquals("suffix",p.getMatch("/foo/something.txt").getValue());
        assertEquals("prefix",p.getMatch("/dump/gzip/something.txt").getValue());
    }
