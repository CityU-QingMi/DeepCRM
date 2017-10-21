    @Test
    public void testParentPath()
    {
        assertEquals("parent /aaa/bbb/","/aaa/", URIUtil.parentPath("/aaa/bbb/"));
        assertEquals("parent /aaa/bbb","/aaa/", URIUtil.parentPath("/aaa/bbb"));
        assertEquals("parent /aaa/","/", URIUtil.parentPath("/aaa/"));
        assertEquals("parent /aaa","/", URIUtil.parentPath("/aaa"));
        assertEquals("parent /",null, URIUtil.parentPath("/"));
        assertEquals("parent null",null, URIUtil.parentPath(null));

    }
