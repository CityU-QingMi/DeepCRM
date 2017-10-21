    @Test
    public void testDefaultPathSpec()
    {
        UriTemplatePathSpec spec = new UriTemplatePathSpec("/");
        assertEquals("Spec.pathSpec","/",spec.getDeclaration());
        assertEquals("Spec.pattern","^/$",spec.getPattern().pattern());
        assertEquals("Spec.pathDepth",1,spec.getPathDepth());
        assertEquals("Spec.group",PathSpecGroup.EXACT,spec.getGroup());

        assertEquals("Spec.variableCount",0,spec.getVariableCount());
        assertEquals("Spec.variable.length",0,spec.getVariables().length);
    }
