    @Test
    public void testPathMatch()
    {
        assertEquals("pathMatch exact","/Foo/bar",new ServletPathSpec("/Foo/bar").getPathMatch("/Foo/bar"));
        assertEquals("pathMatch prefix","/Foo",new ServletPathSpec("/Foo/*").getPathMatch("/Foo/bar"));
        assertEquals("pathMatch prefix","/Foo",new ServletPathSpec("/Foo/*").getPathMatch("/Foo/"));
        assertEquals("pathMatch prefix","/Foo",new ServletPathSpec("/Foo/*").getPathMatch("/Foo"));
        assertEquals("pathMatch suffix","/Foo/bar.ext",new ServletPathSpec("*.ext").getPathMatch("/Foo/bar.ext"));
        assertEquals("pathMatch default","/Foo/bar.ext",new ServletPathSpec("/").getPathMatch("/Foo/bar.ext"));

        assertEquals("pathMatch default","",new ServletPathSpec("/*").getPathMatch("/xxx/zzz"));
    }
