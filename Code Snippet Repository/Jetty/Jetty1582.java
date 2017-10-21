    @Test
    public void testPrefixSpec()
    {
        RegexPathSpec spec = new RegexPathSpec("^/a/(.*)$");
        assertEquals("Spec.pathSpec","^/a/(.*)$",spec.getDeclaration());
        assertEquals("Spec.pattern","^/a/(.*)$",spec.getPattern().pattern());
        assertEquals("Spec.pathDepth",2,spec.getPathDepth());
        assertEquals("Spec.group",PathSpecGroup.PREFIX_GLOB,spec.group);

        assertMatches(spec,"/a/");
        assertMatches(spec,"/a/b");
        assertMatches(spec,"/a/b/c/d/e");

        assertNotMatches(spec,"/a");
        assertNotMatches(spec,"/aa");
        assertNotMatches(spec,"/aa/bb");
    }
