    @Test
    public void testExactOnePathSpec()
    {
        UriTemplatePathSpec spec = new UriTemplatePathSpec("/a");
        assertEquals("Spec.pathSpec","/a",spec.getDeclaration());
        assertEquals("Spec.pattern","^/a$",spec.getPattern().pattern());
        assertEquals("Spec.pathDepth",1,spec.getPathDepth());
        assertEquals("Spec.group",PathSpecGroup.EXACT,spec.getGroup());
        
        assertMatches(spec,"/a");
        assertMatches(spec,"/a?type=other");
        assertNotMatches(spec,"/a/b");
        assertNotMatches(spec,"/a/");

        assertEquals("Spec.variableCount",0,spec.getVariableCount());
        assertEquals("Spec.variable.length",0,spec.getVariables().length);
    }
