    @Test
    public void testGetPathInfo()
    {
        assertEquals("pathInfo exact",null,new ServletPathSpec("/Foo/bar").getPathInfo("/Foo/bar"));
        assertEquals("pathInfo prefix","/bar",new ServletPathSpec("/Foo/*").getPathInfo("/Foo/bar"));
        assertEquals("pathInfo prefix","/*",new ServletPathSpec("/Foo/*").getPathInfo("/Foo/*"));
        assertEquals("pathInfo prefix","/",new ServletPathSpec("/Foo/*").getPathInfo("/Foo/"));
        assertEquals("pathInfo prefix",null,new ServletPathSpec("/Foo/*").getPathInfo("/Foo"));
        assertEquals("pathInfo suffix",null,new ServletPathSpec("*.ext").getPathInfo("/Foo/bar.ext"));
        assertEquals("pathInfo default",null,new ServletPathSpec("/").getPathInfo("/Foo/bar.ext"));

        assertEquals("pathInfo default","/xxx/zzz",new ServletPathSpec("/*").getPathInfo("/xxx/zzz"));
    }
