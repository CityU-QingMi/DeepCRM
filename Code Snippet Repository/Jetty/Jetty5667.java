    @Test
    public void testCompactPath()
    {
        assertEquals("/foo/bar", URIUtil.compactPath("/foo/bar"));
        assertEquals("/foo/bar?a=b//c", URIUtil.compactPath("/foo/bar?a=b//c"));

        assertEquals("/foo/bar", URIUtil.compactPath("//foo//bar"));
        assertEquals("/foo/bar?a=b//c", URIUtil.compactPath("//foo//bar?a=b//c"));

        assertEquals("/foo/bar", URIUtil.compactPath("/foo///bar"));
        assertEquals("/foo/bar?a=b//c", URIUtil.compactPath("/foo///bar?a=b//c"));
    }
