    @Test
    public void testPrefixPathSpec()
    {
        ServletPathSpec spec = new ServletPathSpec("/downloads/*");
        assertEquals("Spec.pathSpec","/downloads/*",spec.getDeclaration());
        assertEquals("Spec.pathDepth",2,spec.getPathDepth());

        assertMatches(spec,"/downloads/logo.jpg");
        assertMatches(spec,"/downloads/distribution.tar.gz");
        assertMatches(spec,"/downloads/distribution.tgz");
        assertMatches(spec,"/downloads/distribution.zip");

        assertMatches(spec,"/downloads");

        assertEquals("Spec.pathInfo","/",spec.getPathInfo("/downloads/"));
        assertEquals("Spec.pathInfo","/distribution.zip",spec.getPathInfo("/downloads/distribution.zip"));
        assertEquals("Spec.pathInfo","/dist/9.0/distribution.tar.gz",spec.getPathInfo("/downloads/dist/9.0/distribution.tar.gz"));
    }
