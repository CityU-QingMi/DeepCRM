    @Test
    public void testEqualsIgnoreEncoding()
    {
        assertTrue(URIUtil.equalsIgnoreEncodings("http://example.com/foo/bar","http://example.com/foo/bar" ));
        assertTrue(URIUtil.equalsIgnoreEncodings("/barry's","/barry%27s"));
        assertTrue(URIUtil.equalsIgnoreEncodings("/barry%27s","/barry's"));
        assertTrue(URIUtil.equalsIgnoreEncodings("/barry%27s","/barry%27s"));
        assertTrue(URIUtil.equalsIgnoreEncodings("/b rry's","/b%20rry%27s"));
        assertTrue(URIUtil.equalsIgnoreEncodings("/b rry%27s","/b%20rry's"));
        assertTrue(URIUtil.equalsIgnoreEncodings("/b rry%27s","/b%20rry%27s"));
        
        assertTrue(URIUtil.equalsIgnoreEncodings("/foo%2fbar","/foo%2fbar"));
        assertTrue(URIUtil.equalsIgnoreEncodings("/foo%2fbar","/foo%2Fbar"));
        
        assertFalse(URIUtil.equalsIgnoreEncodings("ABC", "abc"));
        assertFalse(URIUtil.equalsIgnoreEncodings("/barry's","/barry%26s"));
        
        assertFalse(URIUtil.equalsIgnoreEncodings("/foo/bar","/foo%2fbar"));
        assertFalse(URIUtil.equalsIgnoreEncodings("/foo2fbar","/foo/bar"));
    }
