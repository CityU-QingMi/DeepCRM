    @Test
    public void testSuffixPathSpec()
    {
        ServletPathSpec spec = new ServletPathSpec("*.gz");
        assertEquals("Spec.pathSpec","*.gz",spec.getDeclaration());
        assertEquals("Spec.pathDepth",0,spec.getPathDepth());

        assertMatches(spec,"/downloads/distribution.tar.gz");
        assertMatches(spec,"/downloads/jetty.log.gz");

        assertNotMatches(spec,"/downloads/distribution.zip");
        assertNotMatches(spec,"/downloads/distribution.tgz");
        assertNotMatches(spec,"/abs/path");

        assertEquals("Spec.pathInfo",null,spec.getPathInfo("/downloads/distribution.tar.gz"));
    }
