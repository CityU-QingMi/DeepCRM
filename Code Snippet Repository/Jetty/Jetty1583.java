    @Test
    public void testSuffixSpec()
    {
        RegexPathSpec spec = new RegexPathSpec("^(.*).do$");
        assertEquals("Spec.pathSpec","^(.*).do$",spec.getDeclaration());
        assertEquals("Spec.pattern","^(.*).do$",spec.getPattern().pattern());
        assertEquals("Spec.pathDepth",0,spec.getPathDepth());
        assertEquals("Spec.group",PathSpecGroup.SUFFIX_GLOB,spec.group);

        assertMatches(spec,"/a.do");
        assertMatches(spec,"/a/b/c.do");
        assertMatches(spec,"/abcde.do");
        assertMatches(spec,"/abc/efg.do");

        assertNotMatches(spec,"/a");
        assertNotMatches(spec,"/aa");
        assertNotMatches(spec,"/aa/bb");
        assertNotMatches(spec,"/aa/bb.do/more");
    }
