    @Test
    public void testExactPathSpec()
    {
        ServletPathSpec spec = new ServletPathSpec("/abs/path");
        assertEquals("Spec.pathSpec","/abs/path",spec.getDeclaration());
        assertEquals("Spec.pathDepth",2,spec.getPathDepth());

        assertMatches(spec,"/abs/path");
        
        assertNotMatches(spec,"/abs/path/");
        assertNotMatches(spec,"/abs/path/more");
        assertNotMatches(spec,"/foo");
        assertNotMatches(spec,"/foo/abs/path");
        assertNotMatches(spec,"/foo/abs/path/");
    }
