    @Test
    public void testExactPathSpec_TestWebapp()
    {
        UriTemplatePathSpec spec = new UriTemplatePathSpec("/deep.thought/");
        assertEquals("Spec.pathSpec","/deep.thought/",spec.getDeclaration());
        assertEquals("Spec.pattern","^/deep\\.thought/$",spec.getPattern().pattern());
        assertEquals("Spec.pathDepth",1,spec.getPathDepth());
        assertEquals("Spec.group",PathSpecGroup.EXACT,spec.getGroup());
        
        assertMatches(spec,"/deep.thought/");
        assertNotMatches(spec,"/deep.thought");

        assertEquals("Spec.variableCount",0,spec.getVariableCount());
        assertEquals("Spec.variable.length",0,spec.getVariables().length);
    }
