    @Test
    public void testExactSpec()
    {
        RegexPathSpec spec = new RegexPathSpec("^/a$");
        assertEquals("Spec.pathSpec","^/a$",spec.getDeclaration());
        assertEquals("Spec.pattern","^/a$",spec.getPattern().pattern());
        assertEquals("Spec.pathDepth",1,spec.getPathDepth());
        assertEquals("Spec.group",PathSpecGroup.EXACT,spec.group);

        assertMatches(spec,"/a");

        assertNotMatches(spec,"/aa");
        assertNotMatches(spec,"/a/");
    }
