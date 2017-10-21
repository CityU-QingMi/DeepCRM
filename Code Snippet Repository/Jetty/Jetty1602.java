    @Test
    public void testExactTwoPathSpec()
    {
        UriTemplatePathSpec spec = new UriTemplatePathSpec("/a/b");
        assertEquals("Spec.pathSpec","/a/b",spec.getDeclaration());
        assertEquals("Spec.pattern","^/a/b$",spec.getPattern().pattern());
        assertEquals("Spec.pathDepth",2,spec.getPathDepth());
        assertEquals("Spec.group",PathSpecGroup.EXACT,spec.getGroup());

        assertEquals("Spec.variableCount",0,spec.getVariableCount());
        assertEquals("Spec.variable.length",0,spec.getVariables().length);

        assertMatches(spec,"/a/b");

        assertNotMatches(spec,"/a/b/");
        assertNotMatches(spec,"/a/");
        assertNotMatches(spec,"/a/bb");
    }
