    @Test
    public void testServletMatchDefault()
    {
        PathMappings<String> p = new PathMappings<>();

        p.put(new ServletPathSpec("/"),"default");
        p.put(new ServletPathSpec("/*"),"any"); 

        assertMatch(p,"/abs/path","any");
        assertMatch(p,"/abs/path/xxx","any");
        assertMatch(p,"/animal/bird/eagle/bald","any");
        assertMatch(p,"/","any");
    }
